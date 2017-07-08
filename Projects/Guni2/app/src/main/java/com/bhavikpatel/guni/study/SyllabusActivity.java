package com.bhavikpatel.guni.study;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bhavikpatel.guni.DBHandler;
import com.bhavikpatel.guni.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BHAVIK PATEL on 16-Jun-17.
 */

public class SyllabusActivity extends AppCompatActivity {

    private ListSyllabusAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus_main);

        //set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.syllabusMainToolbar);
        toolbar.setTitle("Syllabus");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get referrences
        ExpandableListView group = (ExpandableListView) findViewById(R.id.syllabusContentExpandableListView);
        Button addButton = (Button) findViewById(R.id.syllabusContentAddButton);
        Button searchButton = (Button) findViewById(R.id.syllabusContentSerchButton);
        EditText searchET = (EditText) findViewById(R.id.syllabusContentSearchET);

        //set Adapter
        listAdapter = new ListSyllabusAdapter(getApplicationContext());
        group.setAdapter(listAdapter);

    }

    public class ListSyllabusAdapter extends BaseExpandableListAdapter {

        private Context context;
        private ArrayList<String> listGroup;
        private HashMap<String ,ArrayList<Study.Syllabus>> child;
        private DBHandler.DBStudy dbStudy;
        private DBHandler.DBStudy.TableSyllabus dbHandler;

        public ListSyllabusAdapter(Context context) {
            this.context = context;
            dbStudy = new DBHandler.DBStudy(context,null,null,1);
            dbHandler = dbStudy.new TableSyllabus();
            listGroup = dbHandler.getListGroup();
            child = new HashMap<>();
            for(int i = 0 ; i < listGroup.size() ; i++ ){
                child.put(listGroup.get(i),dbHandler.getList(listGroup.get(i)));
            }
        }

        private class ViewHolder{
            TextView title;

            public ViewHolder(View view) {
                this.title = (TextView) view.findViewById(R.id.syllabusContentListItemTV);
            }
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view ;
            if(convertView == null){
                view = LayoutInflater.from(context).inflate(R.layout.syllabus_content_list_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            else {
                view = convertView;
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.title.setText(listGroup.get(groupPosition));
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view ;
            if(convertView == null){
                view = LayoutInflater.from(context).inflate(R.layout.syllabus_content_list_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            else {
                view = convertView;
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.title.setText(child.get(listGroup.get(groupPosition)).get(childPosition).getTitle());
            return view;
        }

        @Override
        public int getGroupCount() {
            return listGroup.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(listGroup.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return listGroup.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(listGroup.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
