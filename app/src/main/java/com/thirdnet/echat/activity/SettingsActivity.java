package com.thirdnet.echat.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thirdnet.echat.fragment.SettingsFragment;

//参考http://developer.android.com/intl/zh-cn/guide/topics/ui/settings.html
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
