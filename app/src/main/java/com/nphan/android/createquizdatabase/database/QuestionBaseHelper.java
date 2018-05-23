package com.nphan.android.createquizdatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nphan.android.createquizdatabase.database.QuestionDbSchema.QuestionTable;

public class QuestionBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "questionBase.db";

    public QuestionBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + QuestionTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                QuestionTable.Cols.UUID + ", " +
                QuestionTable.Cols.QUESTION + ", " +
                QuestionTable.Cols.A + ", " +
                QuestionTable.Cols.B + ", " +
                QuestionTable.Cols.C + ", " +
                QuestionTable.Cols.D + ", " +
                QuestionTable.Cols.ANSWER + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
