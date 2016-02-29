package com.thirdnet.echat;

import android.app.Application;

import com.thirdnet.echat.bean.PersonBean;

/**
 * 作者：杨水强
 * 时间：2016/2/19
 */
public class MyApp extends Application {

    private PersonBean mMyInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyInfo = new PersonBean(0l, R.mipmap.portrait_test0);
    }

    public PersonBean getMyInfo() {
        return mMyInfo;
    }
}
