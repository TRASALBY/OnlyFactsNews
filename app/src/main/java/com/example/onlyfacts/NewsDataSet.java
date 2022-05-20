package com.example.onlyfacts;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewsDataSet {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("imglink")
    @Expose
    private String imglink;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("field")
    @Expose
    private String field;

    @SerializedName("sourcelink")
    @Expose
    private String sourcelink;

    @SerializedName("reliability")
    @Expose
    private String reliability;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImglink() {
        return imglink;
    }

    public String getTime() {
        return time;
    }

    public String getField() {
        return field;
    }

    public String getSourcelink() {
        return sourcelink;
    }

    public String getReliability() {
        return reliability;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setSourcelink(String sourcelink) {
        this.sourcelink = sourcelink;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }
}
