package com.example.comment.utils;

/**
 * 固定常量类接口，提供各种固定数据
 */
public interface ConstantUtils {

    public int RESULTCODE_NOCLOSEACTIVITY = 66;//设置返回码是不关闭上一个页面
    public int REQUESTCODE_LOGIN_USERINFO = 60;     //Login页面跳转到UserInfo页面的请求码

    public int REQUESTCODE_TEMPLEACTIVITY = 70;     //BloodDiagnosisDetailsActivity页面跳转到TempleActivity页面的请求码
    public int RESPONSE_TEMPLEACTIVITY = 77;     //TempleActivity到BloodDiagnosisDetailsActivity页面跳转的返回码
    public String TEMPLET = "templet";

}
