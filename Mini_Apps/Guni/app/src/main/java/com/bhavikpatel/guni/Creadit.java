package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class Creadit {
    private String subjectName;
    private int subjectCreadits;

    private static final String JSON_SUBJECT_NAME = "subjectName";
    private static final String JSON_SUBJECT_CREADIT = "subjectCreadit";

    /*public Creadit(String subjectName, int subjectCreadits) {
        this.subjectName = subjectName;
        this.subjectCreadits = subjectCreadits;
    }*/

    public Creadit(){}

    public Creadit(JSONObject jo) throws JSONException{
        this.subjectCreadits = jo.getInt(JSON_SUBJECT_CREADIT);
        this.subjectName = jo.getString(JSON_SUBJECT_NAME);
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectCreadits() {
        return subjectCreadits;
    }

    public void setSubjectCreadits(int subjectCreadits) {
        this.subjectCreadits = subjectCreadits;
    }

    public JSONObject convertToJSONObject()throws JSONException{
        JSONObject jo = new JSONObject();
        jo.put(JSON_SUBJECT_NAME,this.subjectName);
        jo.put(JSON_SUBJECT_CREADIT,this.subjectCreadits);
        return jo;
    }
}
