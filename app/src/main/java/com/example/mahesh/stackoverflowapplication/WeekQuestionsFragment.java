package com.example.mahesh.stackoverflowapplication;



import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahesh.stackoverflowapplication.model.ListOfQuestions;
import com.example.mahesh.stackoverflowapplication.support.Common;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mahesh.stackoverflowapplication.support.Common.questionsAdapter;
import static com.example.mahesh.stackoverflowapplication.support.Common.sort;



/**
 * A simple {@link Fragment} subclass.
 */
public class WeekQuestionsFragment extends Fragment {

    private static final String TAG = "WeekFragment";
    private RecyclerView questionsRecyclerView;

    public WeekQuestionsFragment() {
        Log.d(TAG, "WeekQuestionsFragment: ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_week_questions, container, false);
        questionsRecyclerView = view.findViewById(R.id.questions_recycler_view);

        initRecyclerView();


        return view;
    }

    private void initRecyclerView() {
        sort = Common.TAB_TITLE_WEEK;

        StackOverFlowAPI stackOverFlowAPI = Common.getStackOverFlowAPI(Common.STACKEXCHANGEURL);
        stackOverFlowAPI.getListOfQuestions(sort, Common.TAGGED, Common.SITE).enqueue(
                new Callback<ListOfQuestions>() {
                    @Override
                    public void onResponse(Call<ListOfQuestions> call, Response<ListOfQuestions> response) {
                        Log.d(TAG, "onResponse: "+call.request().toString());
                        questionsAdapter.setFragment(WeekQuestionsFragment.this);
                        questionsAdapter.setQuestionsList(response.body().getItems());
                        questionsRecyclerView.setAdapter(questionsAdapter);
                        questionsAdapter.notifyDataSetChanged();
                        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        Log.d(TAG, "onResponse: "+response.body().getItems().toString());
                    }

                    @Override
                    public void onFailure(Call<ListOfQuestions> call, Throwable t) {
                        t.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
