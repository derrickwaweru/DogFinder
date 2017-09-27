package com.example.root.mbwakenya.util;

/**
 * Created by root on 9/26/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
