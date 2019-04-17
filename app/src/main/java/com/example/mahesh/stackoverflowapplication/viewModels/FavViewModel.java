package com.example.mahesh.stackoverflowapplication.viewModels;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowRepository;

import java.util.List;

public class FavViewModel extends AndroidViewModel {

    private StackOverFlowRepository repository;
    private LiveData<List<Questions>> getAllFavourites;

    public FavViewModel(@NonNull Application application) {
        super(application);

        repository = new StackOverFlowRepository(application);
        getAllFavourites = repository.getGetFavourites();

    }

    public void insertFav(Questions questions) {
        repository.insertFav(questions);
    }

    public void deleteFav(Questions questions) {
        repository.deleteFav(questions);
    }

    public LiveData<List<Questions>> getGetAllFavourites() {
        return getAllFavourites;
    }
}
