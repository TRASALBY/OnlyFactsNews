package com.example.onlyfacts.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfacts.NewsActivity;
import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.RoomClass;
import com.example.onlyfacts.dbconnthread.RoomInsertDeleteConn;
import com.example.onlyfacts.dbconnthread.RoomSelectConn;

import java.util.ArrayList;
import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder> {
    private List<NewsDataSet> list = new ArrayList<>();
    private Context context;
    RoomClass roomDatabase;
    List<NewsDataSet> bookmarklist;     //

    public NewsDataAdapter(List<NewsDataSet> data) {
        this.list = data;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_news, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        roomDatabase = RoomClass.getInstance(context);

        Thread thread = new RoomSelectConn(new SelectHandler(), roomDatabase);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String imageURL = list.get(position).getImglink();
        if (imageURL.equals("")) { //이미지 있을때 해당 이미지 적용
            holder.img.setImageResource(R.drawable.img_dummy);
        }
        else {  //이미지 없을때 더미이미지 적용
            Glide.with(holder.itemView.getContext()).load(imageURL).into(holder.img);
        }
        NewsDataSet item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTime());
        holder.reliability.setText(item.getReliability());
        int reliability = Integer.parseInt(list.get(position).getReliability());
        if(reliability >= 80) { //초록
            holder.color.setBackgroundColor(Color.argb(255, 76, 175, 80));
        } else if (reliability >= 50 & reliability < 80) {  //노랑
            holder.color.setBackgroundColor(Color.argb(255, 255, 240, 000));
        } else if (reliability >= 40 & reliability < 80) {  // 주황
            holder.color.setBackgroundColor(Color.argb(255, 251, 119, 61));
        }else if (reliability >= 0 & reliability < 40) {   // 빨강
            holder.color.setBackgroundColor(Color.argb(255, 255, 0, 0));
        } else {
            holder.color.setBackgroundColor(Color.argb(255, 251, 119, 61));
        }
        Boolean bookmarked = false;
        if (bookmarklist != null) {
            String itemID = item.getId();
            for (int i = 0; i < bookmarklist.size(); i++) {
                NewsDataSet forSet = bookmarklist.get(i);
                if (forSet.getId().equals(itemID)) {
                    bookmarked = true;
                    break;
                }
            }
        }
        holder.bookmark.setOnCheckedChangeListener(null);
        holder.bookmark.setChecked(bookmarked);
        holder.bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.bookmark.setChecked(true);
                }
                else {
                    holder.bookmark.setChecked(false);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("selected", item);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
        holder.bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Thread thread = new RoomInsertDeleteConn(new InsertDeleteHandler(), roomDatabase, item);
                    thread.start();
                    notifyDataSetChanged();
            }
        });
    }

    class InsertDeleteHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("I/D_Log", "insert/delete message handled");
        }
    }

    class SelectHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("Adapter_Loger", "select message handled");

            Bundle bundle = msg.getData();
            bookmarklist = bundle.getParcelableArrayList("data");
        }
    }

    public void updateRecyclerview() {
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView body;
        ImageView img;
        TextView time;
        TextView field;
        TextView sourcelink;
        TextView reliability;
        CheckBox bookmark;
        LinearLayout color;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img_rcv);
            this.title = itemView.findViewById(R.id.title_rcv);
            this.time = itemView.findViewById(R.id.time_rcv);
            this.reliability = itemView.findViewById(R.id.reliability_rcv);
            this.bookmark = itemView.findViewById(R.id.bookmark_rcv);
            this.color = itemView.findViewById(R.id.color_reliability);

        }
    }
}
