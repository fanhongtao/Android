/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.widget;

import org.fanhongtao.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @attr ref org.fanhongtao.R.styleable#SearchBox_hasClearButton
 * @attr ref org.fanhongtao.R.styleable#SearchBox_hint
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class SearchBox extends LinearLayout {

    /** TextView for input search string */
    private TextView mSearchText;
    
    /** An optional button to clear search string */
    private Button mClearButton;
    
    public SearchBox(Context context) {
        this(context, null);
    }

    public SearchBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lib_searchbox, this);
        mSearchText = (TextView) findViewById(R.id.lib_searchbox_searchtext);
        mClearButton = (Button)findViewById(R.id.lib_searchbox_clear);
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchBox);
        boolean hasClearButton = a.getBoolean(R.styleable.SearchBox_hasClearButton, false);
        if (hasClearButton) {
            mClearButton.setOnClickListener(new ClearClickListener());
            mClearButton.setVisibility(View.INVISIBLE);
            mSearchText.addTextChangedListener(new Watcher());
        } else {
            mClearButton.setVisibility(View.GONE);
        }
        
        CharSequence hint = a.getString(R.styleable.SearchBox_hint);
        if (hint != null) {
            mSearchText.setHint(hint);
        }
        a.recycle();
    }
    
    public void addTextChangedListener(TextWatcher watcher) {
        mSearchText.addTextChangedListener(watcher);
    }
    
    public CharSequence getText() {
        return mSearchText.getText();
    }
    
    class ClearClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mSearchText.setText("");
        }
    }
    
    class Watcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() != 0) {
                mClearButton.setVisibility(View.VISIBLE);
            } else {
                mClearButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
