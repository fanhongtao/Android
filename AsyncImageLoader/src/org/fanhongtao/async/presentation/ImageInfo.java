/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.presentation;

/**
 * Information about the image to be loaded.
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ImageInfo {
    public enum ImageType {
        APP_RESOURCE,   // Image from APK's resource
        // LOCAL_DB,       // Image from Database
        LOCAL_FILE,     // Image from local file.
        INTERNET        // Image from Internet.
    }
    
    /**
     * Image type
     */
    private ImageType type;
    
    /**
     * Image's resource id (if it's type is APP_RESOURCE)
     */
    private int resourceId;
    
    /**
     * Image file name (if it's a local image file)
     */
    private String filename;
    
    /**
     * Image URL (if it's a image from Internet)
     */
    private String url;

    /**
     * The unique ID to distinguish this image.
     */
    private String fileId = null;
    
    /**
     * The holder to draw image
     */
    private IImageHolder holder;
    
    /**
     * The location of widget in the holder
     */
    private int location;
    
    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getFileId() {
        if (fileId == null) {
            switch (type) {
            case APP_RESOURCE:
                fileId = "app_" + resourceId;
                break;
            case LOCAL_FILE:
                fileId = "file_" + filename;
                break;
            case INTERNET:
                fileId = "url_" + url;
                break;
            }
        }
        return fileId;
    }

    public IImageHolder getHolder() {
        return holder;
    }

    public void setHolder(IImageHolder holder) {
        this.holder = holder;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
