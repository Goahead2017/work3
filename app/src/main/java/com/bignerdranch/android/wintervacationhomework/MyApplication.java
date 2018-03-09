package com.bignerdranch.android.wintervacationhomework;

import android.app.Application;
import android.view.View;

/**
 * Created by 14158 on 2018/2/22.
 */

public class MyApplication extends Application {

    /*private static MyApplication context;

    public static MyApplication getAppContext(){
        return context;
    }*/

    private View view;

    private static int WIDTH;

    public int getWIDTH(){
        return WIDTH;
    }

    public void setWIDTH(int width){
        WIDTH = width;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*context = this;*/
    }

    public View getView(){
        return view;
    }

    public void setView(View view){
        this.view = view;
    }

}

