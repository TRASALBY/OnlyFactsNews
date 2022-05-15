package com.example.onlyfacts;

import java.util.ArrayList;

//신뢰도 박스 색상, 뉴스카테고리, 북마크 유무
public class Data {
    private String tv_reliabilitiy;
    private int iv_profile;
    private String tv_press;
    private String tv_time;
    private String tv_title;

    public Data(String tv_reliabilitiy, int iv_profile, String tv_press, String tv_time, String tv_title) {
        this.tv_reliabilitiy = tv_reliabilitiy;
        this.iv_profile = iv_profile;
        this.tv_press = tv_press;
        this.tv_time = tv_time;
        this.tv_title = tv_title;
    }

    public String getTv_reliabilitiy() {
        return tv_reliabilitiy;
    }

    public void setTv_reliabilitiy(String tv_reliabilitiy) {
        this.tv_reliabilitiy = tv_reliabilitiy;
    }

    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getTv_press() {
        return tv_press;
    }

    public void setTv_press(String tv_press) {
        this.tv_press = tv_press;
    }

    public String getTv_time() {
        return tv_time;
    }

    public void setTv_time(String tv_time) {
        this.tv_time = tv_time;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

}