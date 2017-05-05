package com.example.expanableListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ExpanableListView示例
 */
public class MyActivity extends Activity {
    ExpandableListView elv;
    MyAdapter adapter;
    List<GroudBean> listGroud = new ArrayList<GroudBean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        elv = (ExpandableListView) findViewById(R.id.elv);
        String[] arr1={"ddd","aaaa"};
        String[] arr2={"kkkkk","ffff","dfsdfsdfsd"};
        String[] arr3={"aaaaa","bbbbb","cccccccccccccccccccccccccccccc"};
        listGroud.add(new GroudBean("第一組", Arrays.asList(arr1)));
        listGroud.add(new GroudBean("第二組", Arrays.asList(arr2)));
        listGroud.add(new GroudBean("第三組", Arrays.asList(arr3)));
        adapter = new MyAdapter(this, listGroud);
        elv.setAdapter(adapter);
    }
}
