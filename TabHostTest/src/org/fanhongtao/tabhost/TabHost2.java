/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.tabhost;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * This file demonstrate how to change tab's icon manually by register a 'OnTabChangedListener'.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class TabHost2 extends BaseTabActivity implements OnTabChangeListener {

    private int lastTabId = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "TabHost2";
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab2);
        
        addTabView(getString(R.string.view1), null, View1.class);
        addTabView(getString(R.string.view2), null, View2.class);
       
        // TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost tabHost = getTabHost();
        TabWidget tw = tabHost.getTabWidget();
        tw.setBackgroundResource(R.drawable.tab_bg);
        
        for (int i = 0, n = tw.getChildCount(); i < n; i++) {
            View view = (View) tw.getChildTabViewAt(i);
            view.setBackgroundResource(R.drawable.none);
            
            TextView tv = (TextView) view.findViewById(android.R.id.title);
            tv.setTextSize(10);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setText("Label" + (i+1));
            
            ImageView img = (ImageView) view.findViewById(android.R.id.icon);
            img.setBackgroundResource(R.drawable.none);
            img.setImageResource(R.drawable.tab_button);
        }
        
        tabHost.setOnTabChangedListener(this);
        onTabChanged(getString(R.string.view1));
    }
    

    @Override
    public void onTabChanged(String tabId) {
        Log.i(TAG, "onTabChanged: " + tabId);
        TabHost tabHost = getTabHost();
        int current = tabHost.getCurrentTab();
        
        ImageView iv = (ImageView)tabHost.getTabWidget().getChildAt(lastTabId).findViewById(android.R.id.icon);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.tab_button));
        
        iv = (ImageView)tabHost.getTabWidget().getChildAt(current).findViewById(android.R.id.icon);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.tab_button_selected));
        
        lastTabId = current;
    }
}
