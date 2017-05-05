package com.example.comment.mvp.view.common;

import android.content.Context;
import com.example.comment.adapter.TempletAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
public interface TempletView extends BaseView {
    /**
     * 暂无数据
     */
    void showTempletEmpty();

    /**
     * 有数据,展示数据
     */
    void showTemplets();

    /**
     * 获取上下文
     */
    Context getContext();

    /**
     * 获取listview适配器
     */
    TempletAdapter getAdapter();
}
