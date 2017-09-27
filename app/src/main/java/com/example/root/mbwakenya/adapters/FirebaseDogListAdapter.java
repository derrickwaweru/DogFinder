package com.example.root.mbwakenya.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.root.mbwakenya.models.Dog;
import com.example.root.mbwakenya.util.ItemTouchHelperAdapter;
import com.example.root.mbwakenya.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;



public class FirebaseDogListAdapter extends FirebaseRecyclerAdapter<Dog, FirebaseDogViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;


    public FirebaseDogListAdapter(Class<Dog> modelClass, int modelLayout,
                                  Class<FirebaseDogViewHolder> viewHolderClass,
                                  Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void populateViewHolder(final FirebaseDogViewHolder viewHolder, Dog model, int position) {
        viewHolder.bindDog(model);
        viewHolder.mDogImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}