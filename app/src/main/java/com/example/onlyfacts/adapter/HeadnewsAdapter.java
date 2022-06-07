package com.example.onlyfacts.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.onlyfacts.MainActivity;
import com.example.onlyfacts.headnews.Headnews1;
import com.example.onlyfacts.headnews.Headnews2;
import com.example.onlyfacts.headnews.Headnews3;
import com.example.onlyfacts.headnews.Headnews4;
import com.example.onlyfacts.news_category.FragHome;

public class HeadnewsAdapter extends FragmentStateAdapter {

    public int mCount;

    public HeadnewsAdapter(FragHome fragmentActivity, int num_page) {
        super(fragmentActivity);
        mCount = num_page;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index==0) return new Headnews1();
        else if(index==1) return new Headnews2();
        else if(index==2) return new Headnews3();
        else return new Headnews4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) {
        return position % mCount;
    }
}