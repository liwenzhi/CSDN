package com.example.FragmentDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 碎片页面1
 */
public class Fragment1 extends Fragment   {


    String fragment1String;
    User fragment1User;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment1String = new String("这是Fragment1页面的数据");
        fragment1User = new User("fragment1User", 88);
        View view = inflater.inflate(R.layout.fragment1, null);
        listView= (ListView) view.findViewById(R.id.listView);
        initView();
        return view;
    }

    private void initView() {
        List<String> list=new ArrayList<String>();
        for (int i=0;i<100;i++){
            list.add("line"+i);
        }
        ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.activity_list_item,android.R.id.text1,list);
        listView.setAdapter(adapter);
    }


}
