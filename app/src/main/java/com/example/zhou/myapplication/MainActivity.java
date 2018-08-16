package com.example.zhou.myapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhou.mutilthread.Bank;
import com.example.zhou.mutilthread.Consumer;
import com.example.zhou.mutilthread.LetOneStop;
import com.example.zhou.mutilthread.PersonA;
import com.example.zhou.mutilthread.PersonB;
import com.example.zhou.mutilthread.Producer;
import com.example.zhou.mutilthread.Rabbit;
import com.example.zhou.mutilthread.Station;
import com.example.zhou.mutilthread.Tortoise;
import com.example.zhou.recycleview.DividerItemDecoration;
import com.example.zhou.recycleview.NormalRecyclerViewAdapter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        trymRecyclerView();
    }

    public void trymRecyclerView(){
        setContentView(R.layout.recyclerview_main_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    public void race(){
        Rabbit rabbit = new Rabbit();
        Tortoise tortoise = new Tortoise();

        LetOneStop letOneStop1 = new LetOneStop(rabbit);
        tortoise.calltoback = letOneStop1;

        LetOneStop letOneStop2 = new LetOneStop(tortoise);
        rabbit.calltoback = letOneStop2;

        rabbit.start();
        tortoise.start();
    }

    private void outMoney(){
        Bank bank = new Bank();
        PersonB personB = new PersonB(bank,"Counter");
        PersonA personA = new PersonA(bank,"ATM");
        personA.start();
        personB.start();
    }

    private void tickets(){
        Station station1 = new Station("window1");
        Station station2 = new Station("window2");
        Station station3 = new Station("window3");
        station1.start();
        station2.start();
        station3.start();
    }

    private void producerConsumer(){
        BlockingQueue shareQueue = new LinkedBlockingDeque(10);
        Thread consThread = new Thread(new Consumer(shareQueue));
        Thread prodThread = new Thread(new Producer(shareQueue));

        prodThread.start();
        consThread.start();
    }

    private void selfView(){
//        View myView = (MyView) LayoutInflater.from(this).inflate(R.layout.my_view_layout,null);
//        setContentView(myView);

        setContentView(R.layout.my_view_layout);
    }

    private void selfViewgroup(){
        setContentView(R.layout.my_viewgroup_layout);
    }
}
