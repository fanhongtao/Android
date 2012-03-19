/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class SimpleImageRefresher implements IImageRefresher {

    private IImageHolder imageHolder;

    public SimpleImageRefresher(IImageHolder imageHolder) {
        super();
        this.imageHolder = imageHolder;
    }

    @Override
    public void refreshImage(String id, int location, Bitmap bitmap) {
        ImageView imageView = imageHolder.getImageView(location);
        imageView.setImageBitmap(bitmap);
    }

}
