package com.example.zhou.mutilthread;

import android.util.Log;

public class Tortoise extends Animal{
    private static final String TAG = "Tortoise";

    public Tortoise(){
        setName("Tortoise");
    }

    @Override
    public void running() {
        int dis = 2;
        length -= dis;
        Log.e(TAG, "Tortoise run 2 Then " + length);
        if(length <= 0){
            length = 0;
            if(calltoback != null){
                Log.e(TAG, "Tortoise win");
                calltoback.win();
            }
        }

        try {
            sleep(100);
            Log.e(TAG, "Tortoise sleep 100");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
