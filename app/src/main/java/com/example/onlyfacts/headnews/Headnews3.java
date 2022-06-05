package com.example.onlyfacts.headnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onlyfacts.NewsActivity;
import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.RoomClass;
import com.example.onlyfacts.dbconnthread.DBConnect;
import com.example.onlyfacts.dbconnthread.DBJsonToString;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Headnews3 extends Fragment {
    Handler settingHandler;
    RoomClass database;
    List<NewsDataSet> newsDataSetList;
    LinearLayout headNews3;
    TextView title, field, time;
    ImageView img;
    Activity activity;
    public Headnews3() {    }

    public static Headnews3 newInstance() {
        Headnews3 fragment = new Headnews3();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.headnews3,container,false);

        activity = getActivity();
        headNews3 = rootView.findViewById(R.id.box_mainNews3);
        title = rootView.findViewById(R.id.title_head3);
        time = rootView.findViewById(R.id.time_head3);
        img = rootView.findViewById(R.id.img_head3);
        field = rootView.findViewById(R.id.field_head3);

        settingHandler = new MainHandler();
        database = RoomClass.getInstance(getActivity());
        Thread dbConnect = new DBConnect(settingHandler);
        dbConnect.start();

        return rootView;
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
                //
                List<NewsDataSet>  politics_random = new ArrayList<NewsDataSet>();

                /*
                for(int i = 0; i < newsDataSetList.size(); i++){
                    NewsDataSet set = newsDataSetList.get(i);
                    if(set.getField().equals("1")){ //정치 1
                        politics_random.add(set);
                    }
                }
                */
                newsDataSetList.size();
                Random random = new Random();
                int randomNum = random.nextInt(newsDataSetList.size());
                NewsDataSet set = newsDataSetList.get(randomNum);
                politics_random.add(set);

                title.setText(set.getTitle());
                time.setText(set.getTime());
                if (set.getImglink() != null) { //이미지 있을때 해당 이미지 적용
                    if (set.getImglink().equals("")){
                        img.setImageResource(R.drawable.img_dummy);
                    }
                    else{
                        Glide.with(activity).load(set.getImglink()).into(img);
                    }
                }
                else {  //이미지 없을때 더미이미지 적용
                    img.setImageResource(R.drawable.img_dummy);
                }
                if (set.getField().equals("1")) {
                    field.setText("정치");
                } else if (set.getField().equals("2")) {
                    field.setText("경제");
                }else if (set.getField().equals("3")) {
                    field.setText("사회");
                }else if (set.getField().equals("4")) {
                    field.setText("생활/문화");
                }else if (set.getField().equals("5")) {
                    field.setText("IT/과학");
                }

                headNews3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), NewsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("selected", set);
                        intent.putExtras(bundle);
                        v.getContext().startActivity(intent);
                    }
                });

            }
        }
    }
}