package com.example.mahesh.stackoverflowapplication.roomDao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.mahesh.stackoverflowapplication.model.Questions;

import java.util.List;

@Dao
public interface FavDao {

    @Insert
    void insertFav(Questions questions);

    @Delete
    void deleteFav(Questions questions);

    @Query("SELECT * FROM Questions")
    LiveData<List<Questions>> getFavList();
}
