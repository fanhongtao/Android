/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.tabhost;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;

/**
 * This file demonstrate how to use &ltselector&gt to change the icon of tab.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class TabHost3 extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "TabHost3";
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab2);  // Reuse the layout of TabHost2.
        
        // Here is the key point: use a <selector> drawable as the icon of Tab.
        addTabView(getString(R.string.view1), getResources().getDrawable(R.drawable.tab_button_selector), View1.class);
        addTabView(getString(R.string.view2), getResources().getDrawable(R.drawable.tab_button_selector), View2.class);
        
        TabHost tabHost = getTabHost();
        TabWidget tw = tabHost.getTabWidget();
        tw.setBackgroundResource(R.drawable.tab_bg_3);
        
        for (int i = 0, n = tw.getChildCount(); i < n; i++) {
            View view = (View) tw.getChildTabViewAt(i);
            view.setBackgroundResource(R.drawable.none);
        }
    }
    
}
