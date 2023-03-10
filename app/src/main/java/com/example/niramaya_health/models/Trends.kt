package com.example.niramaya_health.models

class Trends {

      var title:String? = null
      var image_url:String? =null
      var discription:String? =null
      var content:String ?=null
      var url:String ?=null
    constructor(
        title: String,
        image_url: String,
        discription: String,
        content: String,
        url: String
    ) {
        this.title = title
        this.image_url = image_url
        this.discription = discription
        this.content = content
        this.url = url
    }

    constructor()
    {

    }





}