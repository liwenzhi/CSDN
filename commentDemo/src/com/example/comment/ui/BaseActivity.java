package com.example.comment.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.example.comment.mvp.presenter.BasePresenter;
import com.example.comment.utils.CommonUtils;


/**
 * activity 基类，默认实现以下功能
 * 1 初始化 presenter
 * 2 在相应的生命周期对 view 的绑定和解绑
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends Activity {

    public T mPresenter;

    private InputMethodManager mInputMethodManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // 初始化 presenter
        mPresenter = initPresenter();
        // 绑定 view
        if (mPresenter != null) {
            mPresenter.onCreate((V) this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑 view
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    /**
     * 初始化 presenter
     */
    protected abstract T initPresenter();

    /**
     * 回退 activity
     */
    public void back() {
        finish();
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode
                != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mInputMethodManager == null) {
                mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            }
            if (mInputMethodManager.isActive() && getCurrentFocus() != null) {
                try {
                    mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * 显示网络不可用
     */
    public void showNetworkError() {
        CommonUtils.showToast(this, "网络异常");
    }
}