package com.chydee.solarpowercalculator.model;

public class Appliances {

    private String mApplianceName;
    private String mApplianceWattage;
    private String mApplianceDurationOfUse;

    public Appliances(String applianceName, String applianceWattage, String  applianceDurationOfUse) {
        mApplianceName = applianceName;
        mApplianceWattage = applianceWattage;
        mApplianceDurationOfUse = applianceDurationOfUse;
    }

    //Set Getters

    public String getApplianceName() {
        return mApplianceName;
    }

    public String getApplianceWattage() {
        return mApplianceWattage;
    }

    public String getApplianceDurationOfUse() {
        return mApplianceDurationOfUse;
    }
}
