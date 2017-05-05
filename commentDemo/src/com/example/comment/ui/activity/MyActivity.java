package com.example.comment.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.comment.R;
import com.example.comment.utils.ConstantUtils;
import com.example.comment.widget.NormalTitleBar;

/**
 * 评论内容的版面设计
 */
public class MyActivity extends Activity implements View.OnClickListener {
    private NormalTitleBar titleBar; //布局内的控件
    private TextView common_template;
    private EditText input_conclusion;
    // 要选择哪个模板(诊断分析还是医生建议 , 最终操作两个不同的数据库)
    public static final String WHICH_TEMPLET = "WHICH_TEMPLET";
    // 诊断分析模板
    public static final int TEMPLET_ANALYSIS = 0;
    // 医生建议模板
    public static final int TEMPLET_PROPOSE = 1;
    //选中后返回的模板数据名称
    public static final String TEMPLET = "TEMPLET";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        initData();

    }

    private void initView() {
        titleBar = (NormalTitleBar) findViewById(R.id.titleBar);
        common_template = (TextView) findViewById(R.id.common_template);
        input_conclusion = (EditText) findViewById(R.id.input_conclusion);
    }

    private void initData() {
        titleBar.setLeftLayoutClickListener(this);
        common_template.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_left:
                finish();
                break;
            case R.id.common_template:
                Intent intent = new Intent(this, TempletActivity.class);
                intent.putExtra(WHICH_TEMPLET, TEMPLET_ANALYSIS);
                startActivityForResult(intent, ConstantUtils.REQUESTCODE_TEMPLEACTIVITY);
                break;



        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //返回的字符串

        if (requestCode == ConstantUtils.REQUESTCODE_TEMPLEACTIVITY && resultCode == ConstantUtils.RESPONSE_TEMPLEACTIVITY) {
            String s = data.getStringExtra(TEMPLET);
            //如果是按要求返回数据，就设置数据
            input_conclusion.append(s + ";" + "\n");
        }

    }

}
