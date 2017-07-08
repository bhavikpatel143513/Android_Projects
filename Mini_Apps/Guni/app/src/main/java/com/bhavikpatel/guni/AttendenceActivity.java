package com.bhavikpatel.guni;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 04-Jun-17.
 */

public class AttendenceActivity extends AppCompatActivity implements AttendenceDiaSub.DiaSubCompt{

    private SubListAdapter subListAdapter;
    private ListView subList;
    private boolean isItemLongClicked = false;
    private boolean isAllSelected = false;
    private ArrayList<Integer> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_main);
        subList = (ListView) findViewById(R.id.attendenceSubListView);
        FloatingActionButton subFab = (FloatingActionButton) findViewById(R.id.attendenceMainFab);
        subFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AttendenceDiaSub diaSub = new AttendenceDiaSub();
                        diaSub.show(getFragmentManager(),"");
                    }
                }
        );
        subListAdapter = new SubListAdapter(getApplicationContext());
        subList.setAdapter(subListAdapter);
        subList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        if(selectedItems.contains(position)) {
                            deSelect(view);
                            selectedItems.remove(position);
                            if(selectedItems.size() == 0){
                                isItemLongClicked = false;
                                invalidateOptionsMenu();
                            }
                        }
                        else{
                            isItemLongClicked = true;
                            invalidateOptionsMenu();
                            select(view);
                            selectedItems.add(position);
                        }
                        return true;
                    }
                    void select(View view){
                        view.setBackgroundColor(Color.parseColor("#aaaaff"));//Color.argb(255,200,200,200));
                    }
                    void deSelect(View view){
                        view.setBackgroundColor(Color.parseColor("#fafafa"));
                    }
                }
        );
        subList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(isItemLongClicked == true){
                            if(selectedItems.contains(position)) {
                                deSelect(view);
                                selectedItems.remove(new Integer(position));
                                isAllSelected = false;
                                invalidateOptionsMenu();
                                if(selectedItems.size() == 0){
                                    isItemLongClicked = false;
                                }
                            }
                            else{
                                select(view);
                                selectedItems.add(position);
                                if(selectedItems.size() == subListAdapter.getCount()){
                                    isAllSelected = true;
                                    invalidateOptionsMenu();
                                }
                            }
                        }else {
                            Intent iDates = new Intent(getApplicationContext(),AttDesActivity.class);
                            String sub = ((TextView) view.findViewById(R.id.attendenceSubListItemSubTV)).getText().toString();
                            iDates.putExtra(AttDesActivity.SUB,sub);
                            startActivity(iDates);
                        }
                    }
                    void select(View view){
                        view.setBackgroundColor(Color.parseColor("#aaaaff"));//Color.argb(255,200,200,200));
                    }
                    void deSelect(View view){
                        view.setBackgroundColor(Color.parseColor("#fafafa"));
                    }
                }
        );

    }

    @Override
    protected void onPause() {
        super.onPause();
        subListAdapter.save();
    }

    @Override
    public void addAttendenceSub(AttendenceSub as) {
        subListAdapter.add(as);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendence_menu_sub,menu);
        if(isItemLongClicked){
            MenuItem delete = menu.findItem(R.id.attendenceMenuDelete);
            MenuItem selectAll = menu.findItem(R.id.attendenceMenuSelectAll);
            delete.setVisible(true);
            selectAll.setVisible(true);
        }
        if(isAllSelected){
            MenuItem selectAll = menu.findItem(R.id.attendenceMenuSelectAll);
            selectAll.setIcon(R.drawable.menu_select_all_checked);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.attendenceMenuSelectAll){
            isAllSelected = !isAllSelected;
            if(isAllSelected){
                selectedItems.clear();
                for(int i = 0 ; i < subListAdapter.getCount(); i++){
                    selectedItems.add(i);
                }
            }
            else {
                selectedItems.clear();
                isItemLongClicked = false;
                invalidateOptionsMenu();
            }
            subListAdapter.notifyDataSetChanged();
            invalidateOptionsMenu();
        }
        if(id == R.id.attendenceMenuDelete){
            subListAdapter.delete();
            isItemLongClicked = false;
            invalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(item);
    }


    private class SubListAdapter extends BaseAdapter implements Checkable{

        private ArrayList<AttendenceSub> subList;
        private Context mContext;
        private AttendenceSubListSerializer mSerializer;


        public SubListAdapter(Context context) {
            this.mContext = context;
            mSerializer = new AttendenceSubListSerializer("GuniStudyAttendenceSub.json",mContext);
            subList = mSerializer.load();
        }

        public void save(){
            mSerializer.save(subList);
        }

        public void add(AttendenceSub as){
            subList.add(as);
            notifyDataSetChanged();
        }
        public void delete(){
            File f = null;
            AttDesSerializer s;
            for(int i = 0 ; i < selectedItems.size() ; i++){
                //deleting the dates list

                String sub = subList.get((int)selectedItems.get(i)).getSubjectName();
               /* f = mContext.getDir("GuniStudyAttendenceSubDate" + sub + ".json",Context.MODE_PRIVATE);//"GuniStudyAttendenceSubDate" + sub + ".json"
                Log.i("BHAVIK","DATES files delete : " + f.getAbsolutePath());
                f.delete();*/
                String fileName = "GuniStudyAttendenceSubDate" + sub + ".json";
                s = new AttDesSerializer(fileName,mContext);
                s.deleteFile();
                subList.remove((int)selectedItems.get(i));
            }
            selectedItems.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return subList.size();
        }

        @Override
        public Object getItem(int position) {
            return subList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.attendence_sublist_item,null);
            }
            TextView sub = (TextView) view.findViewById(R.id.attendenceSubListItemSubTV);
            TextView att = (TextView) view.findViewById(R.id.attendenceSubListItemAttTV);
            sub.setText(subList.get(position).getSubjectName());
            att.setText("" + subList.get(position).getSubjectAtt());
            if(isAllSelected){
                view.setBackgroundColor(getColor(R.color.guniBackgroundColor));
            }else view.setBackgroundColor(Color.parseColor("#fafafa"));
            return view;
        }

        @Override
        public void setChecked(boolean checked) {

        }

        @Override
        public boolean isChecked() {
            return false;
        }

        @Override
        public void toggle() {

        }
    }
}
