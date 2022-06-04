package com.example.onlyfacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfacts.Roomtest.RoomClass;
import com.example.onlyfacts.dbconnthread.DBJsonToString;
import com.example.onlyfacts.dbconnthread.RoomInsertConn;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder> {
    private List<NewsDataSet> list = new ArrayList<>();
    private Context context;
    RoomClass roomDatabase;

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
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String imageURL = list.get(position).getImglink();
        if (imageURL != null) { //이미지 있을때 해당 이미지 적용
            Glide.with(holder.itemView.getContext()).load(imageURL).into(holder.img);
        }
        else {  //이미지 없을때 더미이미지 적용
            holder.img.setImageResource(R.drawable.img_dummy);
        }
        NewsDataSet item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTime());
        holder.reliability.setText(item.getReliability());

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
                Thread thread = new RoomInsertConn(new InsertHandler(), roomDatabase, item);
                thread.start();
            }
        });
    }

    class InsertHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("Insert_Log", "insert message handled");
        }
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
        Button bookmark;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img_rcv);
            this.title = itemView.findViewById(R.id.title_rcv);
            this.time = itemView.findViewById(R.id.time_rcv);
            this.reliability = itemView.findViewById(R.id.reliability_rcv);
            this.bookmark = itemView.findViewById(R.id.bookmark_rcv);

        }
    }
}
