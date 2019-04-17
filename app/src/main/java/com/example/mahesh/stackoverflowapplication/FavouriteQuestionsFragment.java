package com.example.mahesh.stackoverflowapplication;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahesh.stackoverflowapplication.adapters.FavQuestionsAdapter;
import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.viewModels.FavViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteQuestionsFragment extends Fragment {


    public FavouriteQuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favourite_questions, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.questions_recycler_view);
        initRecycler(recyclerView);

        return view;
    }

    private void initRecycler(final RecyclerView recyclerView){
        FavViewModel favViewModel = ViewModelProviders.of(FavouriteQuestionsFragment.this).get(FavViewModel.class);
        favViewModel.getGetAllFavourites().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable List<Questions> questions) {
                FavQuestionsAdapter adapter = new FavQuestionsAdapter();
                adapter.setFragment(FavouriteQuestionsFragment.this);
                adapter.setQuestionsList(questions);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

    }
}
