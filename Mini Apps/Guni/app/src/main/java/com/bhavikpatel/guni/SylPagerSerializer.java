package com.bhavikpatel.guni;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class SylPagerSerializer {
    private Context mContext;
    private String mFilename;

    public SylPagerSerializer(Context mContext, String mFilename) {
        this.mContext = mContext;
        this.mFilename = mFilename;
    }

    public ArrayList<SylSub> load(){
        BufferedReader reader = null;
        ArrayList<SylSub> sylSubs = new ArrayList<>();
        try{
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0 ; i < jsonArray.length() ; i++){
                sylSubs.add(new SylSub(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){
            Log.i("BHAVIK","InputStream in = mContext.openFileInput(mFilename); in SylPagerSerializer.java" +
                    "Error = " + e);
        }catch (IOException e) {
            Log.i("BHAVIK","while((line = reader.readLine()) != null){ in SylPagerSerializer.java");
        }catch (JSONException e) {
            Log.i("BHAVIK","JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue(); in SylPagerSerializer.java");
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sylSubs;
    }

    public void save(ArrayList<SylSub> sylSubs){
        JSONArray jsonArray = new JSONArray();
        for(SylSub sylSub : sylSubs){
            jsonArray.put(sylSub.convertToJSONObject());
        }
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            Log.i("BHAVIK","writer.write(jsonArray.toString()); in SylPagerSerializer.java");
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
