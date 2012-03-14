/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.ringmode;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * This program is used to switch mobile audio mode by one touch.
 * The mode is change in sequence: NORMAL -> SILENT -> VIBRATE -> NORMAL (again)
 * 
 * @author Fan Hongtao <fanhongtao@gmail.com>
 *
 */
public class RingModeWidgetProvider extends AppWidgetProvider {
    
    private static final String TAG = "RingModeWidgetProvider";
    
    private static final String ACTION_CLICK_NAME = "NAME.FHT.ACTION.WIDGET.RINGMODE.CLICK";
    
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        
        final int N = appWidgetIds.length;
        Log.i(TAG, "Widget update: " + N);
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to broadcast message.
            Intent intent = new Intent(ACTION_CLICK_NAME);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the imageView
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setOnClickPendingIntent(R.id.imageView1, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Widget onReceive: " + intent);
        if (intent.getAction().equals(ACTION_CLICK_NAME)) {
            changeAudioMode(context);
        } else {
            super.onReceive(context, intent);
        }
    }
    
    private void changeAudioMode(Context context) {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        int ringMode = audioManager.getRingerMode();
        int target = AudioManager.RINGER_MODE_NORMAL;
        switch (ringMode) {
        case AudioManager.RINGER_MODE_NORMAL:
            target = AudioManager.RINGER_MODE_SILENT;
            break;
        case AudioManager.RINGER_MODE_SILENT:
            target = AudioManager.RINGER_MODE_VIBRATE;
            break;
        default:
            target = AudioManager.RINGER_MODE_NORMAL;
            break;
        }
        audioManager.setRingerMode(target);
    }
}
