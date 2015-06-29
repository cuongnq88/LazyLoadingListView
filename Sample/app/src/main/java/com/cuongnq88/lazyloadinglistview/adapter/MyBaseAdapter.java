package com.cuongnq88.lazyloadinglistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cuongnq88.lazyloadinglistview.activity.R;

import java.util.List;

/**
 * Created by nguyenquoccuong on 6/29/15.
 */

public class MyBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;

    /**
     * MyBaseAdapter
     *
     * @param context
     * @param data
     */
    public MyBaseAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        } else {
            view = convertView;
        }
        binView(position, view);
        return view;
    }

    /**
     * binView
     * @param position
     * @param view
     */
    private void binView(int position, View view) {
        String title = mData.get(position);
        TextView textView = (TextView)view.findViewById(R.id.text_view_title);
        textView.setText(title);

    }
}













