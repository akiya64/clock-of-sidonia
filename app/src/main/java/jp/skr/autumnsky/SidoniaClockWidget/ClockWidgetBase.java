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
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.RemoteViews;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    }

    protected PendingIntent makePendingIntent(Context context) {
        Intent alarmIntent = new Intent(context, this.getClass());
        alarmIntent.setAction("UPDATE_CLOCK_TIME");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        return pi;
    }

    /*時刻のアップデート*/
    protected void updateClock(Context context ,RemoteViews rv){

        /*ウイジェットのイメージビュー書き換えに必要なクラスを呼ぶ*/
        AppWidgetManager ap = AppWidgetManager.getInstance(context);
        ComponentName cn = new ComponentName(context,this.getClass());

        /*画像を名前で呼ぶためにリソースクラスをセット*/
        Resources re = context.getResources();

        /*時刻から対応する数字画像のIntセット*/
        final SimpleDateFormat Time = new SimpleDateFormat("HHmm");
        final String TIME_STR = Time.format(Calendar.getInstance().getTime());
        final char[] TIME_DIGIT = TIME_STR.toCharArray();

        List<Integer> digits = new ArrayList<Integer>();

        String digitFile = "digit";

        if (this.getClass().getSimpleName().endsWith("Large")){

            digitFile += "_l_";

        }

        for(char digit: TIME_DIGIT){
            digits.add(re.getIdentifier(digitFile + digit,"drawable","jp.skr.autumnsky.SidoniaClockWidget"));
        }

        /* imageViewにリソースを設定して更新*/
        rv.setImageViewResource(R.id.hour1, digits.get(0));
        rv.setImageViewResource(R.id.hour2, digits.get(1));
        rv.setImageViewResource(R.id.min1, digits.get(2));
        rv.setImageViewResource(R.id.min2, digits.get(3));

        ap.updateAppWidget(cn, rv);
    }

    /*曜日・日付のアップデート*/
    protected void updateCalendar(Context context ,RemoteViews rv) {

        /*ウイジェットのイメージビュー書き換えに必要なクラスを呼ぶ*/
        AppWidgetManager ap = AppWidgetManager.getInstance(context);
        ComponentName cn = new ComponentName(context, this.getClass());

        /*画像を名前で呼ぶためにリソースクラスをセット*/
        Resources re = context.getResources();

        /*時刻から対応する数字画像のIntセット*/
        final SimpleDateFormat DATE = new SimpleDateFormat("MMdd");
        final String DATE_STR = DATE.format(Calendar.getInstance().getTime());
        final char[] DATE_DIGIT = DATE_STR.toCharArray();

        List<Integer> digits = new ArrayList<Integer>();

        String digitFile = "digit_d";

        if (this.getClass().getSimpleName().endsWith("Large")) { digitFile += "_l_"; }

        for (char digit : DATE_DIGIT) {
            digits.add(re.getIdentifier(digitFile + digit, "drawable", "jp.skr.autumnsky.SidoniaClockWidget"));
        }

        /*曜日画像*/
        String dwFile = "dw_";
        if (this.getClass().getSimpleName().endsWith("Large")) { dwFile += "_l_"; }

        final Calendar cal = Calendar.getInstance();
        final int DW = re.getIdentifier("dw_" + cal.get(Calendar.DAY_OF_WEEK), "drawable", "jp.skr.autumnsky.SidoniaClockWidget");

        /* imageViewにリソースを設定して更新*/
        rv.setImageViewResource(R.id.month1, digits.get(0));
        rv.setImageViewResource(R.id.month2, digits.get(1));
        rv.setImageViewResource(R.id.day1, digits.get(2));
        rv.setImageViewResource(R.id.day2, digits.get(3));

        rv.setImageViewResource(R.id.dw,DW);

        ap.updateAppWidget(cn, rv);
    }
}
