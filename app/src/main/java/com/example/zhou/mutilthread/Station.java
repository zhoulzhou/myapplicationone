package com.example.zhou.mutilthread;

import android.util.Log;

public class Station extends Thread{
    private static final String TAG = "Station";
    private String name;
    private static int sTickets = 20;
    private static Object lock = "lock";

    public Station(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while(sTickets > 0){
            synchronized (lock){
                if(sTickets > 0){
                    sTickets--;
                    Log.e(TAG, name + " sold " + sTickets);
                }else{
                    Log.e(TAG, "tickets out");
                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
