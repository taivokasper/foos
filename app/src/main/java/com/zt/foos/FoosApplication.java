package com.zt.foos;

import android.app.Application;

import io.realm.Realm;

public class FoosApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
