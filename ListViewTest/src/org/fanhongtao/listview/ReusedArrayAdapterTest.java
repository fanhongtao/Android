/*
 * Copyright (C) 2012 Fan Hongtao (http://www.fanhongtao.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fanhongtao.listview;

import java.util.ArrayList;
import java.util.List;

import org.fanhongtao.widget.ReusedArrayAdapter;
import org.fanhongtao.widget.ReusedArrayAdapter.IViewHolder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ReusedArrayAdapterTest extends Activity {

    private ListView listView;
    
    private List<Item> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = createData(20);
        listView = new ListView(this);
        MyAdapter adapter = new MyAdapter(this, R.layout.list_item_3, data);
        listView.setAdapter(adapter);
        setContentView(listView);
    }

    protected List<Item> createData(int count) {
        List<Item> list = new ArrayList<Item>();
        for (int i=0; i<count; i++) {
            list.add(new Item("blue-" + i, R.drawable.color_blue));
            list.add(new Item("red-" + i, R.drawable.color_red));
            list.add(new Item("green-" + i, R.drawable.color_green));
            list.add(new Item("yellow-" + i, R.drawable.color_yellow));
            list.add(new Item("white-" + i, R.drawable.color_white));
        }
        return list;
    }
    
    class Item {
        public String name;
        public int colorResourceId;
        
        public Item(String name, int colorResourceId) {
            this.name = name;
            this.colorResourceId = colorResourceId;
        }
    }
    
    class ViewHolder implements IViewHolder {
        public ImageView image;
        public TextView  name;
    }
    
    class MyAdapter extends ReusedArrayAdapter<Item> {

        public MyAdapter(Context context, int resource, List<Item> objects) {
            super(context, resource, objects);
        }

        @Override
        protected IViewHolder createViewHolder(View convertView) {
            ViewHolder holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            return holder;
        }

        @Override
        protected void fillViewHolder(IViewHolder viewHolder, View convertView, Item item) {
            ViewHolder holder = (ViewHolder) viewHolder;
            
            Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), item.colorResourceId);
            holder.image.setImageBitmap(bitmap);
            holder.name.setText(item.name);
        }
    }
}
