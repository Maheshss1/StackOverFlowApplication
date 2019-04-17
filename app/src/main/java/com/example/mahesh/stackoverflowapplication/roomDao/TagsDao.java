package com.example.mahesh.stackoverflowapplication.roomDao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.mahesh.stackoverflowapplication.model.Tags;

import java.util.List;

@Dao
public interface TagsDao {

    @Insert
    void insert(List<Tags> tags);

    @Query("SELECT * FROM TAGS_TABLE")
    LiveData<List<Tags>> getTags();

}
