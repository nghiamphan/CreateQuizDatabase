package com.nphan.android.createquizdatabase;

import android.support.v4.app.Fragment;

public class QuestionListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new QuestionListFragment();
    }
}
