package com.example.niramaya_health.adapters;

import android.widget.TextView;

import java.io.Serializable;

public class PostTextItem  implements Serializable {

    private String posttext;
    private int imguser;
    private String time;
    private String diagonisis;
    private String date_of_visit;
    private String followup;
    private String medicines;

    PostTextItem()
    {

    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getDiagonisis() {
        return diagonisis;
    }

    public void setDiagonisis(String diagonisis) {
        this.diagonisis = diagonisis;
    }

    public String getDate_of_visit() {
        return date_of_visit;
    }

    public void setDate_of_visit(String date_of_visit) {
        this.date_of_visit = date_of_visit;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }



    public String getPostText() {
        return posttext;
    }

    public void setPostText(String postText) {
        this.posttext = postText;
    }

    public int getImgUser() {
        return imguser;
    }

    public void setImgUser(int imguser) {
        this.imguser = imguser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PostTextItem(String posttext, int imguser, String time,String diagonisis,String date_of_visit,String followup,String medicines ) {
        this.posttext = posttext;
        this.imguser = imguser;
        this.time = time;
        this.diagonisis=diagonisis;
        this.date_of_visit=date_of_visit;
        this.medicines=medicines;
        this.followup=followup;
    }
}
