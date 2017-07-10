package com.bhavikpatel.guni;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 03-Jun-17.
 */

public class ResultActivity extends AppCompatActivity implements ResultDiaSub.DiaSubCompt,ResultDiaFab.DiaFabCompt{

    private ViewPager pager;
    private ResultPagerAdapter pagerAdapter;
    private boolean mHideDeleteMenuButton = true;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
        fab = (FloatingActionButton) findViewById(R.id.resultFab);
        pager = (ViewPager) findViewById(R.id.resultPager);
        pagerAdapter = new ResultPagerAdapter(getSupportFragmentManager(),getApplicationContext());
        if(pagerAdapter.frags.size() != 0 ){
            mHideDeleteMenuButton = false;
        }
        pager.setAdapter(pagerAdapter);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ResultDiaFab diaFab = new ResultDiaFab();
                        diaFab.show(getFragmentManager(),"");
                    }
                }
        );

    }

    @Override
    public void setSub(String sub) {
        ResultFrag frag = new ResultFrag();
        frag.setSubject(sub);
        pagerAdapter.add(frag);
    }

    @Override
    public void setMarks(ResultMarks marks) {
        ResultFrag frag = pagerAdapter.frags.get(pager.getCurrentItem());
        frag.add(marks);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pagerAdapter.save();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result_sub,menu);
        if(mHideDeleteMenuButton){
            MenuItem delete = menu.findItem(R.id.resultMenuDelSub);
            MenuItem deleteAll = menu.findItem(R.id.resultMenuDelAllSub);
            delete.setVisible(false);
            deleteAll.setVisible(false);
            fab.hide();
        }else fab.show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.resultMenuAddSub){
            ResultDiaSub diaSub = new ResultDiaSub();
            diaSub.show(getFragmentManager(),"");
            return true;
        }
        if(id == R.id.resultMenuDelSub){
            pagerAdapter.delete(pager.getCurrentItem());
            return true;
        }
        if(id == R.id.resultMenuDelAllSub){
            pagerAdapter.delete();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ResultPagerAdapter extends FragmentStatePagerAdapter{

        private ArrayList<ResultSub> subjects;
        private Context mContext;
        private ArrayList<ResultFrag> frags;
        private ResultPagerSerializer mSerializer;

        public ResultPagerAdapter(FragmentManager fm, Context c) {
            super(fm);
            this.mContext = c;
            frags = new ArrayList<ResultFrag>();
            mSerializer = new ResultPagerSerializer("GuniStudyResultSub.json",mContext);
            subjects = mSerializer.load();
            Log.i("BHAVIK","SUB LIST SIZE ON INITI = " + subjects.size());
            for(int i = 0 ; i < subjects.size() ; i++){
                ResultFrag f = new ResultFrag();
                f.setSubject(subjects.get(i).getSubjectName());
                frags.add(f);
                Log.i("BHAVIK","IN FOR LOOP OF PAGER_ADAPTER : FRAG = " + i + " SUB + " + subjects.get(i).getSubjectName());
            }
        }

        public void save(){
            ArrayList<ResultSub> subjects = new ArrayList<>();
            Log.i("BHAVIK","FRAG LIST SIZE ON SAVE OR PAUSE= " + frags.size());

            for(int i = 0 ; i < frags.size() ; i++ ){
                ResultSub s = new ResultSub();
                s.setSubjectName(frags.get(i).getSubject());
                subjects.add(s);
            }
            mSerializer.save(subjects);
        }

        public void delete(int position){
            ResultFrag f = frags.get(position);
            f.deleteFile();
            frags.remove(position);
            if(frags.size() == 0 ){
                mHideDeleteMenuButton = true;
                invalidateOptionsMenu();
            }
            Log.i("BHAVIK","frags length on del = " + frags.size());
            notifyDataSetChanged();
        }
        public void delete(){
            while(frags.size() != 0 ) delete(0);
        }

        public void add(ResultFrag frag){
            frags.add(frag);
            if(frags.size() != 0){
                mHideDeleteMenuButton = false;
                invalidateOptionsMenu();
            }
            Log.i("BHAVIK","frags length on add = " + frags.size());
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return frags.get(position);
        }

        @Override
        public int getCount() {
            return frags.size();
        }
    }
}
