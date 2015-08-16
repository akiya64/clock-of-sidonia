/*
 * Copyright (C) 2015 The Android Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package jp.skr.autumnsky.SidoniaClockWidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.text.DateFormat;
import java.util.Calendar;

/**
 *ClockWidget Base Class
 * @author akiya
 */

public abstract class ClockWidgetBase extends AppWidgetProvider {

    /**最初のウィジェットが配置されたら、AlarmManagerをセット*/
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.v("Widget", "start");
        setAlarm(context);
    }

    /**最後のWidgetが消えたら、毎分更新用のアラームマネージャーをキャンセルする*/
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.v("Widget", "end");
        PendingIntent pi = makePendingIntent(context);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }
    
    protected void setAlarm(Context context) {
        PendingIntent pi = makePendingIntent(context);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MINUTE, 1);

        Long nextMin = cal.getTimeInMillis();

        am.setRepeating(AlarmManager.RTC, nextMin, 60000, pi);

        DateFormat df = DateFormat.getDateTimeInstance() ;

        Log.v("AlarmSet", df.toString());

    }

    protected PendingIntent makePendingIntent(Context context) {
        Intent alarmIntent = new Intent(context, this.getClass());
        alarmIntent.setAction("UPDATE_CLOCK_TIME");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        return pi;
    }
    
}
