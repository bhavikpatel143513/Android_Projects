package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultSub {
    private String subjectName;
    private static final String SUBJECT = "subject";

    public ResultSub() {
    }

    public ResultSub(String subjectName) {
        this.subjectName = subjectName;
    }

    public ResultSub(JSONObject jo){
        try {
            this.subjectName = jo.getString(SUBJECT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(SUBJECT,subjectName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }
}
