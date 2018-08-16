package com.example.zhou.mutilthread;

import android.util.Log;

import java.util.Objects;

public class Bank {
    private static final String TAG = "Bank";
    static double money = 1000;

    private void Counter(double money){
        Bank.money -= money;
        Log.e(TAG, " counter get " + money + " now " + Bank.money);
    }

    private void ATM(double money){
        Bank.money -= money;
        Log.e(TAG, " ATM get " + money + " now " + Bank.money);
    }

    public synchronized void outMoney(double money, String mode){
        if(money > Bank.money){
            Log.e(TAG, " money out return");
            return;
        }

        if(Objects.equals(mode, "ATM")){
            ATM(money);
        }else{
            Counter(money);
        }
    }
}
