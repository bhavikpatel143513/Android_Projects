package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 04-Jun-17.
 */

public class AttendenceSub {
    private String subjectName;
    private int subjectAtt;
    private final static String SUB = "sub";
    private final static String ATT = "att";

    public AttendenceSub() {
    }

    public AttendenceSub(String subjectName, int subjectAtt) {
        this.subjectName = subjectName;
        this.subjectAtt = subjectAtt;
    }

    public AttendenceSub(JSONObject jo){
        try {
            this.subjectName = jo.getString(SUB);
            this.subjectAtt = jo.getInt(ATT);
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

    public int getSubjectAtt() {
        return subjectAtt;
    }

    public void setSubjectAtt(int subjectAtt) {
        this.subjectAtt = subjectAtt;
    }

    public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(SUB,subjectName);
            jo.put(ATT,subjectAtt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }
}
