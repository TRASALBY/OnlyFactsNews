package com.example.onlyfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    TextView tv_title;
    TextView tv_body;
    ImageView img;
    TextView tv_field;
    TextView tv_time;
    TextView tv_sourcelink;
    TextView tv_reliability;
    TextView current_pg;
    NewsDataSet newsDataSet;
    ImageView pg_down, pg_up;

    String sourcelink_news;
    String realField = "";
    String[] txtlist;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        FragmentManager  fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle recieve = getIntent().getExtras();
        newsDataSet = recieve.getParcelable("selected");

        tv_title = findViewById(R.id.title_news);
        tv_body = findViewById(R.id.body_news);
        img = findViewById(R.id.img_news);
        tv_field = findViewById(R.id.field_news);
        tv_time = findViewById(R.id.time_news);
        tv_reliability = findViewById(R.id.reliability_news);
        pg_down = findViewById(R.id.page_down);
        pg_up = findViewById(R.id.page_up);
        current_pg = findViewById(R.id.current_pg_num);

        /*
        String bodytext = newsDataSet.getBody();
        String[] txtlist = bodytext.split("#*"); //배열길이가 3~5줄인 String 배열


        List<Fragment> fraglist = new ArrayList<Fragment>();
        for (int i = 0; i < txtlist.length; i++){
            Fragment frag = new FragmentNews(txtlist[i]);
            fraglist.add(frag);
        }           // 프래그래그먼트 리스트 완성

        //뷰페이저어뎁터(fraglist)
*/



        String title_news = newsDataSet.getTitle();
        String body_news = newsDataSet.getBody();
        String imglink_news = newsDataSet.getImglink();
        sourcelink_news = newsDataSet.getSourcelink();
        String field_news = newsDataSet.getField();
        String time_news = newsDataSet.getTime();
        String reliability_news = newsDataSet.getReliability();

        field_change(field_news);   //뉴스 분야 정수형->문자형으로 변경
        String test_str ="qweqweqweqw#!qerqrqrqerqrq";
        String[] test_strlist = test_str.split("#!");
        txtlist = body_news.split("#!");

        tv_title.setText(title_news);
        settingTxtview();
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
        pg_up.setOnClickListener(v -> {
            Log.d("btn_clicked", "clicked_up");
            if (count < txtlist.length-1){
                count++;
                settingTxtview();
            }
        });

        pg_down.setOnClickListener(v -> {
            Log.d("btn_clicked", "clicked_down");
            if(count > 0){
                count--;
                settingTxtview();
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


    private void settingTxtview(){
        tv_body.setText(txtlist[count]);
        String zzz = Integer.toString(count + 1) + " / " + Integer.toString(txtlist.length);
        current_pg.setText(zzz);
        Log.d("settingTxtView", "?-_^^...;;");
    }

}