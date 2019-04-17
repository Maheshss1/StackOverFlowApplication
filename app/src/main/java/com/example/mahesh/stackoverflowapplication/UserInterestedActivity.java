package com.example.mahesh.stackoverflowapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mahesh.stackoverflowapplication.adapters.TagsSelectedAdapter;
import com.example.mahesh.stackoverflowapplication.adapters.UserInterestAdapter;
import com.example.mahesh.stackoverflowapplication.model.ListOfTags;
import com.example.mahesh.stackoverflowapplication.model.Tags;
import com.example.mahesh.stackoverflowapplication.support.Common;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowAPI;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowRepository;
import com.example.mahesh.stackoverflowapplication.viewModels.TagsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInterestedActivity extends AppCompatActivity {

    private static final String TAG = "UserInterestedActivity";
    List<Tags> listTags;
    UserInterestAdapter adapter;
    final List<Tags> selectedTagsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interested);

        initRecyclerView();

    }

    private void initRecyclerView() {
        final TagsSelectedAdapter tagsSelectedAdapter = new TagsSelectedAdapter(selectedTagsList);
        StackOverFlowRepository stackOverFlowRepository = new StackOverFlowRepository(this);
        stackOverFlowRepository.getAllTags().observe(this, new Observer<List<Tags>>() {
            @Override
            public void onChanged(@Nullable List<Tags> tags) {
                if (tags.size()!=0){
                    startActivity(new Intent(UserInterestedActivity.this, QuestionListActivity.class));
                    finish();
                }
            }
        });

        final RecyclerView unselectedTagsRecycler = findViewById(R.id.unselected_tags_recycler);
        unselectedTagsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.divider_line_green));
        unselectedTagsRecycler.addItemDecoration(dividerItemDecoration);
        StackOverFlowAPI stackOverFlowAPI = Common.getStackOverFlowAPI(Common.STACKEXCHANGEURL);

        stackOverFlowAPI.getPopularTags().enqueue(

                new Callback<ListOfTags>() {
                    @Override
                    public void onResponse(Call<ListOfTags> call, Response<ListOfTags> response) {
                        Log.d(TAG, "onResponse: " + call.request().toString());
                        listTags = response.body().getItems();
                        adapter = new UserInterestAdapter(listTags);
                        unselectedTagsRecycler.setAdapter(adapter);
                        listTags = response.body().getItems();
                        adapterAndListeners(tagsSelectedAdapter, selectedTagsList);
                    }

                    @Override
                    public void onFailure(Call<ListOfTags> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


        unselectedTagsRecycler.setAdapter(adapter);


        RecyclerView selectedTagsRecycler = findViewById(R.id.selected_tags_recycler);
        selectedTagsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        selectedTagsRecycler.setAdapter(tagsSelectedAdapter);
    }

    private void adapterAndListeners(final TagsSelectedAdapter tagsSelectedAdapter, final List<Tags> selectedTagsList) {
        adapter.setListner(new UserInterestAdapter.ClickListner() {
            @Override
            public void listne(Tags tags) {
                selectedTagsList.add(tags);
                tagsSelectedAdapter.notifyDataSetChanged();

            }
        });
        tagsSelectedAdapter.setRemoveListener(new TagsSelectedAdapter.RemoveListener() {
            @Override
            public void removed(Tags tags) {
                listTags.add(tags.getPosition(), tags);
                adapter.notifyItemInserted(tags.getPosition());
            }
        });
    }

    public void submitTags(View view) {
        if (selectedTagsList.size() < 4) {
            Toast.makeText(this, String.format("Please select At least 4 tags (%s more)", 4 - selectedTagsList.size()), Toast.LENGTH_SHORT).show();
        } else {
            TagsViewModel tagsViewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
            tagsViewModel.insert(selectedTagsList);

            Common.sort = selectedTagsList.get(0).getName();
                    startActivity(new Intent(this, QuestionListActivity.class));
            finish();
        }
    }
}
