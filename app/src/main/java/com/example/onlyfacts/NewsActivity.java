package com.example.onlyfacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        btn_close();
    }

    private void btn_close() {
        ImageButton btn_close = findViewById(R.id.btn_close_news);          //종료버튼
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void btn_marks() {}
    private void pg_down() {}
    private void pg_up() {}
    private void btn_link() {}
}