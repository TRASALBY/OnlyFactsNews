package com.example.onlyfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.onlyfacts.Roomtest.RoomClass;
import com.example.onlyfacts.dbconnthread.RoomSelectConn;

import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<NewsDataSet> bookmarkDataList;
    RoomClass roomDatabase;
    LinearLayoutManager linearLayoutManager;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        roomDatabase = RoomClass.getInstance(this);
        linearLayoutManager = new LinearLayoutManager(this);
        handler = new SelectHandler();
        recyclerView = findViewById(R.id.selected_bookmarkList);

        Thread thread = new RoomSelectConn(handler, roomDatabase);
        thread.start();
    }

    class SelectHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("Select_Log", "select message handled");

            Bundle bundle = msg.getData();
            bookmarkDataList = bundle.getParcelableArrayList("data");


            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            NewsDataAdapter newsDataAdapter = new NewsDataAdapter(bookmarkDataList);
            recyclerView.setAdapter(newsDataAdapter);
        }
    }
}