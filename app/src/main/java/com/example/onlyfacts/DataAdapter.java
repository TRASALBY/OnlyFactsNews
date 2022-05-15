package com.example.onlyfacts;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CustomViewHolder> {
    String TAG = "RecyclerViewAdapter";
    private ArrayList<Data> arrayList;
    Context context;

    public DataAdapter(ArrayList<Data> list) {
        this.arrayList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.CustomViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder");

        holder.tv_reliability.setText(arrayList.get(holder.getAdapterPosition()).getTv_reliabilitiy());
        holder.iv_profile.setImageResource(arrayList.get(holder.getAdapterPosition()).getIv_profile());
        holder.tv_press.setText(arrayList.get(holder.getAdapterPosition()).getTv_press());
        holder.tv_time.setText(arrayList.get(holder.getAdapterPosition()).getTv_time());
        holder.tv_title.setText(arrayList.get(holder.getAdapterPosition()).getTv_title());

        holder.itemView.setTag(holder.getAdapterPosition()) ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewsActivity.class);
                //데이터 넣을거 intent.putExtra(data data data data);
                view.getContext().startActivity(intent);

                //String curName = holder.tv_title.getText().toString();
                //Toast.makeText(view.getContext(), "클릭 : " + curName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_reliability;
        protected ImageView iv_profile;
        protected TextView tv_press;
        protected TextView tv_time;
        protected TextView tv_title;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_reliability = (TextView) itemView.findViewById(R.id.tv_reliability);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.iv_profile);
            this.tv_press = (TextView) itemView.findViewById(R.id.tv_press);
            this.tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);

        }

    }
}
