package com.zhangpeng.administrator.gson;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class Result {
    public  tv mtv;
    public static class tv{
        String createTime  ;
        String firstCategory ;
        int id;
        String level;
        String tvAbout;
        String tvAuthor;
        String tvImageUrl;
        String tvMadeBy;
        String tvName;
        int tvpingfen;
        String twoCategory;

    }
    public List<MV> movie;
    public static class MV{
        String createTime;
        String firstCategory;
        int id;
        String level ;
        String movieAbout;
        String movieAuthor;
        String movieImageUrl;
        String movieMadeBy;
        String movieMp3Url;
        String movieName;
        String movieUrl;
        String movieZiMuUrl;
        int moviepingfen;
        String twoCategory;
    }
    public tv getTv(){
        return  mtv;
    }
    public int getList(){
        return movie.size();
    }
}
