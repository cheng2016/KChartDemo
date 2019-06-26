package com.example.app;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.bean.NewsList;
import com.example.app.utils.L;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.example.com.firstdemo.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by penghuanliang
 * 2017/1/20
 */

public class NewsAdapter extends BaseAdapter {
    NewsList newsList;
    Context context;

    public NewsAdapter(NewsList newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    public void updataListView(NewsList newsList) {
        this.newsList = newsList;
        this.notifyDataSetChanged();
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

    String ChangeDate(String s) {
        String defaultDatePattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        DateFormat format = new SimpleDateFormat("MM月dd日");
        Date date = null;
        String str = "";
        try {
            date = sdf.parse(s);
            str = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //加载布局管理器
        LayoutInflater inflater = LayoutInflater.from(context);
        //将xml布局转换为view对象
        convertView = inflater.inflate(R.layout.item_news, parent, false);
        //getView核心代码
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);//讲ViewHolder存储在View中
        } else {
            viewHolder = (ViewHolder) convertView.getTag();//重获取viewHolder
        }

        if (newsList != null && newsList.getNews().size() > 0) {
            NewsList.News bean = newsList.getNews().get(position);
            setText(viewHolder.title, bean.getTitle());
            setText(viewHolder.content, bean.getDescrip());
            setText(viewHolder.date, ChangeDate(bean.getTime()));
            Picasso.with(context).load(bean.getImgsrc1().replace("http://111.13.63.2:9803","http://testdfs.csc108.com")).fit()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.newImg);
            viewHolder.checkBox.setChecked(bean.getIsCheck());
        }

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                L.i(NewsAdapter.class, "checkBox is change" + isChecked);
                l.onChange(position, isChecked);
            }
        });
        return convertView;
    }

    void setText(TextView textView, String s) {
        if (TextUtils.isEmpty(s)) {
            textView.setText("");
            return;
        }
        textView.setText(s);
    }

    onCheckBoxClickListenr l;

    public interface onCheckBoxClickListenr {
        public void onChange(int position, boolean isChecked);
    }

    public void setOnCheckClickListenr(onCheckBoxClickListenr l) {
        this.l = l;
    }

    class ViewHolder {
        @Bind(R.id.newImg)
        ImageView newImg;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.linearLayout)
        LinearLayout linearLayout;
        @Bind(R.id.checkBox)
        CheckBox checkBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
