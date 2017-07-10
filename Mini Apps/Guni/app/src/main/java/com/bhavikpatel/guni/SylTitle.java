package com.bhavikpatel.guni;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 05-Jun-17.
 */

public class SylTitle {
    private String title;
    private final String TITLE = "title";

    public SylTitle(String title) {
        this.title = title;
    }

    public SylTitle() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SylTitle (JSONObject jo){
        try {
            title = jo.getString(TITLE);
        } catch (JSONException e) {
            Log.i("BHAVIK","title = jo.getString(TITLE); in SylTitle.java");
        }
    }

    public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(TITLE,title);
        } catch (JSONException e) {
            Log.i("BHAVIK","jo.put(TITLE,title); in SylTitle.java");
        }
        return jo;
    }

}
