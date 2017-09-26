package com.example.root.mbwakenya.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.mbwakenya.Constants;
import com.example.root.mbwakenya.R;
import com.example.root.mbwakenya.models.Dog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;



public class DogDetailFragment extends Fragment implements View.OnClickListener {
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

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveDogButton.setOnClickListener(this);


        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mDog.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mDog.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mDog.getLatitude()
                            + "," + mDog.getLongitude()
                            + "?q=(" + mDog.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveDogButton) {
            DatabaseReference dogRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_DOGS);
            dogRef.push().setValue(mDog);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
