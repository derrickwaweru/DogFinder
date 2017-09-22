package com.example.root.mbwakenya.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.mbwakenya.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findDogsButton) Button mFindDogsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface gloriaFont = Typeface.createFromAsset(getAssets(), "fonts/GloriaHallelujah.ttf");
        mAppNameTextView.setTypeface(gloriaFont);

        mFindDogsButton.setOnClickListener(this);
    }
            @Override
            public void onClick (View v){
                if(v == mFindDogsButton) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, DogsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
    }
}
