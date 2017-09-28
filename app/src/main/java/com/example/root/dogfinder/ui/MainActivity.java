package com.example.root.dogfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.dogfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findDogsButton) Button mFindDogsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedDogsButton) Button mSavedDogsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface gloriaFont = Typeface.createFromAsset(getAssets(), "fonts/GloriaHallelujah.ttf");
        mAppNameTextView.setTypeface(gloriaFont);

        mFindDogsButton.setOnClickListener(this);
        mSavedDogsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == mFindDogsButton) {
            Intent intent = new Intent(MainActivity.this, DogListActivity.class);
            startActivity(intent);
        }

        if (v == mSavedDogsButton) {
            Intent intent = new Intent(MainActivity.this, SavedDogListActivity.class);
            startActivity(intent);
        }
    }
}