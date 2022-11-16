/*
 * Copyright 2018-2021 Hazelcast, Inc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.stickels.appfrauddemo.streaming;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.jet.Util;
import com.hazelcast.jet.pipeline.*;
import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.Logger;
import com.hazelcast.map.IMap;
import net.stickels.appfrauddemo.data.ApplicationCounts;
import net.stickels.appfrauddemo.ml.ModelService;

import java.util.Map;
import java.util.Properties;

public class ApplicationStreaming
{
    private static final String TOPIC = "Applications";
    private static final String MAP_NAME = "Application-Event-Map";
    private static final String RESPONSE_MAP_NAME = "Application-Event-Response-Map";
    private final static ILogger log = Logger.getLogger(ApplicationStreaming.class);

    public static void main(String[] args)
    {

        HazelcastInstance hz = Hazelcast.bootstrappedInstance();
        Pipeline p = buildPipeline(hz);

        hz.getJet().newJob(p);
    }

    private static Pipeline buildPipeline(HazelcastInstance hz)
    {
        IMap<String, String> sourceMap = hz.getMap(MAP_NAME);
        Pipeline p = Pipeline.create();
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "net.stickels.appfrauddemo.streaming.MyApplicationDeserializer");

        //StreamSource<Map.Entry<Object, MyApplication>> source = KafkaSources.kafka(properties,TOPIC);
        //p.readFrom(source)
        //        .withoutTimestamps()
        //        .writeTo(Sinks.logger());
        StreamStage<Map.Entry<String, String>> events = p.readFrom(Sources.mapJournal(sourceMap, JournalInitialPosition.START_FROM_OLDEST))
                .withoutTimestamps().setName("Receive Application Event");
        ServiceFactory<?, ModelService> modelService = ServiceFactories.sharedService(ctx-> new ModelService())
                .toNonCooperative();
        StreamStage<Map.Entry<String, ApplicationCounts>> applicationCounts = events.map(
                entry-> Util.entry(entry.getKey(), ApplicationCounts.jsonToAppCounts(entry.getValue()))
        ).setName("Convert JSON to ApplicationCounts");
        StreamStage<Map.Entry<String, ApplicationCounts>> prediction = applicationCounts.mapUsingService(
                modelService, (service, entry) ->
                {
                    Double bad = service.getClassification(entry.getValue());
                    entry.getValue().setBad(bad);
                    return entry;
                }).setName("Deep Learning Model");
        //IList<Double> responseList = hz.getList(appGuid.get());
        IMap<String, Double> responseMap = hz.getMap(RESPONSE_MAP_NAME);
        //SinkStage sinkStage = prediction.writeTo(Sinks.map(responseMap)).setName("Write to Response Map");
        SinkStage sinkStage = prediction.map(e->Util.entry(e.getKey(),e.getValue().getBad()))
        //    {
        //        Map.Entry<String, Double> entry = new E
        //        double score = e.getValue().getBad();
        //        log.info("appGuid.get is "+appGuid.get());
        //        log.info("writing "+score+" to that list");
        //        return score;
        //    })
        .writeTo(Sinks.map(responseMap)).setName("Write to Response Map");
        //SinkStage sinkStage = prediction.writeTo(Sinks.map(responseMap)).setName("Write to Response Map");

        return p;
    }
}
