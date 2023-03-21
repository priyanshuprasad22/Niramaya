package com.example.niramaya_health.models

class patient_medical_data {

    var patient_name:String?=null
    var patient_id:String?=null
    var symptoms:List<String>?=null
    var dateofvist:String?=null
    var medicalhistory:List<String>?=null
    var allergies:String?=null
    var physicalexam:String?=null
    var labResult:String?=null
    var diagonosis:String?=null
    var treatmentplan:String?=null
    var medication:List<String>?=null
    var followup:String?=null

    constructor()
    {

    }

    constructor(
        patient_name: String?,
        patient_id: String?,
        symptoms: List<String>?,
        dateofvist: String?,
        medicalhistory: List<String>?,
        allergies: String?,
        physicalexam: String?,
        labResult: String?,
        diagonosis: String?,
        treatmentplan: String?,
        medication: List<String>?,
        followup: String?
    ) {
        this.patient_name = patient_name
        this.patient_id = patient_id
        this.symptoms = symptoms
        this.dateofvist = dateofvist
        this.medicalhistory = medicalhistory
        this.allergies = allergies
        this.physicalexam = physicalexam
        this.labResult = labResult
        this.diagonosis = diagonosis
        this.treatmentplan = treatmentplan
        this.medication = medication
        this.followup = followup
    }

    override fun toString(): String {
        return "patient_medical_data(patient_name=$patient_name, patient_id=$patient_id, symptoms=$symptoms, dateofvist=$dateofvist, medicalhistory=$medicalhistory, allergies=$allergies, physicalexam=$physicalexam, labResult=$labResult, diagonosis=$diagonosis, treatmentplan=$treatmentplan, medication=$medication, followup=$followup)"
    }

}