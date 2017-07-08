package com.bhavikpatel.guni;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultListSerializer {
    private String mFilename;
    private Context mContext;

    public ResultListSerializer(String mFilename, Context mContext) {
        this.mFilename = mFilename;
        this.mContext = mContext;
    }

    public ArrayList<ResultMarks> load(){
        ArrayList<ResultMarks> marksList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for( int i = 0 ; i < jsonArray.length() ; i++ ){
                marksList.add(new ResultMarks(jsonArray.getJSONObject(i)));
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
        return marksList;
    }

    public void save(ArrayList<ResultMarks> marksList){
        JSONArray jsonArray = new JSONArray();
        for(ResultMarks s : marksList) jsonArray.put(s.convertToJSONObject());
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename,Context.MODE_PRIVATE);
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

    public void deleteFile(){
        File f = mContext.getDir(mFilename,Context.MODE_PRIVATE);
        f.delete();
    }

}
