package com.example.comment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.comment.R;
import com.example.comment.entity.Templet;
import com.example.comment.widget.swipemenulistview.BaseSwipListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public class TempletAdapter extends BaseSwipListAdapter {

    private static final String TAG = "TempletAdapter";
    private final int TYPE_HEADER = 0;
    private final int TYPE_TEMPLET = 1;
    private List<Templet> templets = new ArrayList<Templet>();

    @Override
    public int getCount() {
        return templets.size();
    }

    @Override
    public Templet getItem(int position) {
        return templets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder = null;
        TempletViewHolder templetViewHolder = null;
        if (getItemViewType(position) == TYPE_HEADER) {
            if (convertView == null) {
                headerViewHolder = new HeaderViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_templet_header, null);
                headerViewHolder.tv_templet_header = (TextView) convertView.findViewById(R.id.tv_templet_header);
                convertView.setTag(headerViewHolder);
            } else {
                headerViewHolder = (HeaderViewHolder) convertView.getTag();
            }
            headerViewHolder.setData(templets.get(position));
        } else {
            if (convertView == null) {
                templetViewHolder = new TempletViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_templet, null);
                templetViewHolder.tv_templet = (TextView) convertView.findViewById(R.id.tv_item_templet);
                convertView.setTag(templetViewHolder);
            } else {
                templetViewHolder = (TempletViewHolder) convertView.getTag();
            }
            templetViewHolder.setData(templets.get(position));
        }
        return convertView;
    }

    static class TempletViewHolder {
        TextView tv_templet;

        public void setData(Templet templet) {
            tv_templet.setText(templet.getText());
        }
    }

    static class HeaderViewHolder {
        TextView tv_templet_header;

        public void setData(Templet templet) {
            tv_templet_header.setText(templet.getClasse());
        }

    }

    public void updata(List<Templet> datas) {

        // 类别列表
        ArrayList<String> classes = new ArrayList<String>();
        for (Templet templet : datas) {
            String classe = templet.getClasse();
            if (!classes.contains(classe)) {
                classes.add(classe);
            }
        }

        List<Templet> temp = new ArrayList<Templet>();
        for (String classe : classes) {
            // 添加标题头数据
            Templet templetHeader = new Templet();
            templetHeader.setText("");
            templetHeader.setClasse(classe);
            temp.add(templetHeader);
            for (Templet templet : datas) {
                if (templet.getClasse().equals(classe) && !templet.getText().equals("")) {
                    temp.add(templet);
                }
            }
        }

        Collections.sort(temp, new Comparator<Templet>() {
            @Override
            public int compare(Templet lhs, Templet rhs) {
                return lhs.getClasse().compareTo(rhs.getClasse());
            }
        });
        this.templets.clear();
        this.templets.addAll(temp);

        notifyDataSetChanged();
    }

    @Override
    public boolean getSwipEnableByPosition(int position) {
        if (this.templets.get(position).getText().equals("")) {
            return false;
        } else {
            return true;
        }

    }

    public List<Templet> getTemplets() {
        return templets;
    }

    @Override
    public int getItemViewType(int position) {
        if (templets.get(position).getText().equals("")) {
            return TYPE_HEADER;
        } else {
            return TYPE_TEMPLET;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
