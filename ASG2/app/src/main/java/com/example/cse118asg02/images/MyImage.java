package com.example.cse118asg02.images;

import android.content.Context;

import com.example.cse118asg02.imageDB.DBHelper;

/**
 * MyImage class to store image information for upload/download.
 */
public class MyImage {
    private String url;
    private String title;

    public MyImage(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrlString() {
        return url;
    }

    public String getTitle() {
        return title;
    }

}
