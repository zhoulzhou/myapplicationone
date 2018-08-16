package com.example.zhou.mutilthread;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private static final String TAG = "Producer";

    private final BlockingQueue shareQueue;

    public Producer(BlockingQueue shareQueue){
        this.shareQueue = shareQueue;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try{
                Log.e(TAG, "produced " + i);
                shareQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }

    }
}
