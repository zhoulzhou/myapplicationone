package com.example.zhou.mutilthread;

import android.util.Log;

public class Rabbit extends Animal{
    private static final String TAG = "Rabbit";
    public Rabbit(){
        setName("Rabbit");
    }

    @Override
    public void running() {
        int dis = 5;
        length -= dis;
        Log.e(TAG, "Rabbit run 5 Then " + length);
        if(length <= 0){
            length = 0;
            if(calltoback != null){
                Log.e(TAG, "Rabbit win");
                calltoback.win();
            }
        }

        try{
            if((2000 - length)% 20 == 0){
                sleep(1000);
                Log.e(TAG,"Rabbit sleep 1000");
            }else {
                sleep(100);
                Log.e(TAG,"Rabbit sleep 100");
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
