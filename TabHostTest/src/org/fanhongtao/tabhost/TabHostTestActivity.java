/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.tabhost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class TabHostTestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        createButton(R.id.button1, TabHost1.class);
        createButton(R.id.button2, TabHost2.class);
        createButton(R.id.button3, TabHost3.class);
    }
    
    private void createButton(int id, final Class<?> activityClass) {
        Button button = (Button) findViewById(id);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabHostTestActivity.this, activityClass);
                startActivity(intent);
            }
        });
    }
}