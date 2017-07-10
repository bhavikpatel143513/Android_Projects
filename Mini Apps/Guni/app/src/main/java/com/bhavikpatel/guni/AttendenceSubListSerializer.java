package com.bhavikpatel.guni;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 04-Jun-17.
 */

public class AttendenceSubListSerializer {
    private String mFileName;
    private Context mContext;

    public AttendenceSubListSerializer(String mFileName, Context mContext) {
        this.mFileName = mFileName;
        this.mContext = mContext;
    }

    public ArrayList<AttendenceSub> load(){
        ArrayList<AttendenceSub> subList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFileName);
            File f = mContext.getDir(mFileName,Context.MODE_PRIVATE);
            Log.i("BHAVIK","A1    =      "+f.getAbsolutePath());
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i=0 ; i<jsonArray.length(); i++) {
                subList.add(new AttendenceSub(jsonArray.getJSONObject(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return subList;
    }
    public void save(ArrayList<AttendenceSub> subList){
        JSONArray jsonArray = new JSONArray();
        for(AttendenceSub as : subList){
            jsonArray.put(as.convertToJSONObject());
        }
        Writer writer = null;

        try {
            OutputStream out = mContext.openFileOutput(mFileName,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
