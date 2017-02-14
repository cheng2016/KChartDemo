package com.example.app.http;

import com.example.app.bean.NewsList;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Michael Smith on 2016/7/21.
 */

public interface ServiceApi {
    public static final String baseurl = "http://accounttest.csc108.com:9800/api/news20/";

    @GET("list")
    Observable<NewsList> getNewsList(@Query("req_funType") String funType,
                                           @Query("req_count") String count);

    /*分时图url*/
    @GET(Constant.DETAILURL)
    Observable<ResponseBody> getMinutes(@Query("code") String code);
}
