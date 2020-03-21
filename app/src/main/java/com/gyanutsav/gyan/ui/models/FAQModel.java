package com.gyanutsav.gyan.ui.models;

public class FAQModel {

    String id;
    String ques;
    String Ans;

    public FAQModel() {
    }

    public FAQModel(String id, String ques, String ans) {
        this.id = id;
        this.ques = ques;
        Ans = ans;
    }

    public String getId() {
        return id;
    }

    public String getQues() {
        return ques;
    }

    public String getAns() {
        return Ans;
    }
}
