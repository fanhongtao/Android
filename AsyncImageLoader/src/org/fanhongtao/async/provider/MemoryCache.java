/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async.provider;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class MemoryCache {
    private Map<String, SoftReference<Bitmap>> cache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();

    public Bitmap get(String id) {
        SoftReference<Bitmap> ref = cache.get(id);
        return (ref == null) ? null: ref.get();
    }

    public void put(String id, Bitmap bitmap) {
        cache.put(id, new SoftReference<Bitmap>(bitmap));
    }

    public void clear() {
        cache.clear();
    }
}
