package com.example.triviaapprk22applicationv2.model.triviadata;

public class PreparedMultipleChoiceQuestion {
    private String question;
    private String[] allPossibleAnswers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAllPossibleAnswers() {
        return allPossibleAnswers;
    }

    public void setAllPossibleAnswers(String[] allPossibleAnswers) {
        this.allPossibleAnswers = allPossibleAnswers;
    }
}
