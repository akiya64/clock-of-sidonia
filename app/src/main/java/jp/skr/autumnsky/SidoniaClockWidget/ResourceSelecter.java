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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Apart time into char and select digit Png in drawable
 * 
 * @author Kitami Akiya
 */

class ResourceSelecter{

    private final SimpleDateFormat Time = new SimpleDateFormat("HHmm");
    private final String TIME_STR = Time.format(Calendar.getInstance().getTime());
    private final char[] TIME_DIGIT = TIME_STR.toCharArray();

    private final int H1 = selectPng(TIME_DIGIT[0]);
    private final int H2 = selectPng(TIME_DIGIT[1]);
    private final int M1 = selectPng(TIME_DIGIT[2]);
    private final int M2 = selectPng(TIME_DIGIT[3]);

    private final int H1L = selectPngL(TIME_DIGIT[0]);
    private final int H2L = selectPngL(TIME_DIGIT[1]);
    private final int M1L = selectPngL(TIME_DIGIT[2]);
    private final int M2L = selectPngL(TIME_DIGIT[3]);

//    private String selectPng(char ch ,char size){
//        String pngName;
//        if(size == 'l'){
//            pngName = "digit"+ch +"l";
//        }
//        else{
//            pngName = "digit"+ch;
//        }
//
//        return getResources().getIdentifier(pngName, "drawable","jp.skr.autumnsky.SidoniaClockWidget");
//    }

    /** 数字1文字のcharを受け取って対応するpngリソースIDをセット
     * 時刻スモールサイズ*/
    private int selectPng(char ch) {
        int id;
        switch (ch) {
            case '0':
                id = R.drawable.digit0;
                break;
            case '1':
                id = R.drawable.digit1;
                break;
            case '2':
                id = R.drawable.digit2;
                break;
            case '3':
                id = R.drawable.digit3;
                break;
            case '4':
                id = R.drawable.digit4;
                break;
            case '5':
                id = R.drawable.digit5;
                break;
            case '6':
                id = R.drawable.digit6;
                break;
            case '7':
                id = R.drawable.digit7;
                break;
            case '8':
                id = R.drawable.digit8;
                break;
            case '9':
                id = R.drawable.digit9;
                break;
            default:
                id = R.drawable.digit0;
        }
        return id;
    }
    /** 数字1文字のcharを受け取って対応するpngリソースIDをセット
     * 時刻ラージサイズ*/
    private int selectPngL(char ch) {
        int id;
        switch (ch) {
            case '0':
                id = R.drawable.digit0l;
                break;
            case '1':
                id = R.drawable.digit1l;
                break;
            case '2':
                id = R.drawable.digit2l;
                break;
            case '3':
                id = R.drawable.digit3l;
                break;
            case '4':
                id = R.drawable.digit4l;
                break;
            case '5':
                id = R.drawable.digit5l;
                break;
            case '6':
                id = R.drawable.digit6l;
                break;
            case '7':
                id = R.drawable.digit7l;
                break;
            case '8':
                id = R.drawable.digit8l;
                break;
            case '9':
                id = R.drawable.digit9l;
                break;
            default:
                id = R.drawable.digit0l;
        }
        return id;
    }

    private final SimpleDateFormat DATE = new SimpleDateFormat("MMdd");
    private final String DATE_STR = DATE.format(Calendar.getInstance().getTime());
    private final char[] DATE_DIGIT = DATE_STR.toCharArray();

    private final int MON_1 = selectPngD(DATE_DIGIT[0]);
    private final int MON_2 = selectPngD(DATE_DIGIT[1]);
    private final int DAY_1 = selectPngD(DATE_DIGIT[2]);
    private final int DAY_2 = selectPngD(DATE_DIGIT[3]);

    /** 数字1文字のcharを受け取って対応するpngリソースIDをセット */
    private int selectPngD(char ch) {
        int id;
        switch (ch) {
            case '0':
                id = R.drawable.digit_d0;
                break;
            case '1':
                id = R.drawable.digit_d1;
                break;
            case '2':
                id = R.drawable.digit_d2;
                break;
            case '3':
                id = R.drawable.digit_d3;
                break;
            case '4':
                id = R.drawable.digit_d4;
                break;
            case '5':
                id = R.drawable.digit_d5;
                break;
            case '6':
                id = R.drawable.digit_d6;
                break;
            case '7':
                id = R.drawable.digit_d7;
                break;
            case '8':
                id = R.drawable.digit_d8;
                break;
            case '9':
                id = R.drawable.digit_d9;
                break;
            default:
                id = R.drawable.digit_d0;
        }
        return id;
    }

    private final Calendar cal = Calendar.getInstance();
    private final int DW = selectPngWd(cal.get(Calendar.DAY_OF_WEEK));

    private int selectPngWd(int wd) {
        int id;
        switch (wd) {
            case '1':
                id = R.drawable.wd_1;
                break;
            case '2':
                id = R.drawable.wd_2;
                break;
            case '3':
                id = R.drawable.wd_3;
                break;
            case '4':
                id = R.drawable.wd_4;
                break;
            case '5':
                id = R.drawable.wd_5;
                break;
            case '6':
                id = R.drawable.wd_6;
                break;
            case '7':
                id = R.drawable.wd_7;
                break;
            default:
                id = R.drawable.wd_1;
        }
        return id;
    }

    public int getH1() {
        return this.H1;
    }

    public int getH2() {
        return this.H2;
    }

    public int getM1() {
        return this.M1;
    }

    public int getM2() {
        return this.M2;
    }

    public int getH1L() {
        return this.H1L;
    }

    public int getH2L() {
        return this.H2L;
    }

    public int getM1L() {
        return this.M1L;
    }

    public int getM2L() {
        return this.M2L;
    }

    public int getMON_1() {
        return this.MON_1;
    }

    public int getMON_2() {
        return this.MON_2;
    }

    public int getDAY_1() {
        return this.DAY_1;
    }

    public int getDAY_2() {
        return this.DAY_2;
    }

    public int getDw() {
        return this.DW;
            }

}
