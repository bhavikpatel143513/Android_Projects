package com.bhavikpatel.guni;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultFrag extends Fragment {

    private String subject;
    private MarksListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_content,container,false);
        ListView list = (ListView) view.findViewById(R.id.resultContentLV);
        TextView sub = (TextView) view.findViewById(R.id.resultContentTV);
        sub.setText(subject);
        listAdapter = new MarksListAdapter(subject,getActivity().getApplicationContext());
        list.setAdapter(listAdapter);
        list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        listAdapter.delete(position);
                        return true;
                    }
                }
        );
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        listAdapter.save();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void add(ResultMarks marks){
        this.listAdapter.add(marks);
    }

    public void deleteFile(){
        listAdapter.deleteFile();
    }

    private class MarksListAdapter extends BaseAdapter{

        private Context mContext;
        private String mFileName;
        private ArrayList<ResultMarks> marksList;
        private ResultListSerializer mSerializer;


        public MarksListAdapter(String sub,Context c) {
            this.mContext = c;
            this.mFileName = "GuniStudyResultSubject" + sub + ".json";
            mSerializer = new ResultListSerializer(mFileName,mContext);
            marksList = mSerializer.load();
        }

        public void save(){
            mSerializer.save(marksList);
        }

        public void add(ResultMarks marks){
            marksList.add(marks);
            Log.i("BHAVIK","markslist length on add = " + marksList.size());
            notifyDataSetChanged();
        }
        public void delete(int position){
            marksList.remove(position);
            Log.i("BHAVIK","markslist length on del = " + marksList.size());
            notifyDataSetChanged();
        }
        public void deleteFile(){
            marksList.clear();
            notifyDataSetChanged();
            mSerializer.deleteFile();
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
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.result_list_item,null);
            }
            TextView exam = (TextView) view.findViewById(R.id.resultListItemExamTV);
            TextView marksO = (TextView) view.findViewById(R.id.resultListItemMarksObTV);
            TextView marksT = (TextView) view.findViewById(R.id.resultListItemMarksTotalTV);
            exam.setText(marksList.get(position).getExam());
            marksO.setText("" + marksList.get(position).getMarksObtained());
            marksT.setText("" + marksList.get(position).getMarksTotal());
            return view;
        }
    }
}
