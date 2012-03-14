/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.handler;

import org.fanhongtao.common.BaseActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Demo of how to link Loop to a thread.
 * 
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class LoopActivity extends BaseActivity {

    private static final int INCREASE = 1;

    private static final int DECREASE = 2;

    private int result = 0;

    private Handler uiHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "LoopTestActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loop_test);
        Log.i(TAG, "onCreate, thread-id: " + android.os.Process.myTid());
        final LooperThread thread = new LooperThread();
        thread.start();

        Button btnInc = (Button) findViewById(R.id.button_inc);
        btnInc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.mHandler.sendEmptyMessage(INCREASE);
            }
        });
        Button btnDec = (Button) findViewById(R.id.button_dec);
        btnDec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.mHandler.sendEmptyMessage(DECREASE);
            }
        });

        final TextView textView = (TextView) findViewById(R.id.textView1);
        uiHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "handleMessage, thread-id: " + android.os.Process.myTid());
                // only one kind of msg, no neeed to write a switch statement.
                textView.setText("" + msg.arg1);
            }

        };
    }

    class LooperThread extends Thread {
        Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                    case INCREASE:
                        result++;
                        break;
                    case DECREASE:
                        result--;
                        break;
                    }

                    Log.i(TAG, "Result: " + result);
                    Log.i(TAG, "thread's handdleMessage, thread-id: " + android.os.Process.myTid());
                    Message uiMsg = new Message();
                    uiMsg.what = 0;
                    uiMsg.arg1 = result;
                    uiHandler.sendMessage(uiMsg);
                }
            };

            Looper.loop();
        }
    }
}
