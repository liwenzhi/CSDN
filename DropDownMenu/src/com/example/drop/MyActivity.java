package com.example.drop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.drop.dropdownmenu.ConstellationAdapter;
import com.example.drop.dropdownmenu.DropDownMenu;
import com.example.drop.dropdownmenu.GirdDropDownAdapter;
import com.example.drop.dropdownmenu.ListDropDownAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DropDownMenu使用示例
 */
public class MyActivity extends Activity {


    private String headers[] = {"城市", "年龄", "性别", "星座"};// 条件选择的标题
    private List<View> popupViews = new ArrayList<View>();
    private DropDownMenu mDropDownMenu;//自定义VIew类的对象
    private GirdDropDownAdapter cityAdapter;// 城市显示的适配器
    private ListDropDownAdapter ageAdapter;  //年龄显示的适配器
    private ListDropDownAdapter sexAdapter;//   性别显示的适配器
    private ConstellationAdapter constellationAdapter;//群体样式显示的适配器

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};// 第一个选择里面的内容
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;//  选中群体样式列表的序列号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //初始化对象
        initData();//初始化 数据
        initEvent();// 初始化点击事件
    }

    //下面四个是点击选择条件选择是弹出的视图View，可以是ListView也可以是VIew
    ListView cityView;
    ListView ageView;
    ListView sexView;
    View constellationView;
    GridView constellation;  //constellationView里面的视图布局

    TextView textView;//选择条件中间显示的视图，可以是View、ListView、TextView

    /**
     * 初始化数据
     */
    private void initView() {
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);

        //init city menu
        cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);//设置ListView条目间隔的距离
        cityView.setAdapter(cityAdapter);

        //init age menu
        ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        constellation = (GridView) constellationView.findViewById(R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);


    }


    private void initData() {
        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        //init context view
        textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setText("内容显示区域"+"\n");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, textView);

    }

    /**
     * 设置四个条件选择的点击事件
     */
    private void initEvent() {
        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                textView.append(position == 0 ? headers[0] : citys[position]+"\n");
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                textView.append(position == 0 ?headers[1] : ages[position]+"\n");
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                textView.append(position == 0 ?headers[2] : sexs[position]+"\n");
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        TextView ok = (TextView) constellationView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                textView.append(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]+"\n");
                mDropDownMenu.closeMenu();
            }
        });


    }

    /**
     * 监听点击回退按钮事件
     */
    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}

