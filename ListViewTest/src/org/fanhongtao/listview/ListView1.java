/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.listview;

import java.util.ArrayList;
import java.util.List;

import org.fanhongtao.common.BaseActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Demo of using ArrayAdapter.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListView1 extends BaseActivity implements Handler.Callback {

    private ListView listView;
    
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "ListView1";
        super.onCreate(savedInstanceState);
        handler = new Handler(this);
        listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                handler.sendEmptyMessage(0);
                return super.getView(position, convertView, parent);
            }
            
        });
        setContentView(listView);
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>(26);
        for (int i=0; i<26; i++) {
            data.add("Item " + (char)('A' + i));
        }
        return data;
    }

    @Override
    public boolean handleMessage(Message msg) {
        int first = listView.getFirstVisiblePosition();
        int last = listView.getLastVisiblePosition();
        Log.i(TAG, "First: " + first + ", last: " + last);
        return false;
    }
}
