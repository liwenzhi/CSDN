package com.example.NavigationDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Called when the activity is first created.
 */
public class MyActivity extends Activity {

    private List<String> mPlanetTitles = new ArrayList<String>();
    private DrawerLayout mDrawerLayout;
    private LinearLayout left;
    private ListView mDrawerList;
    private TextView tv_menu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();
        initData();
        initEvent();

    }


    private void initView() {
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        left= (LinearLayout) findViewById(R.id.left);
        tv_menu= (TextView) findViewById(R.id.tv_menu);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mPlanetTitles.add("left---Line" + i);
        }
        //创建并设置适配器
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, android.R.id.text1, mPlanetTitles);
        mDrawerList.setAdapter(adapter);
    }


    private void initEvent() {
        //设置ListView的点击条目事件 ,选中后让菜单栏消失
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyActivity.this, "点击了" + position, Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawers();   //关闭侧边栏的菜单
            }
        });

       //点击菜单按钮，显示菜单
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(left);  //显示左边的菜单栏的控制
            }
        });

        //监听侧边菜单栏打开或关闭的状态
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {

            }
            //打开菜单栏后触发的方法
            @Override
            public void onDrawerOpened(View view) {
                Toast.makeText(MyActivity.this, "打开了侧边栏" , Toast.LENGTH_LONG).show();
            }
            //关闭菜单栏后触发的方法
            @Override
            public void onDrawerClosed(View view) {
                Toast.makeText(MyActivity.this, "关闭了侧边栏" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });


    }
}
