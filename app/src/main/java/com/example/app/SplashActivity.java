package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.example.com.firstdemo.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangke on 2017/2/8.
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.news)
    Button news;
    @Bind(R.id.kChart)
    Button kChart;
    @Bind(R.id.minute)
    Button minute;


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void init() {
        Log.i(TAG,"this is main thread excute start");

        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"start current Thread: " + Thread.currentThread().getName());

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
//                Log.i(TAG,"this is new thread2 excute");
//                for (Integer i :queue){
//                    handler.sendEmptyMessageAtTime(i,10);
//                }
//                Message msg = Message.obtain();
//                msg.what = -1;
//                handler.sendMessageAtTime(msg,0);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"this is new thread1 excute");
                Message msg = Message.obtain();
                msg.what = 0;
                handler.sendMessageAtTime(msg,0);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"this is new thread2 excute");
//                for (Integer i :queue){
//                    handler.sendEmptyMessageAtTime(i,10);
//                }
            }
        }).start();
        Message message = Message.obtain();
        message.what = 2;
        handler.sendMessageAtTime(message,0);
    }

    volatile LinkedList<Integer> queue = new LinkedList();


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case -1:
                    Log.i(TAG,"start");
                    break;
                case 0:
                    Log.i(TAG,"success");
                    break;
                case 1:
                    Log.i(TAG,"fail");

                    break;
                case 2:
                    Log.i(TAG,"finish");

                    break;
                case 3:
                    Executors.newCachedThreadPool().submit(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG,"this is new Executors3 thread in heandler excute");
                            handler.sendEmptyMessage(9);
                        }
                    });
                    handler.sendEmptyMessage(91);
                    break;

            }
            Log.i(TAG,"handleMessage msg.what == "+msg.what);
        }
    };

    @OnClick({R.id.news, R.id.kChart,R.id.minute})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news:
                startActivity(new Intent().setClass(this, MainActivity.class));
                break;
            case R.id.kChart:
                startActivity(new Intent().setClass(this,KLineActivity.class));
                break;
            case R.id.minute:
                startActivity(new Intent().setClass(this,MinutesActivity.class));
                break;
        }
    }
}
