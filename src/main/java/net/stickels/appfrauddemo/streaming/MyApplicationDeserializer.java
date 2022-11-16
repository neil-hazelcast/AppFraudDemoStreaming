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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class MyApplicationDeserializer implements Deserializer
{

    @Override
    public void configure(Map map, boolean b)
    {

    }

    @Override
    public MyApplication deserialize(String s, byte[] bytes)
    {
        if(bytes == null)
            return null;
        //String jsonStr = new String(bytes);
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            JsonNode json = objectMapper.readTree(bytes);
            MyApplication app = new MyApplication();
            app.setName(json.get("name").asText());
            app.setDob(json.get("dob").asText());
            app.setAddress(json.get("address").asText());
            app.setCity(json.get("city").asText());
            app.setState(json.get("state").asText());
            app.setZip(json.get("zip").asText());
            app.setPhoneNumber(json.get("phone").asText());
            app.setEmail(json.get("email").asText());
            app.setMonthlyIncome(json.get("monthlyIncome").asDouble());
            app.setOccupation(json.get("occupation").asText());
            app.setEmployer(json.get("employer").asText());
            return app;
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void close()
    {

    }
}
