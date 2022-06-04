package com.example.onlyfacts.dbconnthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.Roomtest.RoomClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RoomSelectConn extends Thread{

    Handler connHandler;
    RoomClass database;
    List<NewsDataSet> dataSetList;

    public RoomSelectConn(Handler handler, RoomClass database){
        connHandler = handler;
        this.database = database;
    }

    public void run(){

        try {
            dataSetList = database.roomDao().getAll();
        } catch (Exception ex){
            Log.d("a", ex.getMessage());
        }
        Log.d("Select_Log", "select executed");
        Message message = connHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) dataSetList);
        message.setData(bundle);

        Log.d("Select_Log", "select completed");
        connHandler.sendMessage(message);
    }
}
