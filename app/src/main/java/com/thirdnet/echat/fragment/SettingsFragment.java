package com.thirdnet.echat.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.thirdnet.echat.R;

/**
 * 作者：杨水强
 * 时间：2016/3/3
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.d("test", "test");
        return false;
    }
}
