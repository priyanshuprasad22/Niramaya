package com.example.niramaya_health.adapters;

import java.io.Serializable;

public class HeaderTextItem implements Serializable {
    private String headertext;
    private String headerText;

    public HeaderTextItem(String headerText){
        this.headertext = headerText;
    }
    public String getHeaderText(){
        return headerText;
    }
    public void setHeaderText(String headerText){
        this.headertext = headerText;
    }
}
