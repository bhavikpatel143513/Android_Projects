package com.bhavikpatel.guni;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 04-Jun-17.
 */

public class MarksActivity extends AppCompatActivity {

    private MarksListAdapter marksListAdapter;
    public boolean hideMenu = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks_main);
        marksListAdapter = new MarksListAdapter(getApplicationContext());

        ListView marksListView = (ListView) findViewById(R.id.marksListView);
        marksListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        marksListAdapter.deleteMarks(position);
                        return true;
                    }
                }
        );
        marksListView.setAdapter(marksListAdapter);
        FloatingActionButton marksFab = (FloatingActionButton) findViewById(R.id.marksFab);
        marksFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MarksAddDialog dialog = new MarksAddDialog();
                        dialog.show(getFragmentManager(),"");
                    }
                }
        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.marks_menu,menu);
        if(hideMenu == true){
            MenuItem deleteAll = menu.findItem(R.id.marksMenuDeleteAllItem);
            deleteAll.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.marksMenuDeleteAllItem){
            marksListAdapter.deleteAllMarks();
            marksListAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MarksListAdapter extends BaseAdapter{

        private MarksJSONSerializer mSerializer;
        private ArrayList<Marks> marksList;
        private Context c;

        public MarksListAdapter(Context c){
            this.c = c;
            mSerializer = new MarksJSONSerializer("GuniStudyMarks.json",c);
            try{
                marksList = mSerializer.load();
            }
            catch (IOException e) {
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(marksList.size() != 0 ){
                hideMenu = false;
                invalidateOptionsMenu();
            }
        }
        public void saveMarks(){
            mSerializer.saveMarks(marksList);
        }
        public void addMarks(Marks m){
            marksList.add(m);
            hideMenu = false;
            invalidateOptionsMenu();
            saveMarks();
            notifyDataSetChanged();
        }
        public void deleteMarks(int position){
            marksList.remove(position);
            if(marksList.size() == 0 ){
                hideMenu = true;
                invalidateOptionsMenu();
            }
            saveMarks();
            notifyDataSetChanged();
        }
        public void deleteAllMarks(){
            marksList.removeAll(marksList);
            hideMenu = true;
            invalidateOptionsMenu();
            saveMarks();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return marksList.size();
        }

        @Override
        public Object getItem(int position) {
            return marksList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null){
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.marks_list_item,null);
            }
            TextView marksTextView = (TextView) view.findViewById(R.id.marksListItem);
            marksTextView.setText(marksList.get(position).getSubjectName() + " : " + marksList.get(position).getSubjectMarks());
            return view;
        }
    }

    public void addMarks(Marks m){
        marksListAdapter.addMarks(m);
    }
}
