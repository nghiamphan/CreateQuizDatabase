package com.nphan.android.createquizdatabase;

import java.util.UUID;

public class Question {

    private UUID mId;
    private String mQuestionText;
    private String mOptionA;
    private String mOptionB;
    private String mOptionC;
    private String mOptionD;
    private int mAnswer; // A:0, B:1, C:2, D:3

    public Question() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(String questionText) {
        mQuestionText = questionText;
    }

    public String getOptionA() {
        return mOptionA;
    }

    public void setOptionA(String optionA) {
        mOptionA = optionA;
    }

    public String getOptionB() {
        return mOptionB;
    }

    public void setOptionB(String optionB) {
        mOptionB = optionB;
    }

    public String getOptionC() {
        return mOptionC;
    }

    public void setOptionC(String optionC) {
        mOptionC = optionC;
    }

    public String getOptionD() {
        return mOptionD;
    }

    public void setOptionD(String optionD) {
        mOptionD = optionD;
    }

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
        mAnswer = answer;
    }

    public String printQuestion() {
        String s = mQuestionText + "\n" +
                "A: " + mOptionA + "\n" +
                "B: " + mOptionB + "\n" +
                "C: " + mOptionC + "\n" +
                "D: " + mOptionD + "\n" +
                "Answer: " + Integer.toString(mAnswer);
        return s;
    }
}
