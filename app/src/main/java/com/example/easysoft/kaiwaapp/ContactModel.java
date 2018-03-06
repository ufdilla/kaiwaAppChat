package com.example.easysoft.kaiwaapp;

import java.io.Serializable;

/**
 * Created by easysoft on 3/5/18.
 */

public class ContactModel {
    String Description;
    String title;
    int imgResId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
