package com.example.FragmentDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.nineoldandroids.view.ViewHelper;

/**
 * 水平的滚动视图，实现侧滑功能
 */
public class QQSliddingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenuViewGroup;//菜单显示的容器
    private ViewGroup mContentViewGroup;//主显示页面的容器

    private int mScreenWidth;
    private int mMenuRightPadding = 50;
    private int mMenuWidth;//侧滑菜单栏的宽度

    private boolean mIsOnce = true;//第一次显示页面
    private boolean mIsOpen;//是否是打开菜单栏的状态

    /**
     * 构造方法
     */
    public QQSliddingMenu(Context context) {
        this(context, null);
    }

    public QQSliddingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQSliddingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.QQSliddingMenu, defStyleAttr, 0);
        int n = typedArray.length();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.QQSliddingMenu_rigthtPadding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(
                            attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics())
                    );
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
    }

    /**
     * 测量布局
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mIsOnce) {
            mWapper = (LinearLayout) getChildAt(0);
            mMenuViewGroup = (ViewGroup) mWapper.getChildAt(0);
            mContentViewGroup = (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth = mMenuViewGroup.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContentViewGroup.getLayoutParams().width = mScreenWidth;
            mIsOnce = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth, 0);
        }
    }

    /**
     * 事件响应
     */
    private int lastX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //获取到手指处的横坐标和纵坐标
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (action) {
            case MotionEvent.ACTION_UP:
                lastX = x;
                int scrollX = getScrollX();
                if (scrollX >= mMenuWidth / 2) {
                    smoothScrollTo(mMenuWidth, 0);//关闭侧边菜单，向左滑动后才算是关闭 的
                    mIsOpen = false;
                } else {
                    smoothScrollTo(0, 0);//打开侧边菜，默认就是打开的，
                    mIsOpen = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offX = x - lastX;
                if (offX > 200) {   //向右滑动的距离
                    Log.e("TAG", " //向右滑动的距离offX" + offX);
                }
                if (offX < -200) {   //向左滑动的距离
                    Log.e("TAG", " //向左滑动的距离offX" + offX);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单栏
     */
    public void openMenu() {
        if (mIsOpen)
            return;
        smoothScrollTo(0, 0);
        mIsOpen = true;
    }

    /**
     * 关闭菜单栏
     */
    public void closeMenu() {
        if (!mIsOpen)
            return;
        smoothScrollTo(mMenuWidth, 0);
        mIsOpen = false;
    }

    /**
     * 切换菜单
     */
    public void toggleMenu() {
        if (mIsOpen)
            closeMenu();
        else
            openMenu();
    }

    /**
     * 页面滑动的距离的改变
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth; // l: 1.0 ~ 0
        ViewHelper.setTranslationX(mMenuViewGroup, mMenuWidth * scale * 0.7f);

        float rightScale = 0.7f + 0.3f * scale;
        ViewHelper.setPivotX(mContentViewGroup, 0);
        ViewHelper.setPivotY(mContentViewGroup, mContentViewGroup.getHeight() / 2);
        ViewHelper.setScaleX(mContentViewGroup, rightScale);
        ViewHelper.setScaleY(mContentViewGroup, rightScale);

        float leftScale = 1.0f - 0.3f * scale;
        ViewHelper.setScaleX(mMenuViewGroup, leftScale);
        ViewHelper.setScaleY(mMenuViewGroup, leftScale);

        float leftAlpha = 0.1f + 0.9f * (1 - scale);
        ViewHelper.setAlpha(mMenuViewGroup, leftAlpha);
    }
}
