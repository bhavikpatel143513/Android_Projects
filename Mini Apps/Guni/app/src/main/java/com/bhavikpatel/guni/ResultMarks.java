package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultMarks {
    private String exam;
    private int marksObtained;
    private int marksTotal;

    private static final String EXAM = "exam";
    private static final String MARKS_OBTAINED = "marksObtained";
    private static final String MARKS_TOTAL = "marksTotal";

    public ResultMarks() {
    }

    public ResultMarks(int marksObtained, String exam, int marksTotal) {
        this.marksObtained = marksObtained;
        this.exam = exam;
        this.marksTotal = marksTotal;
    }

    public ResultMarks (JSONObject jo){
        try {
            this.exam = jo.getString(EXAM);
            this.marksObtained = jo.getInt(MARKS_OBTAINED);
            this.marksTotal = jo.getInt(MARKS_TOTAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    public int getMarksTotal() {
        return marksTotal;
    }

    public void setMarksTotal(int marksTotal) {
        this.marksTotal = marksTotal;
    }

    public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(EXAM,this.exam);
            jo.put(MARKS_OBTAINED,marksObtained);
            jo.put(MARKS_TOTAL,marksTotal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }

}
