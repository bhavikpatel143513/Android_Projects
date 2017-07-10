package com.bhavikpatel.guni;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class CalanderJSONSerializer {
    private String mFileName;
    private Context mContext;

    public CalanderJSONSerializer(String fn , Context c){
        mContext = c;
        mFileName = fn;
    }
    public ArrayList<CalanderEvent> load() throws IOException, JSONException{
        List<CalanderEvent> events = new ArrayList<>();
        BufferedReader reader = null;
        try{
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0 ; i < jsonArray.length(); i++){
                events.add(new CalanderEvent(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){

        }finally {
            // This will always run
            if (reader != null)
                reader.close();
        }
        return (ArrayList<CalanderEvent> )events;
    }

    public void save(List<CalanderEvent> events ) throws IOException{
        JSONArray jsonArray = new JSONArray();
        try {
            for(CalanderEvent n : events){
                jsonArray.put(n.convertToJSON());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFileName,mContext.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        }finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}
