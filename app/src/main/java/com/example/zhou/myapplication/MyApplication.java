package com.example.zhou.myapplication;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApplication extends Application {
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = leakCanary();
    }

    private RefWatcher leakCanary(){
        //https://www.jianshu.com/p/70b8c87ea877
        //https://blog.csdn.net/O_Mccree/article/details/79607501
        if(LeakCanary.isInAnalyzerProcess(this)){
            return RefWatcher.DISABLED;
        }

        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context){
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        return myApplication.refWatcher;
    }
}
