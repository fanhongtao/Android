package org.fanhongtao.activitygroup;

import org.fanhongtao.common.BaseActivity;

import android.os.Bundle;
import android.widget.EditText;

public class View1 extends BaseActivity {
    
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "View1";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view1);
        
        editText = (EditText) findViewById(R.id.editText1);
    }
    
    @Override
    protected void onResume() {
        editText.clearFocus();
        super.onResume();
    }
    
}
