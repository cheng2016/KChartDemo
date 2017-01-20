package com.example.app;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.bean.NewsList;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.example.com.firstdemo.R;

/**
 * Created by penghuanliang
 * 2017/1/20
 */

public class NewsAdapter extends BaseAdapter{
    NewsList newsList;
    Context context;

    public NewsAdapter(NewsList newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsList.getNews().size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.getNews().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    String ChangeDate(String date){
        String defaultDatePattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date_temp = null;
        String str = "";

        try {
            date_temp  = sdf.parse(date);
            str = format.format(date_temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局管理器
        LayoutInflater inflater = LayoutInflater.from(context);
        //将xml布局转换为view对象
        convertView = inflater.inflate(R.layout.item_news,parent, false);
        //getView核心代码
        ViewHolder viewHolder =null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
            viewHolder.contextTv = (TextView) convertView.findViewById(R.id.content);
            viewHolder.dateTv = (TextView) convertView.findViewById(R.id.date);
            viewHolder.newsImg = (ImageView) convertView.findViewById(R.id.newImg);
            convertView.setTag(viewHolder);//讲ViewHolder存储在View中
        }else{
            viewHolder = (ViewHolder) convertView.getTag();//重获取viewHolder
        }

        if(newsList!=null && newsList.getNews().size()>0){
            NewsList.News bean = newsList.getNews().get(position);
//            viewHolder.titleTv.setText(bean.getTitle());
            setText(viewHolder.titleTv,bean.getTitle());
            setText(viewHolder.contextTv,bean.getDescrip());
            setText(viewHolder.dateTv,ChangeDate(bean.getTitle()));
//            viewHolder.contextTv.setText(bean.getDescrip());
//            viewHolder.dateTv.setText(ChangeDate(bean.getTitle()));
            Picasso.with(context).load(bean.getImgsrc1()).fit()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.newsImg);
        }
        return convertView;
    }

    void setText(TextView textView,String s){
        if(TextUtils.isEmpty(s)){
            textView.setText("");
            return;
        }
        textView.setText(s);
    }

    class ViewHolder{
        TextView titleTv,contextTv,dateTv;
        ImageView newsImg;
    }

}
