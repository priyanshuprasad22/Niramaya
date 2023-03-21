package com.example.niramaya_health.models

class medical_appoint_data {

    var patient_id:String?=null
    var doctor_id:String?=null
    var patient_name:String?=null
    var doctor_name:String?=null
    var last_appointment:String?=null
    var upcoming_appointment:String?=null
    var time:String?=null
    var patient_email:String?=null
    var patient_contact:String?=null

    constructor()
    {

    }

    constructor(
        patient_id: String?,
        doctor_id: String?,
        patient_name: String?,
        doctor_name: String?,
        last_appointment: String?,
        upcoming_appointment: String?,
        time: String?,
        patient_contact:String?,
        patient_email:String?


    ) {
        this.patient_id = patient_id
        this.doctor_id = doctor_id
        this.patient_name = patient_name
        this.doctor_name = doctor_name
        this.upcoming_appointment = upcoming_appointment
        this.last_appointment = last_appointment
        this.time = time
        this.patient_contact=patient_contact
        this.patient_email=patient_email
    }

    override fun toString(): String {
        return "medical_appoint_data(patient_id=$patient_id, doctor_id=$doctor_id, patient_name=$patient_name, doctor_name=$doctor_name, last_appointment=$last_appointment, upcoming_appointment=$upcoming_appointment, time=$time, patient_contact=$patient_contact, patient_email=$patient_email)"
    }


}