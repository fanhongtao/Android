/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fanhongtao.async.AsyncImageLoaderActivity;
import org.fanhongtao.async.presentation.ImageInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;

/**
 * Load image asynchronized, and refresh the corresponding ImageView after loading.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ImageProvider {
    private static final String TAG = "ImageProvider";
    
    /**
     * Message used to notify UI (msg.what)
     */
    public static final int REFRESH_IMAGE = 99;
    
    private static ImageProvider instance = new ImageProvider();
    
    /**
     * Image in the memory cache
     */
    private MemoryCache memoryCache = new MemoryCache();
    
    /**
     * Thread pool for load image.
     */
    private ExecutorService executor = Executors.newCachedThreadPool();
    
    private ImageProvider() {
    }

    public static ImageProvider getInstance() {
        return instance;
    }
    
    /**
     * Load image described in ImageInfo. And draw it into the ImageView .
     * @param imageInfo
     * @param handler
     */
    public Bitmap loadImage(final Context context, final ImageInfo imageInfo, final Handler handler) {
        Bitmap bitmap = memoryCache.get(imageInfo.getFileId());
        if (bitmap != null) {
            return bitmap;
        }
        
        // async load image and refresh
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                String id = imageInfo.getFileId();
                Bitmap bitmap = loadImage(context, imageInfo);
               
                // if load success, try to refresh UI 
                if (bitmap != null) {
                    dispatchMessage(imageInfo, bitmap, handler);
                    memoryCache.put(id, bitmap);
                }
            }
            
        };
        
        executor.execute(runnable);
        return null;
    }
    
    private void dispatchMessage(ImageInfo imageInfo, Bitmap bitmap, Handler handler) {
        Message msg = new Message();
        msg.what = REFRESH_IMAGE;
        msg.obj = new Pair<ImageInfo, Bitmap>(imageInfo, bitmap);
        Log.i(TAG, "dispatch message for image: " + imageInfo.getFileId() + ", holdier id: " + imageInfo.getHolder().getHolderId());
        handler.sendMessage(msg);
    }
    
    private Bitmap loadImage(Context context, ImageInfo imageInfo) {
        Bitmap bitmap = null;
        switch (imageInfo.getType()) {
        case APP_RESOURCE:
            // TODO: change the way to get context
            bitmap = BitmapFactory.decodeResource(context.getResources(), imageInfo.getResourceId());
            break;
        //TODO: support more type
        }
        return bitmap;
    }
}
