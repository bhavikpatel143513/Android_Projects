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
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class CreaditsActivity extends AppCompatActivity {

    public boolean hideMenu = true;
    private CreaditListAdapter creaditListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creadits_main);
        creaditListAdapter = new CreaditListAdapter(getApplicationContext());
        ListView creaditsLstView = (ListView) findViewById(R.id.creaditsListView);
        creaditsLstView.setAdapter(creaditListAdapter);
        creaditsLstView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        creaditListAdapter.deleteCreadits(position);
                        return true;
                    }
                }
        );
        FloatingActionButton creaditsFab = (FloatingActionButton) findViewById(R.id.creaditsFab);
        creaditsFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CreaditsAddDialog dialog = new CreaditsAddDialog();
                        dialog.show(getFragmentManager(),"");
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creadits_menu,menu);
        if(hideMenu == true){
            MenuItem deleteAll = menu.findItem(R.id.creaditsMenuDeleteAll);
            deleteAll.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.creaditsMenuDeleteAll){
            creaditListAdapter.deleteAllCreadits();
            creaditListAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private class CreaditListAdapter extends BaseAdapter{
        private CreaditsJSONSerializer mSerializer;
        private ArrayList<Creadit> creadits;
        private Context c;

        public CreaditListAdapter(Context c) {
            this.c = c;
            mSerializer = new CreaditsJSONSerializer("GuniStudyCreadits.json",c);
            try {
                creadits = mSerializer.load();
            } catch (IOException e) {
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(creadits.size() != 0 ){
                hideMenu = false;
                invalidateOptionsMenu();
            }
        }

        public void saveCreadits(){
            mSerializer.saveCreadits(creadits);
        }
        public void addCreadits(Creadit c){
            creadits.add(c);
            hideMenu = false;
            invalidateOptionsMenu();
            saveCreadits();
            notifyDataSetChanged();
        }
        public void deleteCreadits(int position){
            creadits.remove(position);
            if(creadits.size() == 0 ){
                hideMenu = true;
                invalidateOptionsMenu();
            }
            saveCreadits();
            notifyDataSetChanged();
        }
        public void deleteAllCreadits(){
            creadits.removeAll(creadits);
            hideMenu = true;
            invalidateOptionsMenu();
            saveCreadits();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return creadits.size();
        }

        @Override
        public Object getItem(int position) {
            return creadits.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null){
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.event_list_item,null);
            }
            TextView creaditTextView = (TextView) view.findViewById(R.id.eventIemText);
            creaditTextView.setText(creadits.get(position).getSubjectName() + " : " + creadits.get(position).getSubjectCreadits());
            return view;
        }
    }

    public void addCreadit(Creadit c){
        creaditListAdapter.addCreadits(c);
    }
}





























