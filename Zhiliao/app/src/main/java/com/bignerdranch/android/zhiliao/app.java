package com.bignerdranch.android.zhiliao;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2/16/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}
