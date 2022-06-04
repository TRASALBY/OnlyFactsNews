package com.example.onlyfacts.dbconnthread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlyfacts.NewsDataAdapter;
import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.*;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    Button btn_test;
    TextView tv_test;
    Handler settingHandler;
    RoomClass database;
    List<NewsDataSet> newsDataSetList;
    ArrayList<NewsDataSet> list = new ArrayList<>();
    Handler insertHandler;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv_test = findViewById(R.id.tv_test);
        tv_test.setText("[더미] 임시 DB 데이터");
        settingHandler = new MainHandler();

        database = RoomClass.getInstance(this);
        linearLayoutManager = new LinearLayoutManager(this);


        Thread dbConnect = new DBConnect(settingHandler);
        //이형식으로 List<NewsDataSet> list = ??;
        dbConnect.start();

/*
        insertHandler = new InsertHandler();
        Thread insertThread = new RoomInsertConn(insertHandler, database, newsDataSet);
        insertThread.start();

        Handler exampleHandler = new ExampleHandler();
        Thread exampleThread = new RoomSelectConn(exampleHandler, database);
        exampleThread.start();
*/


    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            String txt = bundle.getString("data");

            if (txt.equals("")){
                Log.d("connect","connection fail");
            }else{
                try {
                    DBJsonToString jts = new DBJsonToString(txt);
                    newsDataSetList = jts.getDatalist();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //여기다가 newsDataSetList 어댑터에 끼워서 리사이클러뷰 만들면 됨.
                RecyclerView recyclerView = findViewById(R.id.test_rcv);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                NewsDataAdapter newsDataAdapter = new NewsDataAdapter(newsDataSetList);
                recyclerView.setAdapter(newsDataAdapter);
            }
        }
    }

    class InsertHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //삽입 핸들러
            //끝나면
        }
    }

    class ExampleHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            ArrayList<NewsDataSet> newslist = bundle.getParcelableArrayList("data");
                    //여기다가 newslist를 어댑터에 끼워서 리사이클러뷰 만들면 됨?

        }
    }
}