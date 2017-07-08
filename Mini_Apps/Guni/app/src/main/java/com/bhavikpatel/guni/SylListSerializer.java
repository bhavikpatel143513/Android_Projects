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
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class SylListSerializer {
    private Context mContext;
    private String mFilename;

    public SylListSerializer(Context mContext, String mFilename) {
        this.mContext = mContext;
        this.mFilename = mFilename;
    }

    public ArrayList<SylTitle> load(){
        BufferedReader reader = null;
        ArrayList<SylTitle> sylTitles = new ArrayList<>();
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
                sylTitles.add(new SylTitle(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){
            Log.i("BHAVIK","InputStream in = mContext.openFileInput(mFilename); in SylListSerializer.java");
        } catch (IOException e) {
            Log.i("BHAVIK","while((line = reader.readLine()) != null){ in SylListSerializer.java");
        }catch (JSONException e) {
            Log.i("BHAVIK","JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue(); in SylListSerializer.java");
        }finally {
            if( reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sylTitles;
    }


    public void save(ArrayList<SylTitle> sylTitles){
        JSONArray jsonArray = new JSONArray();
        for(SylTitle sylTitle : sylTitles){
            jsonArray.put(sylTitle.convertToJSONObject());
        }
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        } catch (FileNotFoundException e) {
            Log.i("BHAVIK","out = mContext.openFileOutput(mFilename,Context.MODE_PRIVATE); in SylListSerializer.java");
        }catch (IOException e) {
            Log.i("BHAVIK","writer.write(jsonArray.toString()); in SylListSerializer.java");
        }finally {
            if(writer != null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFile(){
        try{
            File f = mContext.getDir(mFilename,Context.MODE_PRIVATE);
            f.delete();
        }catch (Exception e){
        }
    }
}
