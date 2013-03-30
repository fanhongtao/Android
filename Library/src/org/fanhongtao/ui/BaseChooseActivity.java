/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.ui;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.fanhongtao.R;
import org.fanhongtao.utils.ToastUtils;

import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public abstract class BaseChooseActivity extends Activity implements View.OnClickListener {
    public static final String BUNDLE_SELECTED_PATH = "SELECTED_PATH";

    protected ListView mListView;
    protected BaseAdapter mAdapter;
    protected List<File> mFileList = new ArrayList<File>();

    protected TextView mPathTextView;

    protected File mCurrentDir = null;
    protected File mSelectedFile;

    public void onBackPressed() {
        if (!doBack()) {
            finish();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.chooseButton) {
            doSelect();
        } else if (id == R.id.cancelButton) {
            finish();
        } else if (id == R.id.backButton) {
            doBack();
        }
    }

    boolean doBack() {
        File parentDir = mCurrentDir.getParentFile();
        if (parentDir != null) {
            mSelectedFile = null;
            mCurrentDir = parentDir;
            loadList(getSubItems(mCurrentDir));
        }
        return (parentDir != null);
    }

    void doSelect() {
        if (mSelectedFile == null) {
            ToastUtils.show(getApplicationContext(), "No folder selected");
            return;
        }

        try {
            String path = mSelectedFile.getCanonicalPath();
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_SELECTED_PATH, path);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<File> getSubFile(File dir) {
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && (!file.getName().startsWith("."));
            }
        });
        if (files == null) {
            return new ArrayList<File>();
        }

        ArrayList<File> fileList = new ArrayList<File>(Arrays.asList(files));
        Collections.sort(fileList);
        return fileList;
    }

    List<File> getSubFolder(File dir) {
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory() && file.canWrite() && (!file.getName().startsWith("."));
            }
        });
        if (files == null) {
            return new ArrayList<File>();
        }

        ArrayList<File> fileList = new ArrayList<File>(Arrays.asList(files));
        Collections.sort(fileList);
        return fileList;
    }

    abstract List<File> getSubItems(File dir);

    void loadList(List<File> fileList) {
        mSelectedFile = null;
        mFileList.clear();
        mFileList.addAll(fileList);
        mAdapter.notifyDataSetChanged();
        mPathTextView.setText(mCurrentDir.getPath());
    }
}
