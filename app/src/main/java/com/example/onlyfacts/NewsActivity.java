package com.example.onlyfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onlyfacts.dbconnthread.DBConnect;
import com.example.onlyfacts.dbconnthread.DBJsonToString;
import com.example.onlyfacts.dbconnthread.TestActivity;

import org.json.JSONException;

import java.util.List;

public class NewsActivity extends AppCompatActivity {
    TextView tv_title;
    TextView tv_body;
    ImageView img;
    TextView tv_field;
    TextView tv_time;
    TextView tv_sourcelink;
    TextView tv_reliability;
    NewsDataSet newsDataSet;

    String sourcelink_news;
    String realField = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Bundle recieve = getIntent().getExtras();
        newsDataSet = recieve.getParcelable("selected");

        tv_title = findViewById(R.id.title_news);
        tv_body = findViewById(R.id.body_news);
        img = findViewById(R.id.img_news);
        tv_field = findViewById(R.id.field_news);
        tv_time = findViewById(R.id.time_news);
        tv_reliability = findViewById(R.id.reliability_news);

        String title_news = newsDataSet.getTitle();
        String body_news = newsDataSet.getBody();
        String imglink_news = newsDataSet.getImglink();
        sourcelink_news = newsDataSet.getSourcelink();
        String field_news = newsDataSet.getField();
        String time_news = newsDataSet.getTime();
        String reliability_news = newsDataSet.getReliability();

        field_change(field_news);   //뉴스 분야 정수형->문자형으로 변경

        tv_title.setText(title_news);
        tv_body.setText(body_news);
        tv_field.setText(realField);
        tv_time.setText(time_news);
        if (imglink_news != null) {
            Glide.with(this).load(imglink_news).into(img);
        }
        else {
            img.setImageResource(R.drawable.img_dummy);
        }
        tv_reliability.setText(reliability_news);

        //****원문 보기 (뉴스 웹페이지 바로가기)****
        ImageView btn_link = findViewById(R.id.sourcelink_news);
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sourcelink_news));
                startActivity(intent);
            }
        });

        btn_close();


    }

    //뉴스 field 정수형 -> 문자형 변환 메소드
    private void field_change(String field_news) {
        int field = Integer.parseInt(field_news);
        if(field == 1) {
            realField = "정치";
        }
        else if (field == 2) {
            realField = "경제";
        }
        else if (field == 3) {
            realField = "사회";
        }
        else if (field == 4) {
            realField = "생활/문화";
        }
        else if (field == 5) {
            realField = "IT/과학";
        }
        else if (field == 6) {
            realField = "국제";
        }
        else {
            realField = "분야";
        }
    }

    //뉴스 액티비티 종료버튼
    private void btn_close() {
        ImageButton btn_close = findViewById(R.id.btn_close_news);          //종료버튼
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //북마크 버튼 메소드
    private void btn_marks() {}

    //뉴스 페이지 다운 메소드
    private void pg_down() {}

    //뉴스 페이지 업 메소드
    private void pg_up() {}

    //뉴스 원문링크 메소드



}