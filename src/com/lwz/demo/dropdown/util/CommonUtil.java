package com.lwz.demo.dropdown.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的通用util
 */
public class CommonUtil {

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isEmpty(String s) {
        return s == null || TextUtils.isEmpty(s);
    }
}
