package com.gamecodeschool.whereitssnaps;

import android.net.Uri;

/**
 * Created by BHAVIK PATEL on 19-May-17.
 */

public class Photo {

    private String mTitle;
    private Uri mStorageLocation;
    private String mTag1;
    private String mTag2;
    private String mTag3;

    public String getTag1() {
        return mTag1;
    }

    public void setTag1(String mTag1) {
        this.mTag1 = mTag1;
    }

    public String getTag2() {
        return mTag2;
    }

    public void setTag2(String mTag2) {
        this.mTag2 = mTag2;
    }

    public String getTag3() {
        return mTag3;
    }

    public void setTag3(String mTag3) {
        this.mTag3 = mTag3;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Uri getStorageLocation() {
        return mStorageLocation;
    }

    public void setStorageLocation(Uri mStorageLocation) {
        this.mStorageLocation = mStorageLocation;
    }
}
