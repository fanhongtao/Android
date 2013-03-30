/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.ui;

import java.io.File;
import java.util.List;

import org.fanhongtao.R;
import org.fanhongtao.utils.ToastUtils;
import org.fanhongtao.widget.ReusedArrayAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class ChooseFolderActivity extends BaseChooseActivity {
    private static final String TAG = "ChooseFolderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_explorer);

        ((TextView) findViewById(R.id.title)).setText(R.string.file_choose_title);
        ((Button) findViewById(R.id.chooseButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.cancelButton)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.backButton)).setOnClickListener(this);

        mPathTextView = ((TextView) findViewById(R.id.pathTextView));
        mAdapter = new FolderAdapter(this, R.layout.file_item, mFileList);
        mListView = ((ListView) findViewById(R.id.fileList));
        mListView.setOnItemClickListener(new FolderListViewItemClick());
        mListView.setAdapter(mAdapter);

        File dir = Environment.getExternalStorageDirectory();
        mCurrentDir = dir;
        loadList(getSubItems(dir));
    }

    @Override
    List<File> getSubItems(File dir) {
        return getSubFolder(dir);
    }

    class FolderAdapter extends ReusedArrayAdapter<File> {
        class ViewHolder implements ReusedArrayAdapter.IViewHolder {
            TextView mFolderName;
            RadioButton mRadio;
        }

        private RadioButton mLastRadio;

        public FolderAdapter(Context context, int resource, List<File> objects) {
            super(context, resource, objects);
        }

        protected IViewHolder createViewHolder(View convertView) {
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mFolderName = ((TextView) convertView.findViewById(R.id.fileName));
            viewHolder.mRadio = ((RadioButton) convertView.findViewById(R.id.radioButton1));
            viewHolder.mRadio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    RadioButton radioButton = (RadioButton) view;
                    if (mLastRadio != radioButton) {
                        if (mLastRadio != null) {
                            mLastRadio.setChecked(false);
                        }
                        mLastRadio = radioButton;
                    }
                    mSelectedFile = ((File) radioButton.getTag());
                    Log.i(TAG, "Select: " + mSelectedFile.getAbsolutePath());
                }
            });
            return viewHolder;
        }

        protected void fillViewHolder(IViewHolder viewHolder, View convertView, File file) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.mFolderName.setText(file.getName());
            RadioButton radioButton = holder.mRadio;
            radioButton.setChecked(file == mSelectedFile);
            radioButton.setTag(file);
        }
    }

    class FolderListViewItemClick implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            File file = (File) mFileList.get(position);
            List<File> list = getSubItems(file);
            if (list.isEmpty()) {
                ToastUtils.show(getApplicationContext(), "No sub-dirs for: " + file.getName());
            } else {
                mCurrentDir = ((File) mFileList.get(position));
                loadList(list);
            }
        }
    }
}
