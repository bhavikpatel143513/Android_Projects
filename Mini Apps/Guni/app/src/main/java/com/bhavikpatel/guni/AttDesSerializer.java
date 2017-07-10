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
 * Created by BHAVIK PATEL on 07-Jun-17.
 */

public class AttDesSerializer {
    private String mFileName;
    private Context mContext;

    public AttDesSerializer(String mFileName, Context mContext) {
        this.mFileName = mFileName;
        this.mContext = mContext;
    }

    public ArrayList<AttDesDate> load()throws IOException,JSONException {
        ArrayList<AttDesDate> dates = new ArrayList<>();
        BufferedReader reader  = null;
        try{
            InputStream in = mContext.openFileInput(mFileName);
            File f = mContext.getDir(mFileName,Context.MODE_PRIVATE);
            Log.i("BHAVIK","A2 : " + f.getAbsolutePath());
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0 ; i < jsonArray.length() ; i++){
                dates.add(new AttDesDate(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){
            Log.i("BHAVIK","FILE NOT FOUND : "+e);
        }
        finally {

            if (reader != null) {
                reader.close();
            }
        }
        Log.i("BHAVIK","Dates Size  : "+dates.size());
        return dates;
    }

    public void save(ArrayList<AttDesDate> dates){
        JSONArray jsonArray = new JSONArray();
        for(AttDesDate c : dates){
            jsonArray.put(c.convertToJSONObject());
        }
        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFileName,mContext.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        }catch (IOException e){}
        finally {
            if(writer != null){
                try{writer.close();}catch (IOException e){}
            }
        }
    }
    public void deleteFile(){
        try{
            File f = mContext.getDir(mFileName,Context.MODE_PRIVATE);
            f.delete();
            Log.i("BHAVIK","finally " + f.getAbsolutePath());
        }catch (Exception e){
        }
    }
}
