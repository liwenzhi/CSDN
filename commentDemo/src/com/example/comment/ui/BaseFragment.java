package com.example.comment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.example.comment.mvp.presenter.BasePresenter;
import com.example.comment.utils.CommonUtils;


/**
 * fragment 基类，默认实现以下功能
 * 1 初始化 presenter
 * 2 在相应的生命周期对 view 的绑定和解绑
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public T mPresenter;

    private InputMethodManager mInputMethodManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // 初始化 presenter
        mPresenter = initPresenter();
        // 绑定 view
        if (mPresenter != null){
            mPresenter.onCreate((V)this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 解绑 view
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null){
            mPresenter.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null){
            mPresenter.onStop();
        }
    }

    /**
     * 初始化 presenter
     */
    protected abstract T initPresenter();


    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        FragmentActivity activity = getActivity();
        if (activity.getWindow().getAttributes().softInputMode
                != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mInputMethodManager == null) {
                mInputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            }
            if (mInputMethodManager.isActive() && activity.getCurrentFocus()!= null){
                try {
                    mInputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * 显示网络不可用
     */
    public void showNetworkError(){
        CommonUtils.showToast(getActivity(), "网络异常");
    }
}