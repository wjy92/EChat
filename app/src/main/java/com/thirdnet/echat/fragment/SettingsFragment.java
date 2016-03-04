package com.thirdnet.echat.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.thirdnet.echat.R;

/**
 * 作者：杨水强
 * 时间：2016/3/3
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("example_text") || key.equals("example_list")) {
            Preference connectionPref = findPreference(key);
            connectionPref.setSummary(sharedPreferences.getString(key, ""));
        }
    }

    private void initPreferenceDisplay() {
        Preference connectionPref0 = findPreference("example_text");
        connectionPref0.setSummary(getPreferenceScreen().getSharedPreferences().getString("example_text", ""));
        Preference connectionPref1 = findPreference("example_list");
        connectionPref1.setSummary(getPreferenceScreen().getSharedPreferences().getString("example_list", ""));
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        initPreferenceDisplay();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
