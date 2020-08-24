
package com.sp.corona.model.casetime;

import lombok.Data;

@Data
public class Cases_time_series {
    private String country;

    private String state;

    private String date;

    private String confirmed;

    private String death;

    private String recovered;
    private String dailyconfirmed;

    private String dailydeceased;

    private String dailyrecovered;

    private String totalconfirmed;

    private String totaldeceased;

    private String totalrecovered;


//    private String totalrecovered;


    public String getConfirmed() {
        return confirmed;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public String getDeath() {
        return death;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getState() {
        return state;
    }
}





//CHANGED

//"Province_State":"New York","Confirmed":390415,"Deaths":31301,"Recovered":70010,"Active":289104,"People_Tested":3619594,"People_Hospitalized":89995




