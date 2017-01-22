package com.example.app.bean;

import java.util.List;

/**
 * Created by zhenjia.cheng
 * 2017/1/20
 */

public class NewsList {

    private List<News> news;
    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<News> getNews() {
        return news;
    }

    public final class News{
        private String descrip;
        private String funType;
        private String imgsrc1;
        private String imgsrc2;
        private String imgsrc3;
        private String layout;
        private String newsId;
        private String newsType;
        private String source;
        private String time;
        private String title;
        private String topic;

        private boolean isCheck;


        public void setDescrip(String descrip) {
            this.descrip = descrip;
        }
        public String getDescrip() {
            return descrip;
        }

        public String getFunType() {
            return funType;
        }

        public void setFunType(String funType) {
            this.funType = funType;
        }

        public void setImgsrc1(String imgsrc1) {
            this.imgsrc1 = imgsrc1;
        }
        public String getImgsrc1() {
            return imgsrc1;
        }

        public void setImgsrc2(String imgsrc2) {
            this.imgsrc2 = imgsrc2;
        }
        public String getImgsrc2() {
            return imgsrc2;
        }

        public void setImgsrc3(String imgsrc3) {
            this.imgsrc3 = imgsrc3;
        }
        public String getImgsrc3() {
            return imgsrc3;
        }

        public void setLayout(String layout) {
            this.layout = layout;
        }
        public String getLayout() {
            return layout;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getNewsType() {
            return newsType;
        }

        public void setNewsType(String newsType) {
            this.newsType = newsType;
        }

        public void setSource(String source) {
            this.source = source;
        }
        public String getSource() {
            return source;
        }

        public void setTime(String time) {
            this.time = time;
        }
        public String getTime() {
            return time;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }
        public String getTopic() {
            return topic;
        }

        public boolean getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(boolean isCheck) {
            this.isCheck = isCheck;
        }
    }
}
