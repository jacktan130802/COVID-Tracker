

package com.sp.corona.model.casetime;

import lombok.Data;

@Data
public class Statewise {
    private String recovered;

    private String deltadeaths;

    private String deltarecovered;

    private String active;

    private String deltaconfirmed;

    private String state;

    private String statecode;

    private String confirmed;

    private String deaths;

    private String lastupdatedtime;

    private String Province_state;
    private String getTotalconfirmed;
    private String getTotaldeceased;


    public String getGetTotalconfirmed() {
        return getTotalconfirmed;
    }

    public String getGetTotaldeceased() {
        return getTotaldeceased;
    }

    public String getState() {
        return state;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getActive() {
        return active;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getProvince_state() {
        return Province_state;
    }
}
