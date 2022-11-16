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

public class MyApplication
{
    private String name;
    private String dob;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;
    private Double monthlyIncome;
    private String occupation;
    private String employer;

    public MyApplication()
    {
    }

    public MyApplication(String name, String dob, String address, String city, String state, String zip,
                         String phoneNumber, String email, Double monthlyIncome, String occupation, String employer)
    {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.monthlyIncome = monthlyIncome;
        this.occupation = occupation;
        this.employer = employer;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDob()
    {
        return dob;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Double getMonthlyIncome()
    {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome)
    {
        this.monthlyIncome = monthlyIncome;
    }

    public String getOccupation()
    {
        return occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public String getEmployer()
    {
        return employer;
    }

    public void setEmployer(String employer)
    {
        this.employer = employer;
    }

    @Override
    public String toString()
    {
        return "MyApplication{" + "name='" + name + '\'' + ", dob='" + dob + '\'' + ", address='" + address + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zip='" + zip + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + ", monthlyIncome=" + monthlyIncome + ", occupation='" + occupation + '\'' + ", employer='" + employer + '\'' + '}';
    }
}

