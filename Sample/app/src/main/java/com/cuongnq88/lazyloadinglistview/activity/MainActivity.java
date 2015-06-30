package com.cuongnq88.lazyloadinglistview.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.cuongnq88.lazyloadinglistview.adapter.MyBaseAdapter;
import com.cuongnq88.lazyloadinglistview.listener.OnLazyLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private View mFooter;
    private BaseAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadControl();
        loadData();
    }

    /**
     * loadControl
     */
    private void loadControl() {
        mListView = (ListView) findViewById(R.id.list_view);
        mFooter = createFooterListView();
        mListView.setOnScrollListener(new OnLazyLoadingListener() {
            @Override
            public void onAddLazyLoading() {
                mListView.addFooterView(mFooter);
            }

            @Override
            public void onRemoveLazyLoading() {
                mListView.removeFooterView(mFooter);
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLoadingData() {
                if (mData != null) {
                    List<String> data = createItemList(mData.size(), 15);
                    mData.addAll(data);
                }
            }

        });
    }

    /**
     * loadData
     */
    private void loadData() {
        mData = createItemList(0, 30);
        mAdapter = new MyBaseAdapter(this, mData);
        mListView.setAdapter(mAdapter);
    }

    /**
     * createFooterListView
     *
     * @return create view footer
     */
    private View createFooterListView() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.footer_list_view, null, false);
        return v;
    }

    /**
     * createItemList
     *
     * @param position
     * @param size
     * @return
     */
    private List<String> createItemList(int position, int size) {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            itemList.add("Item " + (position + i));
        }
        return itemList;
    }
}









