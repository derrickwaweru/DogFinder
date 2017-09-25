package com.example.root.mbwakenya.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.root.mbwakenya.adapters.DogListAdapter;
import com.example.root.mbwakenya.models.Dog;
import com.example.root.mbwakenya.R;
import com.example.root.mbwakenya.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class DogListActivity extends AppCompatActivity {
    public static final String TAG = DogListActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DogListAdapter mAdapter;


    public ArrayList<Dog> mDogs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        ButterKnife.bind(this);


//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dogs);
//        mListView.setAdapter(adapter);
//
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String dog = ((TextView)view).getText().toString();
//                Toast.makeText(DogListActivity.this, dog, Toast.LENGTH_LONG).show();
//            }
//        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getDogs(location);
    }

    private void getDogs(String location){
        final YelpService yelpService = new YelpService();
        yelpService.findDogs(location, new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDogs = yelpService.processResults(response);

                DogListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new DogListAdapter(getApplicationContext(), mDogs);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DogListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}