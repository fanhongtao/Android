/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.tabhost;

import android.os.Bundle;

/**
 * This file demonstrate how to create a simple Tab.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class TabHost1 extends BaseTabActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "TabHost1";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        
        addTabView(getString(R.string.view1), null, View1.class);
        addTabView(getString(R.string.view2), null, View2.class);
    }
}
