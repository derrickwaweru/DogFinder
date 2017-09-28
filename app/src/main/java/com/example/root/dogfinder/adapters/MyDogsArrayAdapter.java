package com.example.root.dogfinder.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by root on 9/22/17.
 */

public class MyDogsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mDogs;
    private String[] mBreeds;

    public MyDogsArrayAdapter(Context mContext, int resource, String[] mDogs, String[] mBreeds){
        super(mContext, resource);
        this.mContext = mContext;
        this.mDogs = mDogs;
        this.mBreeds = mBreeds;
    }
    @Override
    public Object getItem(int position) {
        String dog = mDogs[position];
        String breed = mBreeds[position];
        return String.format("%s \nShows great: %s", dog, breed);
    }

    @Override
    public int getCount() {
        return mDogs.length;
    }
}