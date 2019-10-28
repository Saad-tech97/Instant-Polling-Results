package com.example.polling;

public class Answer {

    private String Answerid;
    private String QuestionPoll;
    private String TypePoll;
    private String option;
    private String Rating;

   public Answer(){

   }

    public Answer(String answerid, String questionPoll, String typePoll, String option, String rating) {
        Answerid = answerid;
        QuestionPoll = questionPoll;
        TypePoll = typePoll;
        this.option = option;
        Rating = rating;
    }

    public String getAnswerid() {
        return Answerid;
    }

    public void setAnswerid(String answerid) {
        Answerid = answerid;
    }

    public String getQuestionPoll() {
        return QuestionPoll;
    }

    public void setQuestionPoll(String questionPoll) {
        QuestionPoll = questionPoll;
    }

    public String getTypePoll() {
        return TypePoll;
    }

    public void setTypePoll(String typePoll) {
        TypePoll = typePoll;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
