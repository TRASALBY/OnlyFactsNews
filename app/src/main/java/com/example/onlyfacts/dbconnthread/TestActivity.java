package com.example.onlyfacts.dbconnthread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.*;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    Button btn_test;
    TextView tv_test;
    Handler settingHandler;
    RoomClass database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv_test = findViewById(R.id.tv_test);
        tv_test.setText("abcdefg");
        settingHandler = new DBconnHandler(tv_test);

        database = RoomClass.getInstance(this);

        Thread dbConnect = new DBConnect(settingHandler);
        //이형식으로 List<NewsDataSet> list = ??;
        dbConnect.start();

    }
}