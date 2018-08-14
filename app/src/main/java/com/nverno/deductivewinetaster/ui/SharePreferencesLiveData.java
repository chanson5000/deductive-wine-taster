//package com.nverno.deductivewinetaster.ui;
//
//import android.arch.lifecycle.LiveData;
//import android.content.SharedPreferences;
//
//import java.lang.reflect.Type;
//
//public class SharePreferencesLiveData<Type> extends LiveData<Type> {
//    private SharedPreferences sharedPreferences;
//    private String key;
//    private Type defaultValue;
//    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
//
//
//
//    public SharePreferencesLiveData(SharedPreferences sharedPreferences, String key, Type defaultValue) {
//
//
//        sharedPreferences.registerOnSharedPreferenceChangeListener();
//    }
//
//
//
//
//
//    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
//        @Override
//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            if (key == this.key) {
//                preferenceChangeListener = getValueFromPreferences(key, defaultValue);
//            }
//        }
//    };
//
//    @Override
//    protected void onActive() {
//        super.onActive();
//    }
//
//    @Override
//    protected void onInactive() {
//        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
//        super.onInactive();
//    }
//
////    abstract void getValueFromPreferences(String key, Type defaultValue);
//
//}
////
////class SharedPreferencesIntLiveData extends SharePreferencesLiveData {
////
////}
