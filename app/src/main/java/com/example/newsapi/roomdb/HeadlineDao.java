package com.example.newsapi.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newsapi.HeadlineModel;

import java.util.List;

/**
 * interface that allows roomDB to implement actions in the database
 */
@Dao
public interface HeadlineDao {
    @Query("SELECT * FROM Headline ORDER BY ID")
    List<HeadlineModel> loadAllHeadlines();
    @Insert
    void insertHeadline(HeadlineModel headlineModel);
}
