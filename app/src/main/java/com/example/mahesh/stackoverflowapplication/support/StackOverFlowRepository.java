package com.example.mahesh.stackoverflowapplication.support;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.model.Tags;
import com.example.mahesh.stackoverflowapplication.roomDao.FavDao;
import com.example.mahesh.stackoverflowapplication.roomDao.TagsDao;

import java.util.List;

public class StackOverFlowRepository {
    private TagsDao tagsDao;
    private LiveData<List<Tags>> allTags;
    private FavDao favDao;
    private LiveData<List<Questions>> getFavourites;

    public StackOverFlowRepository(Context context) {
        StackOverFlowDatabase stackOverFlowDatabase = StackOverFlowDatabase.getInstance(context);
        this.tagsDao = stackOverFlowDatabase.tagsDao();
        this.favDao = stackOverFlowDatabase.favDao();
        allTags = tagsDao.getTags();
        getFavourites = favDao.getFavList();
    }

    public void insert(List<Tags> tags) {
        new InsertTagAsync(tagsDao).execute(tags);
    }

    public LiveData<List<Tags>> getAllTags() {
        return allTags;
    }

    public void insertFav(Questions questions) {
        new InsertFavAsync(favDao).execute(questions);
    }

    public void deleteFav(Questions questions) {
        new DeleteFavAsync(favDao).execute(questions);
    }

    public LiveData<List<Questions>> getGetFavourites() {
        return getFavourites;
    }

    private static class InsertTagAsync extends AsyncTask<List<Tags>, Void, Void> {
        private TagsDao tagsDao;

        public InsertTagAsync(TagsDao tagsDao) {
            this.tagsDao = tagsDao;
        }


        @Override
        protected Void doInBackground(List<Tags>... lists) {
            try {
                tagsDao.insert(lists[0]);
            }catch (Exception e){

            }

            return null;
        }
    }

    private static class InsertFavAsync extends AsyncTask<Questions, Void, Void> {
        private static final String TAG = "sdfsdklcvx";
        private FavDao favDao;

        public InsertFavAsync(FavDao favDao) {

            this.favDao = favDao;
        }

        @Override
        protected Void doInBackground(Questions... lists) {
            try {
                favDao.insertFav(lists[0]);
            }catch (Exception e){

            }
            return null;
        }
    }

    private static class DeleteFavAsync extends AsyncTask<Questions, Void, Void> {

        private FavDao favDao;

        public DeleteFavAsync(FavDao favDao) {
            this.favDao = favDao;
        }

        @Override
        protected Void doInBackground(Questions... questions) {
            favDao.deleteFav(questions[0]);
            return null;
        }
    }
}
