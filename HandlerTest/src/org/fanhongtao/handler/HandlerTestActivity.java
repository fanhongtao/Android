/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.handler;

import org.fanhongtao.common.BaseTestActivity;

import android.os.Bundle;
import android.view.ViewGroup;

/** 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class HandlerTestActivity extends BaseTestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        TAG = "HandlerTestActivity";
        super.onCreate(savedInstanceState);

        ViewGroup parent = createContentView();
        createText(parent, "Handler Test");
        createButton(parent, "ProcessBar : Runnable & EmptyMessage", ProcessBarActivity.class);
        createButton(parent, "Loop : Link loop to a thread", LoopActivity.class);
        createButton(parent, "HandlerThread", HandlerThreadActivity.class);
    }
}