package com.bhavikpatel.guni;

import android.content.Context;

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
 * Created by BHAVIK PATEL on 03-Jun-17.
 */


public class CreaditsJSONSerializer {

    private String mFileName;
    private Context mContext;

    public CreaditsJSONSerializer(String mFileName, Context mContext) {
        this.mFileName = mFileName;
        this.mContext = mContext;
    }

    public ArrayList<Creadit> load()throws IOException,JSONException{
        ArrayList<Creadit> creadits = new ArrayList<>();
        BufferedReader reader  = null;
        try{
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0 ; i < jsonArray.length() ; i++){
                creadits.add(new Creadit(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){}
        finally {

            if (reader != null) {
                reader.close();
            }
        }
        return creadits;
    }

    public void saveCreadits(ArrayList<Creadit> creadits){
        JSONArray jsonArray = new JSONArray();
        try{
            for(Creadit c : creadits){
                jsonArray.put(c.convertToJSONObject());
            }
        }catch (JSONException e){}
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
}






















