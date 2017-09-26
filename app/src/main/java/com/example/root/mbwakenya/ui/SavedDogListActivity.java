package com.example.root.mbwakenya.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.root.mbwakenya.Constants;
import com.example.root.mbwakenya.R;
import com.example.root.mbwakenya.adapters.FirebaseDogViewHolder;
import com.example.root.mbwakenya.models.Dog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedDogListActivity extends AppCompatActivity {

    private DatabaseReference mDogReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dogs);
        ButterKnife.bind(this);

        mDogReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOGS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Dog, FirebaseDogViewHolder>
                (Dog.class, R.layout.dog_list_item, FirebaseDogViewHolder.class,
                        mDogReference) {

            @Override
            protected void populateViewHolder(FirebaseDogViewHolder viewHolder,
                                              Dog model, int position) {
                viewHolder.bindDog(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}