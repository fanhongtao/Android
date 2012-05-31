/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.page;

import org.fanhongtao.common.BaseTestActivity;

import android.os.Bundle;
import android.view.ViewGroup;

/** 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class PageTruningTestActivity extends BaseTestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        TAG = "PageTruningTestActivity";
        super.onCreate(savedInstanceState);
        
        ViewGroup parent = createContentView();
        createText(parent, "Demoes of Page Truning");
        createButton(parent, "ViewFlipper 1: Base use without amination", Flip1.class);
    }
}