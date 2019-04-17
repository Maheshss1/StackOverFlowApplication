package com.example.mahesh.stackoverflowapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.mahesh.stackoverflowapplication.FavouriteQuestionsFragment;
import com.example.mahesh.stackoverflowapplication.HotQuestionsFragment;
import com.example.mahesh.stackoverflowapplication.WeekQuestionsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    String item;

    private static final String TAG = "sfsdfdsfsdfsdf";

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FavouriteQuestionsFragment();
            case 1:
                return new HotQuestionsFragment();
            case 2:
                return new WeekQuestionsFragment();


        }

        return null;

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.d(TAG, "getItemPosition: "+object);
        return super.getItemPosition(object);
    }



    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId: "+position);
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public String getItem(){
        return item;
    }

    public void setItem(String item){
        this.item = item;
    }
}
