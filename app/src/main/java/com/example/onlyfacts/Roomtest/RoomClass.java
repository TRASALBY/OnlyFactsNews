package com.example.onlyfacts.Roomtest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.onlyfacts.NewsDataSet;

import java.util.List;

@Database(entities = NewsDataSet.class, version = 1)
public abstract class RoomClass extends RoomDatabase{
    public static RoomClass INSTANCE = null;
    public abstract com.example.onlyfacts.Roomtest.RoomDao roomDao();

    public static RoomClass getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    RoomClass.class, "Roomclass.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
