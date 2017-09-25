package com.example.root.mbwakenya.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.mbwakenya.R;
import com.example.root.mbwakenya.models.Dog;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DogDetailFragment extends Fragment {
    @Bind(R.id.dogImageView) ImageView mImageLabel;
    @Bind(R.id.dogNameTextView) TextView mNameLabel;
    @Bind(R.id.breedTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveDogButton) TextView mSaveDogButton;

    private Dog mDog;

    public static DogDetailFragment newInstance(Dog dog) {
        DogDetailFragment dogDetailFragment = new DogDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dog", Parcels.wrap(dog));
        dogDetailFragment.setArguments(args);
        return dogDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDog = Parcels.unwrap(getArguments().getParcelable("dog"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mDog.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mDog.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mDog.getCategories()));
        mRatingLabel.setText(Double.toString(mDog.getRating()) + "/5");
        mPhoneLabel.setText(mDog.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mDog.getAddress()));

        return view;
    }
}