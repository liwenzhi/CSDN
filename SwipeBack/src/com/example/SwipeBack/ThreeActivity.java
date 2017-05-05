package com.example.SwipeBack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import com.example.SwipeBack.swipebacklayout.lib.app.SwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ThreeActivity extends SwipeBackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        //创建适配器对象
        Myadapter adapter = new Myadapter();
        //给视图ViewPager添加适配器
        viewPager.setAdapter(adapter);

    }

    public void jump(View view) {
        startActivity(new Intent(ThreeActivity.this, MyActivity.class));
    }

    //数据源,8张图片
    int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h};
    //定义一个集合用来存放视图的View对象
    List<View> listOfView = new ArrayList<View>();

    /**
     * 在类内创建Adapter对象，
     * 这里ViewPager要对应的Adapter是PagerAdapter
     */
    class Myadapter extends PagerAdapter {

        //可滑动的页面的数量
        @Override
        public int getCount() {
            //return listOfView.size();//实现只能滑动一遍图片的效果
            return Integer.MAX_VALUE;//实现无限滑动的效果
        }

        //判断两页的地址是否相同
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //这是google要求的写法
            return view == object;
        }


        //重写构造方法，当创建实例时，把数据添加到集合中
        public Myadapter() {
            for (int i = 0; i < images.length; i++) {
                //创建ImageView存放图片
                ImageView iv = new ImageView(getApplicationContext());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(images[i]);
                //把图片添加到集合zhong
                listOfView.add(iv);
            }
        }

        //下面的两个方法也是必须重写的

        //这里要返回的是View的视图对象
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加一个item 显示
            //Toast.makeText(getBaseContext(), position + "页", Toast.LENGTH_SHORT).show();
            container.addView(listOfView.get(position % 8));
            return listOfView.get(position % 8);
        }

        //销毁一个View的对象
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listOfView.get(position % 8));
        }
    }


}
