/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.listview;

import org.fanhongtao.common.BaseTestActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * ListView need a ListAapter. We can use ArrayAdapter, SimpleCursorAdapter, SimpleAdapter(BaseAdapter).
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListViewTestActivity extends BaseTestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        createButton(R.id.button1, ListView1.class);
        createButton(R.id.button2, ListView2.class);
        createButton(R.id.button3, ListView3.class);
        createButton(R.id.button4, ListView4.class);
        createButton(R.id.button5, ListView5.class);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.main_layout);
        createButton(layout, "List6: Different list-item height", ListView6.class);
        createButton(layout, "List7: ListView with search-box", ListViewWithSearchBox.class);
    }
}