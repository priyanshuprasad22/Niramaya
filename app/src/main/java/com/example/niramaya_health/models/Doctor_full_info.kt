package com.example.niramaya_health.models

class Doctor_full_info : java.io.Serializable{

    var about:String?=null;
    var contact:String?=null;
    var downloadUrl:String?=null;
    var email:String?=null;
    var experience:String?=null;
    var location:String?=null;
    var name:String?=null;
    var qualification:String?=null;
    var registration:String?=null;
    var UserId:String?=null;
    var specialization:String?=null;

    constructor()
    {

    }


    constructor(
        about: String?,
        contact: String?,
        downloadUrl: String?,
        email: String?,
        experience: String?,
        location: String?,
        name: String?,
        qualification: String?,
        registration: String?,
        UserId: String?,
        specialization:String?
    ) {
        this.about = about
        this.contact = contact
        this.downloadUrl = downloadUrl
        this.email = email
        this.experience = experience
        this.location = location
        this.name = name
        this.qualification = qualification
        this.registration = registration
        this.UserId = UserId
        this.specialization=specialization
    }



}