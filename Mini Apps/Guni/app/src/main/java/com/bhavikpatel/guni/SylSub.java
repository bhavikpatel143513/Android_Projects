package com.bhavikpatel.guni;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class SylSub {
    private String subject;
    private final String SUB = "subject";

    public SylSub(JSONObject jo){
        try {
            subject = jo.getString(SUB);
        } catch (JSONException e) {
            Log.i("BHAVIK","subject = jo.getString(SUB); Line : 18 in SylSub.java");
        }
    }

    public SylSub() {
    }

    public SylSub(String subject) {

        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(SUB,subject);
        } catch (JSONException e) {

            Log.i("BHAVIK"," jo.put(SUB,subject); in SylSub.java");
        }
        return jo;
    }
}
