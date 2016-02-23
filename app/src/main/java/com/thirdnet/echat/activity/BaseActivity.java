package com.thirdnet.echat.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * 作者：杨水强
 * 时间：2016/2/19
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }
}
