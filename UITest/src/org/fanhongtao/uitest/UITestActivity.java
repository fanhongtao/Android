/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.uitest;

import org.fanhongtao.common.BaseTestActivity;
import org.fanhongtao.ui.BaseChooseActivity;
import org.fanhongtao.ui.ChooseFileActivity;
import org.fanhongtao.ui.ChooseFolderActivity;
import org.fanhongtao.utils.ToastUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

/** 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class UITestActivity extends BaseTestActivity {
    private final int CHOOSE_DIR = 1;
    private final int CHOOSE_FILE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TAG = "UITestActivity";
        super.onCreate(savedInstanceState);

        ViewGroup parent = createContentView();
        createText(parent, "UI Test");
        createButton(parent, "ChooseFile", ChooseFileActivity.class, CHOOSE_FILE);
        createButton(parent, "ChooseFolder", ChooseFolderActivity.class, CHOOSE_DIR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case CHOOSE_DIR:
            if (resultCode == RESULT_OK) {
                String folder = data.getStringExtra(BaseChooseActivity.BUNDLE_SELECTED_PATH);
                ToastUtils.show(getApplicationContext(), "Choosed folder: " + folder);
            }
            break;
        case CHOOSE_FILE:
            if (resultCode == RESULT_OK) {
                String file = data.getStringExtra(BaseChooseActivity.BUNDLE_SELECTED_PATH);
                ToastUtils.show(getApplicationContext(), "Choosed file: " + file);
            }
            break;
        }
    }

}