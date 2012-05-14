/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.listview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fanhongtao.common.BaseActivity;
import org.fanhongtao.widget.SearchBox;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * List view with a search-box.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ListViewWithSearchBox extends BaseActivity {
    private SearchBox searchBox;
    private ListView listView;
    
    private CountryAdapter adapter;
    private List<String> originalCountryList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "ListViewWithSearchBox";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_with_searchbox);

        searchBox = (SearchBox)findViewById(R.id.search_box);
        searchBox.addTextChangedListener(new MyTextWatcher());
        
        listView = (ListView)findViewById(R.id.listView1);
        
        originalCountryList = Arrays.asList(ListView5.COUNTRIES);
        adapter = new CountryAdapter();
        adapter.setCountryList(originalCountryList);
        listView.setAdapter(adapter);
    }
    
    class CountryAdapter extends BaseAdapter {

        private List<String> countryList;

        private LayoutInflater mInflater;
        
        public CountryAdapter() {
            super();
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        
        public void setCountryList(List<String> countryList) {
            this.countryList = countryList;
        }

        @Override
        public int getCount() {
            return countryList.size();
        }

        @Override
        public Object getItem(int position) {
            return countryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_text, null);
                
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.textView1);
                
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.name.setText(getItem(position).toString());
            return convertView;
        }
        
        class ViewHolder {
            public TextView  name;
        }
    }
    
    
    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String searchText = searchBox.getText().toString().trim().toLowerCase();
            List<String> countryList = new ArrayList<String>();
            if (searchText.length() == 0) {
                countryList.addAll(originalCountryList);
            } else {
                for (String name : originalCountryList) {
                    if (name.toLowerCase().indexOf(searchText) != -1) {
                        countryList.add(name);
                    }
                }
            }
            adapter.setCountryList(countryList);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
        
    }
    
}
