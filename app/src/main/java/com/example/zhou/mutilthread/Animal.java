package com.example.zhou.mutilthread;

public abstract class Animal extends Thread{
    public int length = 2000;

    public abstract void running();

    @Override
    public void run() {
        super.run();
        while(length > 0){
            running();
        }
    }

    public static interface Calltoback{
        public void win();
    }

    public Calltoback calltoback;
}
