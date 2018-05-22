package com.nphan.android.createquizdatabase;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class QuestionActivity extends SingleFragmentActivity {

    private static final String EXTRA_QUESTION_ID = "com.nphan.android.createquizdatabase.question_id";

    public static Intent newIntent(Context context, UUID questionId) {
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID questionId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_QUESTION_ID);
        return QuestionFragment.newInstance(questionId);
    }
}
