package com.example.zhou.mutilthread;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private static final String TAG = "Consumer";
    private BlockingQueue shareQueue;

    public Consumer(BlockingQueue shareQueue){
        this.shareQueue = shareQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                int i = (int) shareQueue.take();
                Log.e(TAG, "consume " + i);
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }

        }

    }
}
