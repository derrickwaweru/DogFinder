package com.example.root.dogfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.dogfinder.R;
import com.example.root.dogfinder.models.Dog;
import com.example.root.dogfinder.ui.DogDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

    public class DogViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        @Bind(R.id.dogImageView) ImageView mDogImageView;
        @Bind(R.id.dogNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public DogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DogDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("dogs", Parcels.wrap(mDogs));
            mContext.startActivity(intent);
        }

        public void bindDog(Dog dog) {
            Picasso.with(mContext).load(dog.getImageUrl()).into(mDogImageView);
            mNameTextView.setText(dog.getName());
            mCategoryTextView.setText(dog.getCategories().get(0));
            mRatingTextView.setText("Rating: " + dog.getRating() + "/5");
        }
    }
}