package com.bhavikpatel.guni;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 05-Jun-17.
 */

public class SylFrag extends Fragment {

    private String subject;
    private SylListAdap listAdap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.syl_content,container,false);
        ListView lv = (ListView) view.findViewById(R.id.sylLV);
        TextView tv = (TextView) view.findViewById(R.id.sylTV);
        tv.setText(subject);
        listAdap = new SylListAdap(getActivity().getApplicationContext(),subject);
        lv.setAdapter(listAdap);
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        listAdap.delete(position);
                        return true;
                    }
                }
        );
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        listAdap.save();
        Log.i("BHAVIK","ON PAUSE IN FRAG");
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void putTitle(String title){
        SylTitle sylTitle = new SylTitle();
        sylTitle.setTitle(title);
        listAdap.add(sylTitle);
    }
    public void delete(){
        listAdap.deleteFile();
    }

    private class SylListAdap extends BaseAdapter{

        private final String sub;
        private final String mFileName;
        private ArrayList<SylTitle> sylTitles;
        private Context mContext;
        private SylListSerializer mSerializer;

        public SylListAdap(Context mContext, String sub) {
            this.mContext = mContext;
            this.sub = sub;
            mFileName = "GuniStudySylList" + sub + ".json";
            mSerializer = new SylListSerializer(mContext,mFileName);
            sylTitles = mSerializer.load();
        }
        public void save(){
            mSerializer.save(sylTitles);
        }

        public void add(SylTitle sylTitle){
            sylTitles.add(sylTitle);
            notifyDataSetChanged();
        }
        public void delete(int position){
            sylTitles.remove(position);
            notifyDataSetChanged();
        }
        public void delete(){
            sylTitles.clear();
            notifyDataSetChanged();
        }
        public void deleteFile(){
            delete();
            mSerializer.deleteFile();
        }

        @Override
        public int getCount() {
            return sylTitles.size();
        }

        @Override
        public SylTitle getItem(int position) {
            return sylTitles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                view = inflater.inflate(R.layout.syl_list_item,null);
            }
            TextView title = (TextView) view.findViewById(R.id.sylListItemTV);
            title.setText(sylTitles.get(position).getTitle());
            return view;
        }
    }
}

