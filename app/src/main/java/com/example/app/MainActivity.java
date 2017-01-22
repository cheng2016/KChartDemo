package com.example.app;

import android.widget.ListView;

import com.example.app.bean.NewsList;
import com.example.app.http.HttpImpl;
import com.example.app.utils.T;

import app.example.com.firstdemo.R;

public class MainActivity extends BaseActivity {
    ListView mListView;
    NewsAdapter mNewsAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.newList);
    }

    @Override
    public void initData() {
        showProgressDialog("加载中...");
        HttpImpl.getInstance().getNewsList("100");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onEventMainThread(Object event) {
        super.onEventMainThread(event);
        hideProgressDialog();
        if (event instanceof NewsList){
            T.showShort(this,"请求成功！");
            NewsList response = (NewsList) event;
            mNewsAdapter = new NewsAdapter(response,this);
            mListView.setAdapter(mNewsAdapter);
//            mNewsAdapter.notifyDataSetChanged();
        }
    }
}
