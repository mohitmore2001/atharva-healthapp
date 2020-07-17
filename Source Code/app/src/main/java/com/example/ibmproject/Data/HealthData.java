package com.example.ibmproject.Data;

import android.provider.Settings;

public class HealthData {
    private int ID;
    private int percentage;
    private String recorddate;
    private String status;

    public HealthData(){

    }
    public HealthData( int percentage, String status) {
        this.percentage = percentage;
        this.status = status;
    }

    public HealthData(int ID, int percentage, String recorddate, String status) {
        this.ID = ID;
        this.percentage = percentage;
        this.recorddate = recorddate;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
