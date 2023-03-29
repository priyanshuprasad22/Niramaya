package com.example.niramaya_health.models;

public class Question_Answer {

    String question;
    String answer;

    String date;

    Question_Answer()
    {

    }

    public Question_Answer(String question, String answer,String date) {
        this.question = question;
        this.answer = answer;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
