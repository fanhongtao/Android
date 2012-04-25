/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.listview;

import java.util.List;
import java.util.Map;

import org.fanhongtao.common.BaseActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Demo of using different list-item height.<br>
 * The item's layout contains 2 sub-layouts. One is for title, the other is for content.<br>
 * Only one of them will be visible at specific time.<br>
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListView6 extends BaseActivity {
private ListView listView;
    
    private List<Map<String, Object>> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "ListView6";
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        data = ListView3.getData(20, true);
        ColorListAdapter adapter = new ColorListAdapter();
        listView.setAdapter(adapter);
        setContentView(listView);
    }
    
    class ColorListAdapter extends BaseAdapter {
        
        private LayoutInflater mInflater;
        
        public ColorListAdapter() {
            super();
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                convertView = mInflater.inflate(R.layout.list_item_6, null);
                
                holder = new ViewHolder();
                holder.titleLayout = (LinearLayout) convertView.findViewById(R.id.title_layout);
                holder.contentLayout = (LinearLayout) convertView.findViewById(R.id.content_layout);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView1);
                holder.name = (TextView) convertView.findViewById(R.id.textView1);
                
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)getItem(position);
            String name = (String)map.get("name");
            
            if (name.startsWith("Group-")) {
                holder.titleLayout.setVisibility(View.VISIBLE);
                holder.contentLayout.setVisibility(View.GONE);
                
                holder.title.setText(name);
                return convertView;
            }
            
            holder.titleLayout.setVisibility(View.GONE);
            holder.contentLayout.setVisibility(View.VISIBLE);
            
            // Set content
            Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), (Integer)map.get("image"));
            holder.image.setImageBitmap(bitmap);
            holder.name.setText(name);
            
            return convertView;
        }
    }
    
    public static class ViewHolder {
        public LinearLayout titleLayout;
        public LinearLayout contentLayout;
        public TextView title;
        public ImageView image;
        public TextView  name;
    }
}
