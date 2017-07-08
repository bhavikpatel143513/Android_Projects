package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 04-Jun-17.
 */

public class Marks {

    private String subjectName;
    private String subjectMarks;

    private static final String JSON_SUBJECT_NAME = "subjectName";
    private static final String JSON_SUBJECT_MARKS = "subjectMarks";

    public Marks(){}

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(String subjectMarks) {
        this.subjectMarks = subjectMarks;
    }

    public Marks(JSONObject jo) throws JSONException {
        this.subjectMarks = jo.getString(JSON_SUBJECT_MARKS);
        this.subjectName = jo.getString(JSON_SUBJECT_NAME);
    }
    public JSONObject convertToJSONObject()throws JSONException{
        JSONObject jo = new JSONObject();
        jo.put(JSON_SUBJECT_NAME,this.subjectName);
        jo.put(JSON_SUBJECT_MARKS,this.subjectMarks);
        return jo;
    }
}