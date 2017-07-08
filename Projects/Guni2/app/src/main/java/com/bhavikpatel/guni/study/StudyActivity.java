package com.bhavikpatel.guni.study;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bhavikpatel.guni.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BHAVIK PATEL on 14-Jun-17.
 */

public class StudyActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_main);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.studyMainToolbar);
        toolbar.setTitle("Study");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get referrence of buttons
        Button materialButton = (Button) findViewById(R.id.studyContentMaterialButton);
        Button attendenceButton = (Button) findViewById(R.id.studyContentAttendenceButton);
        Button resultButton = (Button) findViewById(R.id.studyContentResultButton);
        Button subjectButton = (Button) findViewById(R.id.studyContentSubjectButton);
        Button syllabusButton = (Button) findViewById(R.id.studyContentSyllabusButton);
        Button timeTableButton = (Button) findViewById(R.id.studyContentTimeTableButton);

        //set button's on click
        materialButton.setOnClickListener(this);
        attendenceButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        subjectButton.setOnClickListener(this);
        syllabusButton.setOnClickListener(this);
        timeTableButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        int id = v.getId();
        switch (id){
            case R.id.studyContentAttendenceButton:
                i = new Intent(this,AttendenceActivity.class);
                startActivity(i);
                break;
            case R.id.studyContentMaterialButton:
                i = new Intent(this,MaterialActivity.class);
                startActivity(i);
                break;
            case R.id.studyContentResultButton:
                i = new Intent(this,ResultActivity.class);
                startActivity(i);
                break;
            case R.id.studyContentSubjectButton:
                break;
            case R.id.studyContentSyllabusButton:
                i = new Intent(this,SyllabusActivity.class);
                startActivity(i);
                break;
            case R.id.studyContentTimeTableButton:
                break;
            default:break;
        }
    }
}
