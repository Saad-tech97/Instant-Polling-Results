package com.example.polling;

public class Poll {

    String Name;
    String Email;
    String QuestionId;
    String Question;
    String Type;
    String AddOption1;
    String AddOption2;
    String AddOption3;
    String AddOption4;

    private Poll(){

    }

    public Poll(String name, String email, String questionId, String question, String type, String addOption1, String addOption2, String addOption3, String addOption4) {
        Name = name;
        Email = email;
        QuestionId = questionId;
        Question = question;
        Type = type;
        AddOption1 = addOption1;
        AddOption2 = addOption2;
        AddOption3 = addOption3;
        AddOption4 = addOption4;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String questionId) {
        QuestionId = questionId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddOption1() {
        return AddOption1;
    }

    public void setAddOption1(String addOption1) {
        AddOption1 = addOption1;
    }

    public String getAddOption2() {
        return AddOption2;
    }

    public void setAddOption2(String addOption2) {
        AddOption2 = addOption2;
    }

    public String getAddOption3() {
        return AddOption3;
    }

    public void setAddOption3(String addOption3) {
        AddOption3 = addOption3;
    }

    public String getAddOption4() {
        return AddOption4;
    }

    public void setAddOption4(String addOption4) {
        AddOption4 = addOption4;
    }
}
