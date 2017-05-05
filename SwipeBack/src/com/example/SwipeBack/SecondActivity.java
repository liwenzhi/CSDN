package com.example.SwipeBack;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.SwipeBack.swipebacklayout.lib.app.SwipeBackActivity;

import java.util.ArrayList;


public class SecondActivity extends SwipeBackActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("line " + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_list_item, android.R.id.text1, list);
        listView.setAdapter(adapter);
        setContentView(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position%5==0){
                    startActivity(new Intent(SecondActivity.this, ThreeActivity.class));
                }

                return false;
            }
        });


    }

}
