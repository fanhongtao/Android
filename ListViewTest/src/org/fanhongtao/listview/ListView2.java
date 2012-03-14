/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.listview;

import org.fanhongtao.common.BaseActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Demo of using SimpleCursorAdapter.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListView2 extends BaseActivity {

    private ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "ListView2";
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);
        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                cursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME}, 
                new int[]{android.R.id.text1});
        
        listView.setAdapter(listAdapter);
        setContentView(listView);
    }
}
