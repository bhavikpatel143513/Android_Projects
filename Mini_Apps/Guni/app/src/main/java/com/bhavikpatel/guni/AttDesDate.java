package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;

/**
 * Created by BHAVIK PATEL on 07-Jun-17.
 */

public class AttDesDate {
    private String date;
    private final static String DATE = "date";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AttDesDate() {
    }

    public AttDesDate(String date) {

        this.date = date;
    }
    public AttDesDate(JSONObject jo){
        try {
            this.date = jo.getString(DATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(DATE,this.date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }
}
