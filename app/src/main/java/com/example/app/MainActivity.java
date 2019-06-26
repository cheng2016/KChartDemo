package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.app.bean.NewsList;
import com.example.app.http.HttpImpl;
import com.example.app.utils.L;
import com.example.app.utils.T;

import java.util.ArrayList;
import java.util.List;

import app.example.com.firstdemo.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NewsAdapter.onCheckBoxClickListenr {
    NewsAdapter mNewsAdapter;
    NewsList mNewList = null;
    @Bind(R.id.delete)
    Button delete;
    @Bind(R.id.listView)
    ListView listView;



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        showProgressDialog("加载中...");
        HttpImpl.getInstance().getNewsList("300");
    }

    @Override
    public void onEventMainThread(Object event) {
        super.onEventMainThread(event);
        hideProgressDialog();
        if (event instanceof NewsList) {
            T.showShort(this, "请求成功！");
            NewsList response = (NewsList) event;
            this.mNewList = response;
            mNewsAdapter = new NewsAdapter(response, this);
            listView.setAdapter(mNewsAdapter);
            mNewsAdapter.setOnCheckClickListenr(this);
        }
    }

    @Override
    public void onChange(int position, boolean isChecked) {
        L.i(MainActivity.class, "checkBox is change" + isChecked);
        mNewList.getNews().get(position).setIsCheck(isChecked);
    }

    @OnClick(R.id.delete)
    public void onClick() {
        if (mNewList != null) {
            List<NewsList.News> list = new ArrayList<NewsList.News>();
            for (NewsList.News news : mNewList.getNews()) {
                if (!news.getIsCheck()) {
                    list.add(news);
                }
            }
            if (mNewsAdapter != null) {
                mNewList.setNews(list);
                mNewsAdapter.updataListView(mNewList);
            }
        }
    }
}
