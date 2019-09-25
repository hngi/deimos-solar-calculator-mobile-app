package com.chydee.solarpowercalculator.model;

public class Appliances {

    private String mApplianceName;
    private int mApplianceWattage;
    private int mApplianceDurationOfUse;

    public Appliances(String applianceName, int applianceWattage, int applianceDurationOfUse) {
        mApplianceName = applianceName;
        mApplianceWattage = applianceWattage;
        mApplianceDurationOfUse = applianceDurationOfUse;
    }

    //Set Getters

    public String getApplianceName() {
        return mApplianceName;
    }

    public int getApplianceWattage() {
        return mApplianceWattage;
    }

    public int getApplianceDurationOfUse() {
        return mApplianceDurationOfUse;
    }
}
