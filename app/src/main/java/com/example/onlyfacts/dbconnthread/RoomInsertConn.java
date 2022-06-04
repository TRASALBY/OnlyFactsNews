package com.example.onlyfacts.dbconnthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.Roomtest.RoomClass;

public class RoomInsertConn extends Thread{

    Handler connHandler;
    RoomClass database;
    NewsDataSet dataset;

    public RoomInsertConn(Handler handler, RoomClass database, NewsDataSet dataset){
        connHandler = handler;
        this.database = database;
        this.dataset = dataset;
    }

    public void run(){
        try {
            database.roomDao().insert(dataset);
            Log.d("Insert_Log", "insert executed");
        } catch (Exception ex){
            Log.d("a", ex.getMessage());
        }
        Message message = connHandler.obtainMessage();

        Log.d("Insert_Log", "insert completed");
        connHandler.sendMessage(message);
    }
}
