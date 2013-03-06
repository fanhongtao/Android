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
        //�õ�InputMethodManager��ʵ��
        if (imm.isActive()) {
            //�������
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            //�ر�����̣�����������ͬ������������л�������ر�״̬��
        }
    }
}
