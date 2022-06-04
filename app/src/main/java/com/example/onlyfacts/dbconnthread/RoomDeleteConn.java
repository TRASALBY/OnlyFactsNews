package com.example.onlyfacts.dbconnthread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.Roomtest.RoomClass;

public class RoomDeleteConn extends Thread{

    Handler connHandler;
    RoomClass database;
    NewsDataSet dataset;

    public RoomDeleteConn(Handler handler, RoomClass database, NewsDataSet dataset){
        connHandler = handler;
        this.database = database;
        this.dataset = dataset;
    }

    public void run(){
        try {
            database.roomDao().delete(dataset);
        } catch (Exception ex){
            Log.d("a", ex.getMessage());
        }
        Message message = connHandler.obtainMessage();

        connHandler.sendMessage(message);
    }
}
