package com.nphan.android.createquizdatabase.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.nphan.android.createquizdatabase.Question;
import com.nphan.android.createquizdatabase.database.QuestionDbSchema.QuestionTable;

import java.util.UUID;

public class QuestionCursorWrapper extends CursorWrapper {
    public QuestionCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Question getQuestion() {
        String uuidString = getString(getColumnIndex(QuestionTable.Cols.UUID));
        String questionText = getString(getColumnIndex(QuestionTable.Cols.QUESTION));
        String optionA = getString(getColumnIndex(QuestionTable.Cols.A));
        String optionB = getString(getColumnIndex(QuestionTable.Cols.B));
        String optionC = getString(getColumnIndex(QuestionTable.Cols.C));
        String optionD = getString(getColumnIndex(QuestionTable.Cols.D));
        int answer = getInt(getColumnIndex(QuestionTable.Cols.ANSWER));

        Question question = new Question(UUID.fromString(uuidString));
        question.setQuestionText(questionText);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setAnswer(answer);

        return question;
    }
}
