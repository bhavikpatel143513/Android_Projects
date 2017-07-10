package com.bhavikpatel.guni.study;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bhavikpatel.guni.DBHandler;
import com.bhavikpatel.guni.R;

import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 16-Jun-17.
 */

public class ResultActivity extends AppCompatActivity {

    private ListResultAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);

        //set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.resultMainToolbar);
        toolbar.setTitle("Result");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get referrences
        ListView listResult = (ListView) findViewById(R.id.resultContentListView);
        Button addButton = (Button) findViewById(R.id.resultContentAddButton);
        Button searchButton = (Button) findViewById(R.id.resultContentSerchButton);
        EditText searchET = (EditText) findViewById(R.id.resultContentSearchET);

        //set Adapter
        listAdapter = new ListResultAdapter(getApplicationContext());
        listResult.setAdapter(listAdapter);

    }

    public class ListResultAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<Study.Result> listResult;
        private DBHandler.DBStudy dbStudy;
        private DBHandler.DBStudy.TableResult dbHandler;

        public ListResultAdapter(Context context) {
            this.context = context;
            dbStudy = new DBHandler.DBStudy(context,null,null,1);
            dbHandler = dbStudy.new TableResult();
            listResult = dbHandler.getList();
        }

        public class ViewHolder{
            TextView title,examName,date,marks;

            public ViewHolder(View view) {
                title = (TextView) view.findViewById(R.id.resultContentListItemTitleTV);
                examName = (TextView) view.findViewById(R.id.resultContentListItemExamNameTV);
                date = (TextView) view.findViewById(R.id.resultContentListItemDateTV);
                marks = (TextView) view.findViewById(R.id.resultContentListItemMarksTV);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null){
                view = LayoutInflater.from(context).inflate(R.layout.result_content_list_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            else {
                view = convertView;
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.title.setText(listResult.get(position).getSub());
            holder.date.setText(listResult.get(position).getDate());
            holder.marks.setText(listResult.get(position).getObtainedMarks() + " / " + listResult.get(position).getTotalMarks());
            holder.examName.setText(listResult.get(position).getExamName());
            return view;
        }

        @Override
        public int getCount() {
            return listResult.size();
        }

        @Override
        public Object getItem(int position) {
            return listResult.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
