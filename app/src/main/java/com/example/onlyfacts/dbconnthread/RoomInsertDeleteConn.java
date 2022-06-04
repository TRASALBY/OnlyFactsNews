package com.example.onlyfacts.dbconnthread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.Roomtest.RoomClass;

public class RoomInsertDeleteConn extends Thread{

    Handler connHandler;
    RoomClass database;
    NewsDataSet dataset;

    public RoomInsertDeleteConn(Handler handler, RoomClass database, NewsDataSet dataset){
        connHandler = handler;
        this.database = database;
        this.dataset = dataset;
    }

    public void run(){
        try {
            NewsDataSet set = database.roomDao().getEntity(dataset.getId());

            if (set != null){
                database.roomDao().delete(dataset);
                Log.d("Delete_Log", "delete executed");
            }else{
                database.roomDao().insert(dataset);
                Log.d("Insert_Log", "insert executed");
            }
        } catch (Exception ex){
            Log.d("a", ex.getMessage());
        }
        Message message = connHandler.obtainMessage();

        Log.d("I/D_Log", "insert/delete completed");
        connHandler.sendMessage(message);
    }
}
