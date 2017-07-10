package com.bhavikpatel.guni;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 07-Jun-17.
 */

public class AttDesActivity extends AppCompatActivity implements AttDesDia.DiaCompt{
    private String FILENAME ;
    static final String SUB = "sub";
    private AttDesListAdapter dateListAddapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.att_des_main);
        ListView list = (ListView) findViewById(R.id.attDesContentListView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.attDesContentFab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AttDesDia dia = new AttDesDia();
                        dia.show(getFragmentManager(),"");
                    }
                }
        );
        String sub = getIntent().getStringExtra(SUB);
        dateListAddapter = new AttDesListAdapter(getApplicationContext(),sub);
        FILENAME = dateListAddapter.mFileName;
        list.setAdapter(dateListAddapter);
        Toast.makeText(this,"IN ON CREATE A2",Toast.LENGTH_LONG).show();
        list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        dateListAddapter.delete(position);
                        return false;
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"IN ON DESTROY A2",Toast.LENGTH_LONG).show();
    }

    @Override
    public void addDate(AttDesDate as) {
        dateListAddapter.add(as);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dateListAddapter.save();
        Toast.makeText(this,"IN ON Save A2",Toast.LENGTH_LONG).show();
    }

    private class AttDesListAdapter extends BaseAdapter{
        private String mFileName;
        private File file;
        private ArrayList<AttDesDate> dateList;
        private Context mContext;
        private AttDesSerializer mSerializer;

        public AttDesListAdapter(Context mContext,String sub) {
            this.mContext = mContext;
            mFileName = "GuniStudyAttendenceSubDate" + sub + ".json";
            file = mContext.getFileStreamPath(mFileName);
            Log.i("BHAVIK","file = mContext.getFileStreamPath(mFileName); " + file.getAbsolutePath());
            mSerializer = new AttDesSerializer(mFileName,mContext);
            try {
                dateList = mSerializer.load();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public void save(){
            mSerializer.save(dateList);
        }
        public void add(AttDesDate date){
            dateList.add(date);
            notifyDataSetChanged();
        }
        public void delete(int p){
            dateList.remove(p);
            notifyDataSetChanged();
        }
        public void delete(){
            dateList.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return dateList.size();
        }

        @Override
        public Object getItem(int position) {
            return dateList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null){
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.att_des_list_item,null);
            }
            TextView date = (TextView) view.findViewById(R.id.attDesListItemTV);
            date.setText(dateList.get(position).getDate());
            return view;
        }
    }
}

