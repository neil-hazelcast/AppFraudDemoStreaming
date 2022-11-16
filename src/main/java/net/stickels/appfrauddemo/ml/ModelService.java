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

package net.stickels.appfrauddemo.ml;

import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.RegressionModelPrediction;
import net.stickels.appfrauddemo.data.ApplicationCounts;

import java.io.IOException;

public class ModelService
{
    private final static String FQ_MODEL_FILE = "DeepLearning_Model.zip";


    public static void main(String[] args)
    {
        ModelService ms = new ModelService();
        // int good_name_count, int bad_name_count, int good_ssn_count,
        // int bad_ssn_count, int good_address_count,
        //int bad_address_count, int good_phone_count, int bad_phone_count, int good_email_count,
        //int bad_email_count, double MonthlyIncome, double FICO_SCORE, double ApplicationAmount
        ApplicationCounts counts = new ApplicationCounts(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5000d, 650d, 5000d);
        try {
            double prediction = ms.getClassification(counts);
            System.out.println("prediction is "+prediction);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PredictException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public double getClassification(ApplicationCounts counts) throws IOException, PredictException {
        EasyPredictModelWrapper model = new EasyPredictModelWrapper(MojoModel.load(FQ_MODEL_FILE));
        RowData row = new RowData();
        row.put("good_name_count", counts.getGood_name_count());
        row.put("bad_name_count", counts.getBad_name_count());
        row.put("good_ssn_count", counts.getGood_ssn_count());
        row.put("bad_ssn_count", counts.getBad_ssn_count());
        //row.put("good_dob_count", counts.getGood_dob_count());
        //row.put("bad_dob_count", counts.getBad_dob_count());
        row.put("good_address_count", counts.getGood_address_count());
        row.put("bad_address_count", counts.getBad_address_count());
        row.put("good_phone_count", counts.getGood_phone_count());
        row.put("bad_phone_count", counts.getBad_phone_count());
        row.put("good_email_count", counts.getGood_email_count());
        row.put("bad_email_count", counts.getBad_email_count());
        row.put("MonthlyIncome", counts.getMonthlyIncome());
        row.put("FICO_SCORE", counts.getFICO_SCORE());
        row.put("ApplicationAmount", counts.getApplicationAmount());
        //System.out.println(model.predict(row));
        RegressionModelPrediction p = model.predictRegression(row);
        //MultinomialModelPrediction p = model.predictMultinomial(row);
        //System.out.println("stageProbabilities: "+p.stageProbabilities);
        System.out.println("model score: "+p.value);
        //return p.label;
        return p.value;
    }

}

