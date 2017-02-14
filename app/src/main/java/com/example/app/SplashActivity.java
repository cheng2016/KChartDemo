package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {

    }

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
