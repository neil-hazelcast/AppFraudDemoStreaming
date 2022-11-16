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

package net.stickels.appfrauddemo.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class ApplicationCounts implements Serializable
{
    private double good_name_count;
    private double bad_name_count;
    private double good_ssn_count;
    private double bad_ssn_count;
    private double good_address_count;
    private double bad_address_count;
    private double good_phone_count;
    private double bad_phone_count;
    private double good_email_count;
    private double bad_email_count;
    private double MonthlyIncome;
    private double FICO_SCORE;
    private double ApplicationAmount;
    private double bad;

    public ApplicationCounts()
    {

    }

    public ApplicationCounts(double good_name_count, double bad_name_count, double good_ssn_count,
                             double bad_ssn_count, double good_address_count, double bad_address_count,
                             double good_phone_count, double bad_phone_count, double good_email_count,
                             double bad_email_count, double MonthlyIncome, double FICO_SCORE, double ApplicationAmount)
    {
        this.good_name_count = good_name_count;
        this.bad_name_count = bad_name_count;
        this.good_ssn_count = good_ssn_count;
        this.bad_ssn_count = bad_ssn_count;
        this.good_address_count = good_address_count;
        this.bad_address_count = bad_address_count;
        this.good_phone_count = good_phone_count;
        this.bad_phone_count = bad_phone_count;
        this.good_email_count = good_email_count;
        this.bad_email_count = bad_email_count;
        this.MonthlyIncome = MonthlyIncome;
        this.FICO_SCORE = FICO_SCORE;
        this.ApplicationAmount = ApplicationAmount;
    }

    public ApplicationCounts(double good_name_count, double bad_name_count, double good_ssn_count,
                             double bad_ssn_count, double good_address_count, double bad_address_count,
                             double good_phone_count, double bad_phone_count, double good_email_count,
                             double bad_email_count, double MonthlyIncome, double FICO_SCORE, double ApplicationAmount,
                             double bad)
    {
        this.good_name_count = good_name_count;
        this.bad_name_count = bad_name_count;
        this.good_ssn_count = good_ssn_count;
        this.bad_ssn_count = bad_ssn_count;
        this.good_address_count = good_address_count;
        this.bad_address_count = bad_address_count;
        this.good_phone_count = good_phone_count;
        this.bad_phone_count = bad_phone_count;
        this.good_email_count = good_email_count;
        this.bad_email_count = bad_email_count;
        this.MonthlyIncome = MonthlyIncome;
        this.FICO_SCORE = FICO_SCORE;
        this.ApplicationAmount = ApplicationAmount;
        this.bad = bad;
    }

    public double getGood_name_count() {
        return good_name_count;
    }

    public void setGood_name_count(double good_name_count) {
        this.good_name_count = good_name_count;
    }

    public double getBad_name_count() {
        return bad_name_count;
    }

    public void setBad_name_count(double bad_name_count) {
        this.bad_name_count = bad_name_count;
    }

    public double getGood_ssn_count() {
        return good_ssn_count;
    }

    public void setGood_ssn_count(double good_ssn_count) {
        this.good_ssn_count = good_ssn_count;
    }

    public double getBad_ssn_count() {
        return bad_ssn_count;
    }

    public void setBad_ssn_count(double bad_ssn_count) {
        this.bad_ssn_count = bad_ssn_count;
    }

    public double getGood_address_count() {
        return good_address_count;
    }

    public void setGood_address_count(double good_address_count) {
        this.good_address_count = good_address_count;
    }

    public double getBad_address_count() {
        return bad_address_count;
    }

    public void setBad_address_count(double bad_address_count) {
        this.bad_address_count = bad_address_count;
    }

    public double getGood_phone_count() {
        return good_phone_count;
    }

    public void setGood_phone_count(double good_phone_count) {
        this.good_phone_count = good_phone_count;
    }

    public double getBad_phone_count() {
        return bad_phone_count;
    }

    public void setBad_phone_count(double bad_phone_count) {
        this.bad_phone_count = bad_phone_count;
    }

    public double getGood_email_count() {
        return good_email_count;
    }

    public void setGood_email_count(double good_email_count) {
        this.good_email_count = good_email_count;
    }

    public double getBad_email_count() {
        return bad_email_count;
    }

    public void setBad_email_count(double bad_email_count) {
        this.bad_email_count = bad_email_count;
    }

    public double getMonthlyIncome() {
        return MonthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        MonthlyIncome = monthlyIncome;
    }

    public double getFICO_SCORE() {
        return FICO_SCORE;
    }

    public void setFICO_SCORE(double fICO_SCORE) {
        FICO_SCORE = fICO_SCORE;
    }

    public double getApplicationAmount() {
        return ApplicationAmount;
    }

    public void setApplicationAmount(double applicationAmount) {
        ApplicationAmount = applicationAmount;
    }

    public double getBad() {
        return bad;
    }

    public void setBad(double bad) {
        this.bad = bad;
    }

    public static ApplicationCounts jsonToAppCounts(String jsonStr)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            JsonNode json = objectMapper.readTree(jsonStr);
            ApplicationCounts appCounts = new ApplicationCounts();
            appCounts.setGood_name_count(json.get("good_name_count").asDouble());
            appCounts.setBad_name_count(json.get("bad_name_count").asDouble());
            appCounts.setGood_ssn_count(json.get("good_ssn_count").asDouble());
            appCounts.setBad_ssn_count(json.get("bad_ssn_count").asDouble());
            appCounts.setGood_address_count(json.get("good_address_count").asDouble());
            appCounts.setBad_address_count(json.get("bad_address_count").asDouble());
            appCounts.setGood_phone_count(json.get("good_phone_count").asDouble());
            appCounts.setBad_phone_count(json.get("bad_phone_count").asDouble());
            appCounts.setGood_email_count(json.get("good_email_count").asDouble());
            appCounts.setBad_email_count(json.get("bad_email_count").asDouble());
            appCounts.setMonthlyIncome(json.get("MonthlyIncome").asDouble());
            appCounts.setFICO_SCORE(json.get("FICO_SCORE").asDouble());
            appCounts.setApplicationAmount(json.get("ApplicationAmount").asDouble());
            return appCounts;
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}


