package com.example.mahesh.stackoverflowapplication.adapters;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahesh.stackoverflowapplication.AnswersOfQuestions;
import com.example.mahesh.stackoverflowapplication.QuestionListActivity;
import com.example.mahesh.stackoverflowapplication.R;
import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.support.Common;
import com.example.mahesh.stackoverflowapplication.viewModels.FavViewModel;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private static final String TAG = "jklfjsdlkjfsdlk";
    List<Questions> questionsList;
    Fragment fragment;



    class ViewHolder extends RecyclerView.ViewHolder{

        TextView questions;
        ImageButton fav;
        ImageButton share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questions = itemView.findViewById(R.id.question_title);
            fav = itemView.findViewById(R.id.fav);
            share = itemView.findViewById(R.id.share);
        }
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_fragment_questions, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.questions.setText(questionsList.get(i).getTitle());
        viewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.fav.setBackground(fragment.getContext().getDrawable(R.drawable.ic_favorite_40dp));
                FavViewModel favViewModel = ViewModelProviders.of(fragment).get(FavViewModel.class);
                favViewModel.insertFav(questionsList.get(i));

                Toast.makeText(fragment.getContext(), "Question added", Toast.LENGTH_SHORT).show();

            }
        });

        viewHolder.questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragment.getActivity(), AnswersOfQuestions.class);
                intent.putExtra(Common.Question_Url, questionsList.get(i).getLink());
                fragment.getActivity().startActivity(intent);
            }
        });

        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, questionsList.get(i).getTitle());
                intent.putExtra(Intent.EXTRA_TEXT, questionsList.get(i).getLink());
                fragment.getActivity().startActivity(Intent.createChooser(intent, "Share with"));
            }
        });

    }

    public void clear(){
        if (questionsList!=null)
        questionsList.clear();
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

}
