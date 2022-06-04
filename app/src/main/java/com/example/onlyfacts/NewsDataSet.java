package com.example.onlyfacts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewsDataSet implements Parcelable {

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


    public NewsDataSet(Parcel in) {
        readFromParcel(in);
    }
    @Ignore
    public NewsDataSet(String id) {
        this(id, "null", "null", "null", "null", "null", "null", "null");
    }

    public NewsDataSet(String id, String title, String body, String imglink, String time, String field, String sourcelink, String reliability){
        this.id = id;
        this.title = title;
        this.body = body;
        this.imglink = imglink;
        this.time = time;
        this.field = field;
        this.sourcelink = sourcelink;
        this.reliability = reliability;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsDataSet> CREATOR = new Creator<NewsDataSet>() {
        @Override
        public NewsDataSet createFromParcel(Parcel in) {
            return new NewsDataSet(in);
        }

        @Override
        public NewsDataSet[] newArray(int size) {
            return new NewsDataSet[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(imglink);
        dest.writeString(time);
        dest.writeString(field);
        dest.writeString(sourcelink);
        dest.writeString(reliability);
    }

    private void readFromParcel(Parcel in){
        id = in.readString();
        title = in.readString();
        body = in.readString();
        imglink = in.readString();
        time = in.readString();
        field = in.readString();
        sourcelink = in.readString();
        reliability = in.readString();
    }


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
