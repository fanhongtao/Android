/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.taskinfo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
    
public class TaskInfoActivity extends Activity {

    private static final String TAG = "TaskInfo";

    private List<RunningTaskInfo> taskList = new ArrayList<RunningTaskInfo>();

    private ListView listView;

    private TaskInfoAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.taskinfo_listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        taskList.addAll(mngr.getRunningTasks(30));
        Log.i(TAG, "Get " + taskList.size() + " task(s)");
        adapter = new TaskInfoAdapter(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        taskList.clear();
        adapter.notifyDataSetInvalidated();
        Log.i(TAG, "Clean tasklist");
    }

    public class TaskInfoAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public TaskInfoAdapter(Context context) {
            super();
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return taskList.size();
        }

        @Override
        public Object getItem(int position) {
            return taskList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.taskinfo, null);
                holder.img = (ImageView) convertView.findViewById(R.id.taskImage);
                holder.name = (TextView) convertView.findViewById(R.id.taskName);
                holder.detail = (TextView) convertView.findViewById(R.id.taskDetail);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            RunningTaskInfo taskInfo = (RunningTaskInfo) getItem(position);
            if (taskInfo.thumbnail != null) {
                holder.img.setImageBitmap(taskInfo.thumbnail);
            }
            holder.name.setText("" + taskInfo.id + " - " + taskInfo.numRunning + "/" + taskInfo.numActivities);
            holder.detail.setText(taskInfo.topActivity.getClassName());
            
            return convertView;
        }

    }
}

class ViewHolder {
    public ImageView img;
    public TextView name;
    public TextView detail;
}
