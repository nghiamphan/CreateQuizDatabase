package com.nphan.android.createquizdatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.UUID;

public class QuestionFragment extends Fragment {

    private static final String ARG_QUESTION_ID = "question_id";

    private Question mQuestion;
    private EditText mQuestionField;
    private EditText mOptionAField;
    private EditText mOptionBField;
    private EditText mOptionCField;
    private EditText mOptionDField;
    private RadioGroup mRadioGroup;
    private TextView mTextView;

    public static QuestionFragment newInstance(UUID questionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION_ID, questionId);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        Log.i("HIHQ", "newInstance");
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HIHQ", "onCreate");
        UUID questionId = (UUID) getArguments().getSerializable(ARG_QUESTION_ID);
        mQuestion = QuestionBank.get(getActivity()).getQuestion(questionId);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionField = v.findViewById(R.id.question_text);
        mQuestionField.setText(mQuestion.getQuestionText());
        updateModelFromTextField(mQuestionField, R.id.question_text);

        mOptionAField = v.findViewById(R.id.option_A_text);
        mOptionAField.setText(mQuestion.getOptionA());
        updateModelFromTextField(mOptionAField, R.id.option_A_text);

        mOptionBField = v.findViewById(R.id.option_B_text);
        mOptionBField.setText(mQuestion.getOptionB());
        updateModelFromTextField(mOptionBField, R.id.option_B_text);

        mOptionCField = v.findViewById(R.id.option_C_text);
        mOptionCField.setText(mQuestion.getOptionC());
        updateModelFromTextField(mOptionCField, R.id.option_C_text);

        mOptionDField = v.findViewById(R.id.option_D_text);
        mOptionDField.setText(mQuestion.getOptionD());
        updateModelFromTextField(mOptionDField, R.id.option_D_text);

        mRadioGroup = v.findViewById(R.id.radio_group);
        RadioButton rb = (RadioButton) mRadioGroup.getChildAt(mQuestion.getAnswer());
        rb.setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.option_A_radio_button:
                        mQuestion.setAnswer(0);
                        break;
                    case R.id.option_B_radio_button:
                        mQuestion.setAnswer(1);
                        break;
                    case R.id.option_C_radio_button:
                        mQuestion.setAnswer(2);
                        break;
                    case R.id.option_D_radio_button:
                        mQuestion.setAnswer(3);
                        break;
                }
                mTextView.setText(mQuestion.printQuestion());
            }
        });

        mTextView = v.findViewById(R.id.test);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        QuestionBank.get(getActivity()).updateQuestion(mQuestion);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_question, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_question:
                QuestionBank.get(getActivity()).deleteQuestion(mQuestion);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateModelFromTextField(final EditText editText, final int viewId) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (viewId) {
                    case R.id.question_text:
                        mQuestion.setQuestionText(s.toString());
                        break;
                    case R.id.option_A_text:
                        mQuestion.setOptionA(s.toString());
                        break;
                    case R.id.option_B_text:
                        mQuestion.setOptionB(s.toString());
                        break;
                    case R.id.option_C_text:
                        mQuestion.setOptionC(s.toString());
                        break;
                    case R.id.option_D_text:
                        mQuestion.setOptionD(s.toString());
                        break;
                }
                mTextView.setText(mQuestion.printQuestion());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
