package com.example.niramaya_health.models

class User {

    var name:String?=null
    var email:String?=null
    var number:String?=null

    constructor()
    {

    }

    constructor(name: String?, email: String?, number: String?) {
        this.name = name
        this.email = email
        this.number = number
    }

}