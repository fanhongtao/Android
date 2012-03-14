package org.fanhongtao.handler;

import org.fanhongtao.common.BaseActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Original code from: http://www.vogella.de/articles/AndroidPerformance/article.html
 */
public class ProcessBarActivity extends BaseActivity implements Handler.Callback {

    private static final int FINISHED = 1;
    
    private Handler handler;
    
    private ProgressBar progress;
    
    private boolean running = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "ProcessBarActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_bar);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        handler = new Handler(this);
    }
    
    // This function is call when Button is pressed. See process_bar.xml
    public void startProgress(View view) {
        if (running) {
            Toast.makeText(getApplicationContext(), "Already running", Toast.LENGTH_SHORT).show();
            return;
        }
        
        running = true;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.setProgress(value);
                        }
                    });
                }
                
                handler.sendEmptyMessage(FINISHED);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
        case FINISHED:
            running = false;
            Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
            break;
        }
        return true;
    }

}
