package com.example.niramaya_health.models

class Professional {
    var name:String?=null
    var email:String?=null
    var registry_number:String?=null
    var mobile_number:String?=null

    constructor()
    {

    }

    constructor(name: String?, email: String?, registry_number: String?, mobile_number: String?) {
        this.name = name
        this.email = email
        this.registry_number = registry_number
        this.mobile_number = mobile_number
    }
}