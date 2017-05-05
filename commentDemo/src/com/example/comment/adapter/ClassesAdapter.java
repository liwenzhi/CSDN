package com.example.comment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.comment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-11
 * Time: 下午6:11
 * To change this template use File | Settings | File Templates.
 */
public class ClassesAdapter extends BaseAdapter {

    private List<String> mClasses;

    public ClassesAdapter(List<String> classes) {
        mClasses = classes;
    }

    public ClassesAdapter(String state) {
        mClasses = new ArrayList<String>();
        mClasses.add(state);
    }

    @Override
    public int getCount() {
        return mClasses.size();
    }

    @Override
    public String getItem(int position) {
        return mClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popupwindow_templet_class, null);
            viewHolder.tv_class = (TextView) convertView.findViewById(R.id.tv_popupwindow_classes);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(mClasses.get(position));
        return convertView;
    }

    static class ViewHolder {
        TextView tv_class;

        public void setData(String data) {
            tv_class.setText(data);
        }
    }
}
