/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.common;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/** 
 * Most of my test projects only start from an activity with some buttons, each for a different activity.<br>
 * So I write this file for the convenience of test. Sub-class can create buttons without a XML layout.<br>
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class BaseTestActivity extends BaseActivity {
    
    protected ViewGroup createContentView() {
        LinearLayout parent = new LinearLayout(this);
        parent.setOrientation(LinearLayout.VERTICAL);
        
        ScrollView sv = new ScrollView(this);
        sv.addView(parent);
        
        setContentView(sv);
        
        return parent;
    }

    protected void createButton(int id, final Class<?> activityClass) {
        Button button = (Button) findViewById(id);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseTestActivity.this, activityClass);
                startActivity(intent);
            }
        });
    }
    
    protected void createButton(ViewGroup parent, String buttonText, final Class<?> activityClass) {
        Button button = new Button(this);
        button.setText(buttonText);
        button.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        parent.addView(button);
        
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseTestActivity.this, activityClass);
                startActivity(intent);
            }
        });
    }
    
    protected void createText(ViewGroup parent, String content) {
        TextView textView = new TextView(this);
        textView.setText(content);
        parent.addView(textView);
    }
}
