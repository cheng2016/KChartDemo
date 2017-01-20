package com.example.app.http;

import com.example.app.bean.NewsList;
import com.example.app.utils.L;

import org.greenrobot.eventbus.EventBus;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Michael Smith on 2016/7/24.
 */

public class HttpImpl {
    private final static String TAG = "HttpImpl";

    private static volatile HttpImpl sInstance;
    private static volatile ServiceApi mApiClient;

    public HttpImpl() {
    }

    public ServiceApi getApiClient() {
        if (mApiClient == null) {
            synchronized (this) {
                L.i(TAG, "ServiceApi.newInstance() excute ");
                mApiClient = ServiceFactory.createRetrofit2RxJavaService(ServiceApi.class);
            }
        }
        return mApiClient;
    }

    //获取唯一单列
    public static HttpImpl getInstance() {
        if (sInstance == null) {
            synchronized (HttpImpl.class) {
                L.i(TAG, "HttpImpl.newInstance() excute ");
                sInstance = new HttpImpl();
            }
        }
        return sInstance;
    }

    private final void postEvent(Object object) {
        EventBus.getDefault().post(object);
    }

    public void getNewsList(String count) {
        getApiClient().getNewsList("L295",count)
                //               .debounce(400, TimeUnit.MILLISECONDS)//限制400毫秒的频繁http操作
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.Observer<NewsList>() {
                    @Override
                    public void onCompleted() {
                        L.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        L.e(TAG , "getNewsList 请求失败！" + throwable.toString());
                        postEvent(new FailedEvent(MessageType.GETNEWSLIST, throwable));
                    }

                    @Override
                    public void onNext(NewsList profile) {
                        postEvent(profile);
                    }
                });
    }
}
