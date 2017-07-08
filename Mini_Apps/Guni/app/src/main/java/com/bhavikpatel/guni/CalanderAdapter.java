package com.bhavikpatel.guni;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class CalanderAdapter extends BaseAdapter {
    private CalanderJSONSerializer mSerializer;
    private Context c;
    List<CalanderEvent> events ;
    public CalanderAdapter(Context c){
        this.c = c;
        mSerializer = new CalanderJSONSerializer("GuniStudyCalander.json",c);
        try{
            events = mSerializer.load();
        }catch (Exception e){
            Log.e("Loading calander: ", ""+ e);
        }
        if(events.size() != 0 ){
            ((CalanderActivity)c).hideMenu = false;
            ((CalanderActivity)c).invalidateOptionsMenu();
        }
    }
    public void saveEvents(){
        try{
            mSerializer.save(events);
        }catch (Exception e){
            Log.e("Saving Events","" + e);
        }
    }
    public void addCalanderEvent(CalanderEvent n){
        events.add(n);
        saveEvents();
        ((CalanderActivity)c).hideMenu = false;
        ((CalanderActivity)c).invalidateOptionsMenu();
        notifyDataSetChanged();
    }
    public void deleteCalanderEvent(int n){
        events.remove(n);
        saveEvents();
        if(events.size() == 0){
            ((CalanderActivity)c).hideMenu = true;
            ((CalanderActivity)c).invalidateOptionsMenu();
        }
        notifyDataSetChanged();
    }
    public void deleteAllCalanderEvents(){
        events.removeAll(events);
        saveEvents();
        ((CalanderActivity)c).hideMenu = true;
        ((CalanderActivity)c).invalidateOptionsMenu();
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.event_list_item,parent,false);
        }
        TextView nameTextView = (TextView) view.findViewById(R.id.eventIemText);
        nameTextView.setText(events.get(position).getEventName() + " : " + events.get(position).getEventDate());

        return view;
    }
}