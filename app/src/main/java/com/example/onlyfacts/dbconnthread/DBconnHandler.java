package com.example.onlyfacts.dbconnthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONException;


public class DBconnHandler extends Handler {
    TextView target;

    public DBconnHandler (TextView tv){
        target = tv;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);

        Bundle bundle = msg.getData();
        String txt = bundle.getString("data");

        try {
            DBJsonToString jts = new DBJsonToString(txt);
            txt = jts.getTxt();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        target.setText(txt);
    }
}
