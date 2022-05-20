package com.example.onlyfacts.Roomtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onlyfacts.NewsDataSet;

import java.util.List;
@Dao
public interface RoomDao {
    @Query("SELECT * FROM NewsDataSet")
    List<NewsDataSet> getAll();

    @Insert
    void insert(NewsDataSet entity);

    @Delete
    void delete(NewsDataSet entity);

}
