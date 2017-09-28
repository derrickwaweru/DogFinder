package com.example.root.dogfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.dogfinder.Constants;
import com.example.root.dogfinder.R;
import com.example.root.dogfinder.models.Dog;
import com.example.root.dogfinder.ui.DogDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;


public class FirebaseDogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;
    public ImageView mDogImageView;

    public FirebaseDogViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindDog(Dog dog) {
        mDogImageView = (ImageView) mView.findViewById(R.id.dogImageView);
        ImageView dogImageView = (ImageView) mView.findViewById(R.id.dogImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.dogNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(dog.getImageUrl())
                .into(mDogImageView);

        nameTextView.setText(dog.getName());
        categoryTextView.setText(dog.getCategories().get(0));
        ratingTextView.setText("Rating: " + dog.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Dog> dogs = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOGS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    dogs.add(snapshot.getValue(Dog.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DogDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("dogs", Parcels.wrap(dogs));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

