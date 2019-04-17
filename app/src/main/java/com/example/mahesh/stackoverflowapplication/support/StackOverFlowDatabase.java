package com.example.mahesh.stackoverflowapplication.support;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.model.Tags;
import com.example.mahesh.stackoverflowapplication.roomDao.FavDao;
import com.example.mahesh.stackoverflowapplication.roomDao.TagsDao;

@Database(entities = {Tags.class, Questions.class}, version = 1)
public abstract class StackOverFlowDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "stackoverflow_database";
    private static StackOverFlowDatabase instance;

        public abstract TagsDao tagsDao();
        public abstract FavDao favDao();

    public static StackOverFlowDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context, StackOverFlowDatabase.class, DATABASE_NAME)
                    .build();
        }
        return instance;
    }

}
