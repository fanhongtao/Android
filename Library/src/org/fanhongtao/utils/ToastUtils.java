/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Helper class for {@link Toast}.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ToastUtils {
    public static void show(Context context, int resId) {
        show(context, context.getText(resId));
    }
    
    public static void show(Context context, CharSequence text) {
        int duration = (text.length() > 50) ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
        Toast.makeText(context, text, duration).show();
    }
}
