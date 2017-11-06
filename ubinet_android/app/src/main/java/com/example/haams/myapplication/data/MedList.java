package com.example.haams.myapplication.data;

/**
 * Created by haams on 2017-11-01.
 */

public class MedList {
    private int medNum;
    private String medName;
    private String medSrtDate;
    private String medEndDate;
    private String medTimes;

    public MedList(int medNum, String medName, String medSrtDate, String medEndDate, String medTimes) {
        this.medNum = medNum;
        this.medName = medName;
        this.medSrtDate = medSrtDate;
        this.medEndDate = medEndDate;
        this.medTimes = medTimes;
    }

    public MedList(String medName, String medSrtDate, String medEndDate, String medTimes) {
        this.medName = medName;
        this.medSrtDate = medSrtDate;
        this.medEndDate = medEndDate;
        this.medTimes = medTimes;
    }

    public int getMedNum() {
        return medNum;
    }

    public String getMedName() {
        return medName;
    }

    public String getMedSrtDate() {
        return medSrtDate;
    }

    public String getMedEndDate() {
        return medEndDate;
    }

    public String getMedTimes() {
        return medTimes;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public void setMedSrtDate(String medSrtDate) {
        this.medSrtDate = medSrtDate;
    }

    public void setMedEndDate(String medEndDate) {
        this.medEndDate = medEndDate;
    }

    public void setMedTimes(String medTimes) {
        this.medTimes = medTimes;
    }
}
