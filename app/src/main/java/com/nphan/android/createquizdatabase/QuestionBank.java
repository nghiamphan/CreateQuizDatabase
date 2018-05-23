package com.nphan.android.createquizdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nphan.android.createquizdatabase.database.QuestionBaseHelper;
import com.nphan.android.createquizdatabase.database.QuestionCursorWrapper;
import com.nphan.android.createquizdatabase.database.QuestionDbSchema.QuestionTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionBank {
    private static QuestionBank sQuestionBank;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static QuestionBank get(Context context) {
        if (sQuestionBank == null) {
            sQuestionBank = new QuestionBank(context);
        }
        return sQuestionBank;
    }

    private QuestionBank(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new QuestionBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addQuestion(Question q) {
        ContentValues values = getContentValues(q);

        mDatabase.insert(QuestionTable.NAME, null, values);
    }

    public void deleteQuestion(Question q) {
        mDatabase.delete(QuestionTable.NAME,
                QuestionTable.Cols.UUID + " = ?",
                new String[] {q.getId().toString()});
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        QuestionCursorWrapper cursor = queryQuestions(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                questions.add(cursor.getQuestion());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return questions;
    }

    public void updateQuestion(Question question) {
        String uuidString = question.getId().toString();
        ContentValues values = getContentValues(question);

        mDatabase.update(QuestionTable.NAME, values,
                QuestionTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public Question getQuestion(UUID id) {
        QuestionCursorWrapper cursor = queryQuestions(
                QuestionTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getQuestion();
        }
        finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Question question) {
        ContentValues values = new ContentValues();
        values.put(QuestionTable.Cols.UUID, question.getId().toString());
        values.put(QuestionTable.Cols.QUESTION, question.getQuestionText());
        values.put(QuestionTable.Cols.A, question.getOptionA());
        values.put(QuestionTable.Cols.B, question.getOptionB());
        values.put(QuestionTable.Cols.C, question.getOptionC());
        values.put(QuestionTable.Cols.D, question.getOptionD());
        values.put(QuestionTable.Cols.ANSWER, question.getAnswer());

        return values;
    }

    private QuestionCursorWrapper queryQuestions(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                QuestionTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new QuestionCursorWrapper(cursor);
    }
}
