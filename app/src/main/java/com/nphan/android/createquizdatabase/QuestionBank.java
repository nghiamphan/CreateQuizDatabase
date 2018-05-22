package com.nphan.android.createquizdatabase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionBank {
    private static QuestionBank sQuestionBank;

    private List<Question> mQuestions;

    public static QuestionBank get(Context context) {
        if (sQuestionBank == null) {
            sQuestionBank = new QuestionBank(context);
        }
        return sQuestionBank;
    }

    private QuestionBank(Context context) {
        mQuestions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            question.setQuestionText("Question #" + i);
            question.setAnswer(i%4);
            mQuestions.add(question);
        }
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public Question getQuestion(UUID id) {
        for (Question question : mQuestions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null;
    }
}
