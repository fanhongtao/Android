/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

import android.graphics.Bitmap;

/**
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public interface IImageRefresher {
    public void refreshImage(String id, int location, Bitmap bitmap);
}
