package com.example.expanableListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ExpandableListView的適配器
 */
public class MyAdapter extends BaseExpandableListAdapter {
    //所有组的数,每一组的数据里面还有一个集合
    private List<GroudBean> listGroud = new ArrayList<GroudBean>();
    //上下文
    private Context context;

    /**
     * 构造方法
     */
    public MyAdapter(Context context, List<GroudBean> listGroud) {
        this.context = context;
        this.listGroud.clear();
        this.listGroud.addAll(listGroud);
    }

    /**
     * @return返回组的数量
     */
    @Override
    public int getGroupCount() {
        return listGroud.size();
    }

    /**
     * 每个组的孩子的数量
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return listGroud.get(groupPosition).getListChild().size();
    }

    /**
     * 每个组的对象
     *
     * @param groupPosition
     * @return
     */
    @Override
    public GroudBean getGroup(int groupPosition) {
        return listGroud.get(groupPosition);
    }

    /**
     * 每个孩子的对象
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public String getChild(int groupPosition, int childPosition) {
        return listGroud.get(groupPosition).getListChild().get(childPosition);
    }

    /**
     * 每个组的游标值
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 组里面孩子的游标值
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 是否是稳定的值,一般返回true就可以
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 是否可以被选中
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 组的视图对象
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_groud, null);
        TextView tv_groud = (TextView) convertView.findViewById(R.id.tv_groud);
        tv_groud.setText("" + getGroup(groupPosition).getName());
        return convertView;
    }

    /**
     * 组里面孩子的视图对象
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_child, null);
        TextView tv_child = (TextView) convertView.findViewById(R.id.tv_child);
        tv_child.setText("" + getChild(groupPosition, childPosition));
        return convertView;
    }


}
