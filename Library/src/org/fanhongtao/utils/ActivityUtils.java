/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ActivityUtils {

    public static final void hideInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        //得到InputMethodManager的实例
        if (imm.isActive()) {
            //如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }
}
