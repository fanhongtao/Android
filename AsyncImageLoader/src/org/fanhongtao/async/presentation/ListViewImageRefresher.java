/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListViewImageRefresher implements IImageRefresher {
    
    private static final String TAG = "ListViewImageRefresher";
    
    private ListView listView;
    
    public ListViewImageRefresher(ListView listView) {
        super();
        this.listView = listView;
        
        // clear the ID of recycled view
        listView.setRecyclerListener(new RecyclerListener() {
            
            @Override
            public void onMovedToScrapHeap(View view) {
                Object tag = view.getTag();
                if (tag instanceof IImageHolder) {
                    IImageHolder holder = (IImageHolder)tag;
                    holder.setHolderId(null);
                }
            }
        });
    }

    @Override
    public void refreshImage(String holderId, int location, Bitmap bitmap) {
        Log.i(TAG, "refreshImage, holderId: " + holderId + ", location: " + location);
        for (int i=0, n = listView.getChildCount(); i < n; i++) {
            View view = listView.getChildAt(i);
            Object tag = view.getTag();
            if (!(tag instanceof IImageHolder)) {
                continue;
            }
            
            IImageHolder holder = (IImageHolder) tag;
            if (holderId.equals(holder.getHolderId())) {
                Log.i(TAG, "refreshImage, holderId: " + holderId + ", location: " + location + ", matched");
                ImageView imageView = holder.getImageView(location);
                imageView.setImageBitmap(bitmap);
                break;
            }
        }
    }

}
