package com.example.FragmentDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends FragmentActivity {

    List<Fragment> list = new ArrayList<Fragment>();
    ViewPager viewPager;

    String myActivityString;
    User myActivityUser;
    TextView tv_main_show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        myActivityString = new String("这是MyActivity的字符串");
        myActivityUser = new User("myActivityUser", 18);
        Fragment1 fragmemt1 = new Fragment1();
        Fragment2 fragmemt2 = new Fragment2();
        list.add(fragmemt1);
        list.add(fragmemt2);
        viewPager = (ViewPager) findViewById(R.id.home_vp);
        tv_main_show = (TextView) findViewById(R.id.tv_main_show);
        //创建适配器,设置的碎片管理器使用的是getChildFragmentManager()
        Myadapter adapter = new Myadapter(getSupportFragmentManager());

        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
    }

    //创建ViewPager适配器的类
    class Myadapter extends FragmentStatePagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    /**
     * 获取Fragment1中的数据
     *
     * @param view
     */
    public void getFragment1Data(View view) {

        String msg = ((Fragment1) getSupportFragmentManager().getFragments().get(0)).fragment1String;
        User msg2 = ((Fragment1) getSupportFragmentManager().getFragments().get(0)).fragment1User;
        Toast.makeText(this, msg + msg2, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取Fragment2中的数据
     *
     * @param view
     */
    public void getFragment2Data(View view) {
        String msg = ((Fragment2) getSupportFragmentManager().getFragments().get(1)).fragment2String;
        User msg2 = ((Fragment2) getSupportFragmentManager().getFragments().get(1)).fragment2User;
        Toast.makeText(this, msg + msg2, Toast.LENGTH_SHORT).show();
    }

    /**
     * 改变Fragment1中显示的内容
     */
    public void changeFragment1Data(View view) {
        Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().getFragments().get(0);
    }

    /**
     * 改变Fragment2中显示的内容
     */
    public void changeFragment2Data(View view) {
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().getFragments().get(1);
        fragment2.tv_fragment2_show.append("\nMyActivity在这里改变了数据");

    }

}
