package com.nphan.android.createquizdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionListFragment extends Fragment {

    private RecyclerView mQuestionRecyclerView;
    private QuestionAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        mQuestionRecyclerView = view.findViewById(R.id.question_recycler_view);
        mQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        QuestionBank questionBank = QuestionBank.get(getActivity());
        List<Question> questions = questionBank.getQuestions();

        if (mAdapter == null) {
            mAdapter = new QuestionAdapter(questions);
            mQuestionRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class QuestionHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        private Question mQuestion;

        private TextView mQuestionTextView;

        public QuestionHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_question, parent, false));
            itemView.setOnClickListener(this);

            mQuestionTextView = itemView.findViewById(R.id.question_text);
        }

        public void bind(int position, Question question) {
            mQuestion = question;
            mQuestionTextView.setText(Integer.toString(position) + ". " + mQuestion.getQuestionText());
        }

        @Override
        public void onClick(View v) {
            Intent intent = QuestionActivity.newIntent(getActivity(), mQuestion.getId());
            startActivity(intent);
        }
    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {

        private List<Question> mQuestions;

        public QuestionAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        @NonNull
        @Override
        public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new QuestionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bind(position, question);
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
    }
}
