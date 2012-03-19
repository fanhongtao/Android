/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fanhongtao.async.presentation.AsyncImageLoader;
import org.fanhongtao.async.presentation.ImageInfo;
import org.fanhongtao.async.presentation.ImageInfo.ImageType;
import org.fanhongtao.async.presentation.ImageViewHolder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class AsyncImageLoaderActivity extends Activity {
    private ListView listView;
    
    private List<Map<String, Object>> data;
    
    private static AsyncImageLoaderActivity instance;
    
    public AsyncImageLoaderActivity() {
        instance = this;
    }
    
    public static Context getContext() {
        return instance;
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        listView = new ListView(this);
        data = getData();
        
        ColorListAdapter adapter = new ColorListAdapter();
        listView.setAdapter(adapter);
        
        setContentView(listView);
    }
    
    protected static List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "blue");
        map.put("image", R.drawable.color_blue);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("name", "red");
        map.put("image", R.drawable.color_red);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("name", "green");
        map.put("image", R.drawable.color_green);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("name", "yellow");
        map.put("image", R.drawable.color_yellow);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("name", "white");
        map.put("image", R.drawable.color_white);
        list.add(map);
        return list;
    }


    class ColorListAdapter extends BaseAdapter {
        
        private LayoutInflater mInflater;
        
        private AsyncImageLoader loader;
        
        public ColorListAdapter() {
            super();
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            loader = new AsyncImageLoader(listView);
        }
        
        @Override
        public int getCount() {
            return data.size();
        }
    
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }
    
        @Override
        public long getItemId(int position) {
            return position;
        }
    
        /*
         * Android will cache view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get Holder
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_3, null);
                
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.imageView1);
                holder.name = (TextView) convertView.findViewById(R.id.textView1);
                holder.addImageView(holder.image);
                
                convertView.setTag(holder);
                
                // The setting in list_item_3.xml (layout_height="40dip") does't take effect.
                // So I set view's height in the code.
                convertView.setMinimumHeight(100);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.setHolderId(Integer.toString(position));
            
            // Set content
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)getItem(position);
            //Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), (Integer)map.get("image"));
            // holder.image.setImageBitmap(bitmap);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setType(ImageType.APP_RESOURCE);
            imageInfo.setResourceId((Integer)map.get("image"));
            imageInfo.setHolder(holder);
            imageInfo.setLocation(0);
            
            loader.drawImage(convertView.getContext(), imageInfo);
            holder.name.setText((String)map.get("name"));
            
            return convertView;
        }
    }
    
    public static class ViewHolder extends ImageViewHolder {
        public ImageView image;
        public TextView  name;
    }
}