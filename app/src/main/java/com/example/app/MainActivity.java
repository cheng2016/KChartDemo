package com.example.app;

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

public class MainActivity extends BaseActivity implements NewsAdapter.onCheckBoxClickListenr {
    ListView mListView;
    NewsAdapter mNewsAdapter;
    Button delete;
    NewsList mNewList=null;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.newList);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNewList!=null){
                    List<NewsList.News> list = new ArrayList<NewsList.News>();
                    for (NewsList.News news: mNewList.getNews())  {
                        if(!news.getIsCheck()){
                            list.add(news);
                        }
                    }
                    if(mNewsAdapter!=null){
                        mNewList.setNews(list);
                        mNewsAdapter.updataListView(mNewList);
                    }
                }
            }
        });
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
            this.mNewList = response;
            mNewsAdapter = new NewsAdapter(response,this);
            mListView.setAdapter(mNewsAdapter);
            mNewsAdapter.setOnCheckClickListenr(this);
        }
    }

    @Override
    public void onChange(int position,boolean isChecked) {
        L.i(MainActivity.class,"checkBox is change"+isChecked);
        mNewList.getNews().get(position).setIsCheck(isChecked);
    }
}
