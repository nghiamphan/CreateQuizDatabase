package com.nphan.android.createquizdatabase.database;

public class QuestionDbSchema {
    public static final class QuestionTable {
        public static final String NAME = "questions";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String QUESTION = "question";
            public static final String A = "optionA";
            public static final String B = "optionB";
            public static final String C = "optionC";
            public static final String D = "optionD";
            public static final String ANSWER = "answer";
        }
    }
}
