package com.example.niramaya_health.models;

public class UserUpcomingAppoint {

    String doctorname;
    String location;
    String dateofappointment;
    String timeofappointment;

    String specialization;

    public UserUpcomingAppoint(String doctorname, String location, String dateofappointment, String timeofappointment, String specialization) {
        this.doctorname = doctorname;
        this.location = location;
        this.dateofappointment = dateofappointment;
        this.timeofappointment = timeofappointment;
        this.specialization = specialization;
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

    public String getDateofappointment() {
        return dateofappointment;
    }

    public void setDateofappointment(String dateofappointment) {
        this.dateofappointment = dateofappointment;
    }

    public String getTimeofappointment() {
        return timeofappointment;
    }

    public void setTimeofappointment(String timeofappointment) {
        this.timeofappointment = timeofappointment;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
