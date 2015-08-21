/*
 * Copyright (C) 2013 The Android Open Source Project 
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

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * ClockWidget3x2
 * 
 * @author Kitami Akiya
 */

public class ClockWidgetDateSmall extends ClockWidgetBase {
    
    /** 配置されている全ウィジェットの時刻更新をかける
    /ResouceSelecterで時刻1桁ごとに対応する数字画像を呼び出して、
    /各数字のImageViewのソースを更新する*/
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            updateClock(context);
            
        }
    }

    /*インテントが受け取れるonReceiveで毎分の時刻表示処理を行う*/
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("UPDATE_CLOCK_TIME")) {
            updateClock(context);
        }
    }
    
    private void updateClock(Context context){
        AppWidgetManager ap = AppWidgetManager.getInstance(context);
        ResourceSelecter rs = new ResourceSelecter();
        ComponentName cn = new ComponentName(context,this.getClass());
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_date_small);

        /* imageViewにリソースを設定 */
        rv.setImageViewResource(R.id.hour1, rs.getH1());
        rv.setImageViewResource(R.id.hour2, rs.getH2());
        rv.setImageViewResource(R.id.min1, rs.getM1());
        rv.setImageViewResource(R.id.min2, rs.getM2());

        rv.setImageViewResource(R.id.dw, rs.getDw());

        rv.setImageViewResource(R.id.month1, rs.getMON_1());
        rv.setImageViewResource(R.id.month2, rs.getMON_2());
        rv.setImageViewResource(R.id.day1, rs.getDAY_1());
        rv.setImageViewResource(R.id.day2, rs.getDAY_2());

        ap.updateAppWidget(cn, rv);

    }

}
