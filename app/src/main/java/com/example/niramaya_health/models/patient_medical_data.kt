package com.example.niramaya_health.models

class patient_medical_data {

    var patient_name:String?=null
    var patient_id:String?=null
    var doctor_id:String?=null
    var advices:String?=null
    var doctorname:String?=null
    var symptoms:Map<String,String>?=null
    var doctoraddedsymptoms:String?=null
    var dateofvist:String?=null
    var allergies:String?=null
    var diagonosis:String?=null
    var treatmentplan:String?=null
    var medication:String?=null
    var followup:String?=null
    var height:String?=null
    var O2level:String?=null
    var weight:String?=null
    var bloodpressure:String?=null
    var complete:Boolean?=null

    constructor()
    {

    }

    constructor(
        patient_name: String?,
        patient_id: String?,
        doctor_id: String?,
        advices: String?,
        doctorname: String?,
        symptoms:Map<String,String>,
        doctoraddedsymptoms: String?,
        dateofvist: String?,
        allergies: String?,
        diagonosis: String?,
        treatmentplan: String?,
        medication: String?,
        followup: String?,
        height: String?,
        O2level: String?,
        weight: String?,
        bloodpressure: String?,
        complete:Boolean?

    ) {
        this.patient_name = patient_name
        this.patient_id = patient_id
        this.doctor_id = doctor_id
        this.advices = advices
        this.doctorname = doctorname
        this.symptoms = symptoms
        this.doctoraddedsymptoms=doctoraddedsymptoms
        this.dateofvist = dateofvist
        this.allergies = allergies
        this.diagonosis = diagonosis
        this.treatmentplan = treatmentplan
        this.medication = medication
        this.followup = followup
        this.height = height
        this.O2level = O2level
        this.weight = weight
        this.bloodpressure = bloodpressure
        this.complete=complete
    }

    override fun toString(): String {
        return "patient_medical_data(patient_name=$patient_name, patient_id=$patient_id, doctor_id=$doctor_id, advices=$advices, doctorname=$doctorname, symptoms=$symptoms, dateofvist=$dateofvist, allergies=$allergies, diagonosis=$diagonosis, treatmentplan=$treatmentplan, medication=$medication, followup=$followup, height=$height, O2level=$O2level, weight=$weight, bloodpressure=$bloodpressure,complete=$complete)"
    }


}