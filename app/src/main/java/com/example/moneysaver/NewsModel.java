package com.example.moneysaver;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NewsModel implements Parcelable {
    private String title;
    private Integer imageId;

    public NewsModel(String title, Integer imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeStringArray(new String[] {this.title, this.imageId.toString()});
    }

    public NewsModel(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);

        this.title = data[0];
        this.imageId = Integer.valueOf(data[1]);
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator () {

        @Override
        public Object createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}
