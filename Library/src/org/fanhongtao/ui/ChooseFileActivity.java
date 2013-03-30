/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.fanhongtao.R;
import org.fanhongtao.widget.ReusedArrayAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
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
public class ChooseFileActivity extends BaseChooseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_explorer);

        ((TextView) findViewById(R.id.title)).setText(R.string.file_choose_title);
        ((Button) findViewById(R.id.chooseButton)).setVisibility(View.INVISIBLE);
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
        ArrayList<File> fileList = new ArrayList<File>();
        fileList.addAll(getSubFolder(dir));
        fileList.addAll(getSubFile(dir));
        return fileList;
    }

    class FolderAdapter extends ReusedArrayAdapter<File> {
        class ViewHolder implements ReusedArrayAdapter.IViewHolder {
            TextView mFolderName;
            ImageView mImage;
        }

        public FolderAdapter(Context context, int resource, List<File> objects) {
            super(context, resource, objects);
        }

        protected IViewHolder createViewHolder(View convertView) {
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mImage = ((ImageView) convertView.findViewById(R.id.fileIcon));
            viewHolder.mFolderName = ((TextView) convertView.findViewById(R.id.fileName));
            ((RadioButton) convertView.findViewById(R.id.radioButton1)).setVisibility(View.GONE);
            return viewHolder;
        }

        protected void fillViewHolder(IViewHolder viewHolder, View convertView, File file) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.mFolderName.setText(file.getName());
            if (file.isDirectory()) {
                holder.mImage.setImageResource(R.drawable.lib_fileexplorer_folder);
            } else {
                holder.mImage.setImageResource(R.drawable.lib_fileexplorer_file);
            }
        }
    }

    class FolderListViewItemClick implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            File file = (File) mFileList.get(position);
            if (file.isDirectory()) {
                List<File> list = getSubItems(file);
                mCurrentDir = ((File) mFileList.get(position));
                loadList(list);
            } else {
                mSelectedFile = file;
                doSelect();
            }
        }
    }
}
