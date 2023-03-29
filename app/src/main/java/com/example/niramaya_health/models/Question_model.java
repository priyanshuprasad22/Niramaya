package com.example.niramaya_health.models;

import androidx.navigation.NavType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question_model implements Serializable {

    String Question;
    String type;
    ArrayList<String> choices;

    public Question_model(String question,String type)
    {
        Question=question;
        this.type=type;
    }

    public Question_model(String question, String type, ArrayList<String> choices) {
        Question = question;
        this.type = type;
        this.choices = choices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
