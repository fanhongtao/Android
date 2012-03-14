/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ListView need a ListAapter. We can use ArrayAdapter, SimpleCursorAdapter, SimpleAdapter(BaseAdapter).
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListViewTestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        createButton(R.id.button1, ListView1.class);
        createButton(R.id.button2, ListView2.class);
        createButton(R.id.button3, ListView3.class);
        createButton(R.id.button4, ListView4.class);
        createButton(R.id.button5, ListView5.class);
    }
    
    private void createButton(int id, final Class<?> activityClass) {
        Button button = (Button) findViewById(id);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, activityClass);
                startActivity(intent);
            }
        });
    }
}