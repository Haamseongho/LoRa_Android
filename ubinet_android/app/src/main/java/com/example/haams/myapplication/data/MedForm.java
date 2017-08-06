package com.example.haams.myapplication.data;

import java.util.Date;

/**
 * Created by haams on 2017-08-06.
 */

public class MedForm {
    private String medName;   // 약 명칭
    private int[] alarmHour; // 알림 시
    private int[] alarmMin; // 알림 분
    private Date startDate; // 복용 시작 날짜
    private Date endDate;   // 복용 종료 날짜

    public MedForm(String medName, int[] alarmHour, int[] alarmMin, Date startDate, Date endDate) {
        this.medName = medName;
        this.alarmHour = alarmHour;
        this.alarmMin = alarmMin;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public MedForm(String medName, int[] alarmHour, int[] alarmMin) {
        this.medName = medName;
        this.alarmHour = alarmHour;
        this.alarmMin = alarmMin;
    }

    public String getMedName() {
        return medName;
    }

    public int[] getAlarmHour() {
        return alarmHour;
    }

    public int[] getAlarmMin() {
        return alarmMin;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public void setAlarmHour(int[] alarmHour) {
        this.alarmHour = alarmHour;
    }

    public void setAlarmMin(int[] alarmMin) {
        this.alarmMin = alarmMin;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
