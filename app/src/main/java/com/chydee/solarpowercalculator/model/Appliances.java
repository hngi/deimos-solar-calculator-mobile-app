package com.chydee.solarpowercalculator.model;

public class Appliances {

    private String mApplianceName;
    private String mApplianceWattage;
    private String mApplianceDurationOfUse;
    private String mApplianceQuantity;

    public Appliances(String applianceName, String applianceWattage, String applianceDurationOfUse, String applianceQuantity) {
        mApplianceName = applianceName;
        mApplianceWattage = applianceWattage;
        mApplianceDurationOfUse = applianceDurationOfUse;
        mApplianceQuantity = applianceQuantity;
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

    public String getApplianceQuantity() {
        return mApplianceQuantity;
    }
}
