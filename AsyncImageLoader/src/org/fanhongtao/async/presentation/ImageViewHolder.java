/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

import java.util.ArrayList;
import java.util.List;

import android.widget.ImageView;

/**
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ImageViewHolder implements IImageHolder {
    protected String holderId;

    private List<ImageView> imageViews = new ArrayList<ImageView>();
    
    @Override
    public String getHolderId() {
        return holderId;
    }

    @Override
    public void setHolderId(String id) {
        this.holderId = id;
    }

    public List<ImageView> getImageViews() {
        return imageViews;
    }

    public void setImageViews(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    public void addImageView(ImageView imageView) {
        this.imageViews.add(imageView);
    }
    
    @Override
    public int size() {
        return imageViews.size();
    }

    @Override
    public ImageView getImageView(int location) {
        return imageViews.get(location);
    }

}
