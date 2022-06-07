package com.example.onlyfacts.news_category;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfacts.adapter.NewsDataAdapter;
import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.RoomClass;
import com.example.onlyfacts.dbconnthread.DBConnect;
import com.example.onlyfacts.dbconnthread.DBJsonToString;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FragPolitics extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<NewsDataSet> newsDataSetList;
    Handler settingHandler;
    RoomClass database;
    public static FragPolitics newInstance() {
        FragPolitics fragment = new FragPolitics();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_politics, container, false);

        recyclerView = view.findViewById(R.id.newslist_politics);

        settingHandler = new MainHandler();
        database = RoomClass.getInstance(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        Thread dbConnect = new DBConnect(settingHandler);
        dbConnect.start();

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
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

                List<NewsDataSet>  field_datas = new ArrayList<NewsDataSet>();

                for(int i = 0; i < newsDataSetList.size(); i++){
                    NewsDataSet set = newsDataSetList.get(i);
                    if(set.getField().equals("1")){ //정치 : 1
                        field_datas.add(set);
                    }
                }

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                NewsDataAdapter newsDataAdapter = new NewsDataAdapter(field_datas);
                recyclerView.setAdapter(newsDataAdapter);

            }
        }
    }

}