package com.example.mahesh.stackoverflowapplication.adapters;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mahesh.stackoverflowapplication.AnswersOfQuestions;
import com.example.mahesh.stackoverflowapplication.R;
import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.support.Common;

import java.util.List;

public class FavQuestionsAdapter extends RecyclerView.Adapter<FavQuestionsAdapter.ViewHolder> {

    List<Questions> questionsList;
    Fragment fragment;




    class ViewHolder extends RecyclerView.ViewHolder{

        TextView questions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questions = itemView.findViewById(R.id.question_title);
        }
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public void setFragment(Fragment Fragment) {
        this.fragment = Fragment;
    }

    @NonNull
    @Override
    public FavQuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FavQuestionsAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_fragment_fav_questions, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FavQuestionsAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.questions.setText(String.valueOf(i+1)+") "+questionsList.get(i).getTitle());

        viewHolder.questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragment.getActivity(), AnswersOfQuestions.class);
                intent.putExtra(Common.Question_Url, questionsList.get(i).getLink());
                fragment.getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

}

