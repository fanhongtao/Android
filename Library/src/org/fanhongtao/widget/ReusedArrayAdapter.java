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
package org.fanhongtao.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class ReusedArrayAdapter<T> extends ArrayAdapter<T> {
    
    /**
     * Here we define an empty interface, so that {@link ReusedArrayAdapter#getView(int, View, ViewGroup)} can works.
     */
    public interface IViewHolder {};
    
    /**
     * The resource indicating what views to inflate to display the content of this
     * array adapter.
     */
    private int mResource;
    
    /**
     * The inflater used to create the view for each item.
     */
    private LayoutInflater mInflater;
    
    public ReusedArrayAdapter(Context context, int resource) {
        this(context, resource, new ArrayList<T>());
    }

    public ReusedArrayAdapter(Context context, int resource, T[] objects) {
        this(context, resource, Arrays.asList(objects));
    }

    public ReusedArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        mResource = resource;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T item = getItem(position);
        
        // Use View Holder pattern to avoid the performance problem.
        IViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
            viewHolder = createViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IViewHolder) convertView.getTag();
        }
        fillViewHolder(viewHolder, convertView, item);
        
        return convertView;
    }

    /**
     * Called by {@link #getView(int, View, ViewGroup)} to create a holder object.
     * @param convertView The view for 
     * @return Holder object
     */
    protected abstract IViewHolder createViewHolder(View convertView);
    
    /**
     * Fill values for the view holder's elements.
     * @param viewHolder The view holder to be filled.
     * @param convertView The view associated with this holder.
     * @param item The data item associated with the view.
     */
    protected abstract void fillViewHolder(IViewHolder viewHolder, View convertView, T item);
}
