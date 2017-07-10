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

public class AttendenceActivity extends AppCompatActivity {

    private ListAttendenceAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_main);

        //set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.attendenceMainToolbar);
        toolbar.setTitle("Attendence");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get referrences
        ExpandableListView group = (ExpandableListView) findViewById(R.id.attendenceContentExpandableListView);
        Button addButton = (Button) findViewById(R.id.dataContentAddButton);
        Button searchButton = (Button) findViewById(R.id.attendenceContentSearchButton);
        EditText searchET = (EditText) findViewById(R.id.attendenceContentSearchET);

        //set Adapter
        listAdapter = new ListAttendenceAdapter(getApplicationContext());
        group.setAdapter(listAdapter);

    }

    public class ListAttendenceAdapter extends BaseExpandableListAdapter {

        private Context context;
        private ArrayList<Study.AttendenceGroup> listGroup;
        private HashMap<String ,ArrayList<Study.Attendence>> child;
        private DBHandler.DBStudy dbStudy;
        private DBHandler.DBStudy.TableAttendence dbHandler;

        public ListAttendenceAdapter(Context context) {
            this.context = context;
            dbStudy = new DBHandler.DBStudy(context,null,null,1);
            dbHandler = dbStudy.new TableAttendence();
            listGroup = dbHandler.getListGroup();
            child = new HashMap<>();
            for(int i = 0 ; i < listGroup.size() ; i++ ){
                child.put(listGroup.get(i).getTitle(),dbHandler.getList(listGroup.get(i).getTitle()));
            }
        }

        private class GroupViewHolder{
            TextView title,att;

            public GroupViewHolder(View view) {
                this.title = (TextView) view.findViewById(R.id.attendenceContentListItemTitleTV);
                this.att = (TextView) view.findViewById(R.id.attendenceContentListItemAttTV);
            }
        }

        private class ChildViewHolder{
            TextView date,att;

            public ChildViewHolder(View view) {
                this.date = (TextView) view.findViewById(R.id.attendenceContentChildListItemDateTV);
                this.date = (TextView) view.findViewById(R.id.attendenceContentChildListItemAttTV);
            }
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view ;
            if(convertView == null){
                view = LayoutInflater.from(context).inflate(R.layout.attendence_content_group_list_item,null);
                GroupViewHolder holder = new GroupViewHolder(view);
                view.setTag(holder);
            }
            else {
                view = convertView;
            }
            GroupViewHolder holder = (GroupViewHolder) view.getTag();
            holder.title.setText(listGroup.get(groupPosition).getTitle());
            holder.att.setText(Study.Attendence.getPercent(child.get(listGroup.get(groupPosition))) + "%");
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view ;
            if(convertView == null){
                view = LayoutInflater.from(context).inflate(R.layout.attendence_content_child_list_item,null);
                ChildViewHolder holder = new ChildViewHolder(view);
                view.setTag(holder);
            }
            else {
                view = convertView;
            }
            ChildViewHolder holder = (ChildViewHolder) view.getTag();
            holder.date.setText(child.get(listGroup.get(groupPosition)).get(childPosition).getDate());
            holder.att.setText(child.get(listGroup.get(groupPosition)).get(childPosition).getPresent() +
                    " / " + child.get(listGroup.get(groupPosition)).get(childPosition).getNoOfClass());
            return view;
        }

        @Override
        public int getGroupCount() {
            return listGroup.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(listGroup.get(groupPosition).getTitle()).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return listGroup.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(listGroup.get(groupPosition).getTitle()).get(childPosition);
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
