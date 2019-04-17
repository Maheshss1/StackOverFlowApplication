package com.example.mahesh.stackoverflowapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mahesh.stackoverflowapplication.model.ListOfQuestions;
import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.support.Common;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mahesh.stackoverflowapplication.support.Common.TAB_TITLE_HOT;
import static com.example.mahesh.stackoverflowapplication.support.Common.questionsAdapter;
import static com.example.mahesh.stackoverflowapplication.support.Common.sort;


public class HotQuestionsFragment extends Fragment {


    public static final String TAG = "sfdsfsdfsdf";
    private RecyclerView questionsRecyclerView;

    public HotQuestionsFragment() {
        Log.d(TAG, "HotQuestionsFragment: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_hot_questions, container, false);
        questionsRecyclerView = view.findViewById(R.id.questions_recycler_view);
        initRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResumeHot: ");
        questionsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void initRecyclerView(){
        sort = TAB_TITLE_HOT;

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.create();
        StackOverFlowAPI stackOverFlowAPI = Common.getStackOverFlowAPI(Common.STACKEXCHANGEURL);
        stackOverFlowAPI.getListOfQuestions(sort, Common.TAGGED, Common.SITE).enqueue(
                new Callback<ListOfQuestions>() {
                    @Override
                    public void onResponse(Call<ListOfQuestions> call, Response<ListOfQuestions> response) {
                        progressDialog.show();

                        Log.d(TAG, "onResponse: "+call.request().toString());
                        questionsAdapter.setFragment(HotQuestionsFragment.this);
                        questionsAdapter.setQuestionsList(response.body().getItems());
                        questionsRecyclerView.setAdapter(questionsAdapter);
                        questionsAdapter.notifyDataSetChanged();
                        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        Log.d(TAG, "onResponse: "+response.body().getItems().toString());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ListOfQuestions> call, Throwable t) {
                        t.printStackTrace();
                    }
                }
        );
    }

}
