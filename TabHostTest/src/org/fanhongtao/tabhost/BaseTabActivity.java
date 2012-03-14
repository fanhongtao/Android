/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.tabhost;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class BaseTabActivity extends TabActivity {
    
    protected String TAG;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
    
    protected void addTabView(String tabId, Drawable icon, Class<?> viewClass) {
        TabHost tabHost = getTabHost();
        TabSpec tabSpec = tabHost.newTabSpec(tabId);
        tabSpec.setIndicator(tabId, icon);
        tabSpec.setContent(new Intent(this, viewClass));
        tabHost.addTab(tabSpec);
    }
}
