/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.tabhost;

import java.util.ArrayList;
import java.util.List;

import org.fanhongtao.common.BaseActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class View2  extends BaseActivity {
    private ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "View2";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view2);
        
        listView  = (ListView)findViewById(R.id.item_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
    }
    
    private List<String> getData(){
        List<String> data = new ArrayList<String>(26);
        for (int i=0; i<26; i++) {
            data.add("Item " + (char)('A' + i));
        }
        return data;
    }
}
