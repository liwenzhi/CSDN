package com.example.comment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.comment.R;


/**
 * title bar
 */
public class NormalTitleBar extends RelativeLayout {

    protected RelativeLayout mLeftLayout;
    protected ImageView mLeftImg;
    protected RelativeLayout mRightLayout;
    protected ImageView mRightImage;
    protected TextView mTitleTv;
    protected RelativeLayout mTitleLayout;
    protected TextView mRightText;

    public NormalTitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public NormalTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NormalTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this);
        mLeftLayout = (RelativeLayout) findViewById(R.id.layout_left);
        mLeftImg = (ImageView) findViewById(R.id.img_left);
        mRightLayout = (RelativeLayout) findViewById(R.id.layout_right);
        mRightImage = (ImageView) findViewById(R.id.img_right);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mTitleLayout = (RelativeLayout) findViewById(R.id.layout_title);
        mRightText = (TextView) findViewById(R.id.tv_right);
        parseStyle(context, attrs);

    }

    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            String title = ta.getString(R.styleable.TitleBar_titleBarTitle);
            String about = ta.getString(R.styleable.TitleBar_titleBarRightText);
            mTitleTv.setText(title);
            mRightText.setText(about);
            mRightText.setTextColor(Color.WHITE);
            Drawable leftDrawable = ta.getDrawable(R.styleable.TitleBar_titleBarLeftImage);
            if (null != leftDrawable) {
                mLeftImg.setImageDrawable(leftDrawable);
            }
            Drawable rightDrawable = ta.getDrawable(R.styleable.TitleBar_titleBarRightImage);
            if (null != rightDrawable) {
                mRightImage.setImageDrawable(rightDrawable);
            }

            Drawable background = ta.getDrawable(R.styleable.TitleBar_titleBarBackground);
            if (null != background) {
                mTitleLayout.setBackgroundDrawable(background);
            }

            ta.recycle();
        }
    }

    public void setLeftImageResource(int resId) {
        mLeftImg.setImageResource(resId);
    }

    public void setRightImageResource(int resId) {
        mRightImage.setImageResource(resId);
    }

    public void setLeftLayoutClickListener(OnClickListener listener) {
        mLeftLayout.setOnClickListener(listener);
    }

    public void setRightLayoutClickListener(OnClickListener listener) {
        mRightLayout.setOnClickListener(listener);
    }

    public void setTitleLayoutClickListener(OnClickListener listener) {
        mTitleLayout.setOnClickListener(listener);
    }

    public void setLeftLayoutVisibility(int visibility) {
        mLeftLayout.setVisibility(visibility);
    }

    public void setRightLayoutVisibility(int visibility) {
        mRightLayout.setVisibility(visibility);
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public void setBackgroundColor(int color) {
        mTitleLayout.setBackgroundColor(color);
    }


    public RelativeLayout getLeftLayout() {
        return mLeftLayout;
    }

    public RelativeLayout getRightLayout() {
        return mRightLayout;
    }
}
