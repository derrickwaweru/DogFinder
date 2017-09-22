package com.example.root.mbwakenya.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.mbwakenya.R;
import com.example.root.mbwakenya.models.Dog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;



public class DogListAdapter extends RecyclerView.Adapter<DogListAdapter.DogViewHolder> {
    private ArrayList<Dog> mDogs = new ArrayList<>();
    private Context mContext;

    public DogListAdapter(Context context, ArrayList<Dog> dogs) {
        mContext = context;
        mDogs = dogs;
    }
    @Override
    public DogListAdapter.DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_list_item, parent, false);
        DogViewHolder viewHolder = new DogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DogListAdapter.DogViewHolder holder, int position) {
        holder.bindDog(mDogs.get(position));
    }

    @Override
    public int getItemCount() {
        return mDogs.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dogImageView) ImageView mDogImageView;
        @Bind(R.id.dogNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public DogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDog(Dog dog) {
            Picasso.with(mContext).load(dog.getImageUrl()).into(mDogImageView);
            mNameTextView.setText(dog.getName());
            mCategoryTextView.setText(dog.getCategories().get(0));
            mRatingTextView.setText("Rating: " + dog.getRating() + "/5");
        }
    }
}