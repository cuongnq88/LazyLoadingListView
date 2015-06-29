package com.cuongnq88.lazyloadinglistview.listener;

import android.os.Handler;
import android.os.Message;
import android.widget.AbsListView;

import com.cuongnq88.lazyloadinglistview.utils.ListViewScrollTracker;

/**
 * Created by nguyenquoccuong on 6/29/15.
 */
public abstract class OnLazyLoadingListener implements AbsListView.OnScrollListener {
    private static final int LIMIT_ITEM = 5;
    private ListViewScrollTracker mTracker = null;
    private boolean isUpScrolling;
    private boolean isLoading;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isLoading = false;
            onRemoveLazyLoading();
        }
    };
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mTracker == null) {
            mTracker = new ListViewScrollTracker(view);
        }
        isUpScrolling = mTracker.detectedListViewScroll(firstVisibleItem);
        if (!isUpScrolling && !isLoading) {
            if (firstVisibleItem + visibleItemCount >= totalItemCount - LIMIT_ITEM) {
                isLoading = true;
                onAddLazyLoading();
                mHandler.sendEmptyMessageDelayed(0,3000);
            }
        }
    }

    public abstract void onAddLazyLoading();

    public abstract void onRemoveLazyLoading();
}
