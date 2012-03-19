/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

import org.fanhongtao.async.provider.ImageProvider;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Load image asynchronized, and refresh the corresponding ImageView after loading.
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class AsyncImageLoader {

    private Handler uiHandler;
    private IImageRefresher refresher;
    
    public AsyncImageLoader(ListView listView) {
        refresher = new ListViewImageRefresher(listView);
        uiHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                handleMsg(msg);
            }
        };
    }
    
    /**
     * Load image described in ImageInfo. And draw it into the ImageView .
     * @param imageInfo
     */
    public void drawImage(Context context, ImageInfo imageInfo) {
        ImageProvider loader = ImageProvider.getInstance();
        Bitmap bitmap = loader.loadImage(context, imageInfo, uiHandler);
        if (bitmap != null) {
            ImageView imageView = imageInfo.getHolder().getImageView(imageInfo.getLocation());
            imageView.setImageBitmap(bitmap);
            return;
        }
    }
    
    private void handleMsg(Message msg) {
        switch (msg.what) {
        case ImageProvider.REFRESH_IMAGE:
            @SuppressWarnings("unchecked")
            Pair<ImageInfo, Bitmap> pair = (Pair<ImageInfo, Bitmap>)msg.obj;
            ImageInfo imageInfo = pair.first;
            Bitmap bitmap = pair.second;
            
            String holderId = imageInfo.getHolder().getHolderId();
            refresher.refreshImage(holderId, imageInfo.getLocation(), bitmap);
            break;
        }
    }
}
