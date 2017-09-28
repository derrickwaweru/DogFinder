package com.example.root.dogfinder.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.root.dogfinder.models.Dog;
import com.example.root.dogfinder.ui.DogDetailFragment;

import java.util.ArrayList;


public class DogPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Dog> mDogs;

    public DogPagerAdapter(FragmentManager fm, ArrayList<Dog> dogs) {
        super(fm);
        mDogs = dogs;
    }
    @Override
    public Fragment getItem(int position) {
        return DogDetailFragment.newInstance(mDogs.get(position));
    }

    @Override
    public int getCount() {
        return mDogs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDogs.get(position).getName();
    }
}