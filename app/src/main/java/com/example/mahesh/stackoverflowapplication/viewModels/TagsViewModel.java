package com.example.mahesh.stackoverflowapplication.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.nfc.Tag;
import android.support.annotation.NonNull;

import com.example.mahesh.stackoverflowapplication.model.Tags;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowRepository;

import java.util.List;

public class TagsViewModel extends AndroidViewModel {
    private StackOverFlowRepository repository;
    private LiveData<List<Tags>> listLiveData;

    public TagsViewModel(@NonNull Application application) {
        super(application);

        repository = new StackOverFlowRepository(application);
        listLiveData = repository.getAllTags();
    }

    public void insert(List<Tags> tags){
         repository.insert(tags);
    }

    public LiveData<List<Tags>> getListLiveData() {
        return listLiveData;
    }
}
