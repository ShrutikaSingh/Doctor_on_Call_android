package com.saloni.doctoroncall.Doctor;

import java.util.ArrayList;

public class Epidemic {
    private String eName;
    private String patientName;
    private String patientMobile;

    private ArrayList<String> symptoms;

    private String docKey;
    private String doctorName;
    private String doctorPhone;

    private long time;

    private String latitude;
    private String longitude;

    public Epidemic() {

    }

    public Epidemic(String eName, String patientName, String patientMobile, ArrayList<String> symptoms, String docKey, String doctorName, String doctorPhone, long time, String latitude, String longitude) {
        this.eName = eName;
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.symptoms = symptoms;
        this.docKey = docKey;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getDocKey() {
        return docKey;
    }

    public void setDocKey(String docKey) {
        this.docKey = docKey;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
