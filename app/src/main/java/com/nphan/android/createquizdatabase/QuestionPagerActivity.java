package com.nphan.android.createquizdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class QuestionPagerActivity extends AppCompatActivity {
    private static final String EXTRA_QUESTION_ID = "com.nphan.android.createquizdatabase.question_id";

    private ViewPager mViewPager;
    private List<Question> mQuestions;

    public static Intent newIntent(Context context, UUID questionId) {
        Intent intent = new Intent(context, QuestionPagerActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID questionId = (UUID) getIntent().getSerializableExtra(EXTRA_QUESTION_ID);

        mViewPager = findViewById(R.id.question_view_pager);

        mQuestions = QuestionBank.get(this).getQuestions();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Question question = mQuestions.get(position);
                return QuestionFragment.newInstance(question.getId());
            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });

        for (int i = 0; i < mQuestions.size(); i++) {
            if (mQuestions.get(i).getId().equals(questionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
