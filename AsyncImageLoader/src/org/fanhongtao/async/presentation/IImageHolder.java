/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

import android.widget.ImageView;

/**
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public interface IImageHolder {
    
    public String getHolderId();
    
    public void setHolderId(String id);
    
    /**
     * Returns the number of ImageView in this holder.
     * @return
     */
    public int size();
    
    /**
     * Returns the ImageView at the specified location in this holder.
     * @param index the index of the ImageView to return.
     * @return the ImageView at the specified location.
     * @throws IndexOutOfBoundsException if location < 0 || >= size()
     */
    public ImageView getImageView(int location);
}
