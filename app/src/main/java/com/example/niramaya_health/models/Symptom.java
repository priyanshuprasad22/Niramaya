package com.example.niramaya_health.models;

import java.io.Serializable;
import java.util.Map;

public class Symptom implements Serializable {

    Map<String,String> symptoms;
    String userId;
    String doctorId;
    String dateofvist;

    String timeofvisit;

    String username;
    String useremail;
    String usercontact;

    String doctorname;
    String location;
    String specialization;



    public Symptom()
    {

    }

    public Symptom(Map<String, String> symptoms, String userId, String doctorId, String dateofvist,String timeofvisit,String username,String useremail,String usercontact,String doctorname,String location,String specialization) {
        this.symptoms = symptoms;
        this.userId = userId;
        this.doctorId = doctorId;
        this.dateofvist = dateofvist;
        this.timeofvisit=timeofvisit;
        this.usercontact=usercontact;
        this.useremail=useremail;
        this.username=username;
        this.doctorname=doctorname;
        this.location=location;
        this.specialization=specialization;
    }

    public Map<String, String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Map<String, String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDateofvist() {
        return dateofvist;
    }

    public void setDateofvist(String dateofvist) {
        this.dateofvist = dateofvist;
    }

    public String getTimeofvisit() {
        return timeofvisit;
    }

    public void setTimeofvisit(String timeofvisit) {
        this.timeofvisit = timeofvisit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsercontact() {
        return usercontact;
    }

    public void setUsercontact(String usercontact) {
        this.usercontact = usercontact;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "symptoms=" + symptoms +
                ", userId='" + userId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", dateofvist='" + dateofvist + '\'' +
                ", timeofvisit='" + timeofvisit + '\'' +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", usercontact='" + usercontact + '\'' +
                ", doctorname='" + doctorname + '\'' +
                ", location='" + location + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
