package com.bhavikpatel.guni;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class CalanderEvent {
    private String eventName;
    private String eventDate;

    private static final String JSON_EVENT_NAME = "name";
    private static final String JSON_EVENT_DATE = "date";


    public CalanderEvent(JSONObject jo)throws JSONException {
        eventName = jo.getString(JSON_EVENT_NAME);
        eventDate = jo.getString(JSON_EVENT_DATE);
    }

    /*public CalanderEvent(String name , String date){
        eventName = name;
        eventDate = date;
    }*/
    public CalanderEvent(){}

    public JSONObject convertToJSON() throws JSONException{
        JSONObject jo = new JSONObject();
        jo.put(JSON_EVENT_DATE,eventDate);
        jo.put(JSON_EVENT_NAME,eventName);
        return jo;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
