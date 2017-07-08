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

public class SylActivity extends AppCompatActivity implements SylDiaSub.SylDiaSubCompt , SylDiaTitle.SylDiaTitleCompt{
    private SylActivity.SylPagerAdap pagerAdap;
    private ViewPager pager;
    private boolean mHideDeleteMenuItem = true;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syl_main);
        fab = (FloatingActionButton) findViewById(R.id.sylFab);
        pager = (ViewPager) findViewById(R.id.sylPag);
        pagerAdap = new SylActivity.SylPagerAdap(getSupportFragmentManager(),getApplicationContext());
        if(pagerAdap.frag.size() != 0){
            mHideDeleteMenuItem = false;
            fab.show();
            invalidateOptionsMenu();
        }
        pager.setAdapter(pagerAdap);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SylDiaTitle diaTitle = new SylDiaTitle();
                        diaTitle.show(getFragmentManager(),"Title");
                    }
                }
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        pagerAdap.save();
        Log.i("BHAVIK","ON PAUSE IN ACTIVITY");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.syl_sub,menu);
        if(mHideDeleteMenuItem == true){
            MenuItem deleteSub = menu.findItem(R.id.sylSubDel);
            deleteSub.setVisible(false);
            fab.hide();
        }else fab.show();
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sylSubAdd){
            SylDiaSub diaSub = new SylDiaSub();
            diaSub.show(getFragmentManager(),"hi");
            return true;
        }
        if(id == R.id.sylSubDel){
            Log.i("BHAVIK","BEFORE SUB DELETE PAGER CURRENT POSITION IS = " + pager.getCurrentItem());
            pagerAdap.delete(pager.getCurrentItem());
            Log.i("BHAVIK","AFTER SUB DELETE PAGER CURRENT POSITION IS = " + pager.getCurrentItem());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void putSub(String sub) {
        SylFrag frag = new SylFrag();
        frag.setSubject(sub);
        pagerAdap.add(frag);
    }

    @Override
    public void putTitle(String title) {
        SylFrag frag =(SylFrag) pagerAdap.frag.get(pager.getCurrentItem());
        frag.putTitle(title);
    }

    private class SylPagerAdap extends FragmentStatePagerAdapter {

        //private ArrayList<SylSub> sylSubs;
        private ArrayList<Fragment> frag;
        private Context mContext;
        private SylPagerSerializer mSerializer;

        public SylPagerAdap(FragmentManager fm, Context c) {
            super(fm);
            mContext = c;
            mSerializer = new SylPagerSerializer(mContext,"GuniStudySylSub.json");
            ArrayList<SylSub> sylSubs = mSerializer.load();

            frag = new ArrayList<Fragment>();
            for(int i = 0 ; i < sylSubs.size() ; i++ ){
                SylFrag f = new SylFrag();
                f.setSubject(sylSubs.get(i).getSubject());
                frag.add(f);
            }
            Log.i("BHAVIK","sub list length = " + sylSubs.size() + " and fraglist length = " + frag.size());
        }

        public void save(){
            ArrayList<SylSub> sylSubs = new ArrayList<>();
            for(int i = 0 ; i < frag.size() ; i++){
                SylSub sub = new SylSub();
                sub.setSubject(((SylFrag)frag.get(i)).getSubject());
                sylSubs.add(sub);
            }
            mSerializer.save(sylSubs);
            Log.i("BHAVIK","SAVING SUB LIST OF LENGTH = " + sylSubs.size());
        }

        public void add(Fragment f){
            mHideDeleteMenuItem = false;
            invalidateOptionsMenu();
            fab.show();
            frag.add(f);
            notifyDataSetChanged();
        }
        public void delete(int position){
            try {
                SylFrag f = (SylFrag) frag.get(position);
                f.delete();
                frag.remove(position);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(frag.size() == 0 ){
                mHideDeleteMenuItem = true;
                invalidateOptionsMenu();
                fab.hide();
            }
            notifyDataSetChanged();
        }
        public Fragment get(int position){
            Fragment f = this.frag.get(position);
            return f;
        }

        @Override
        public Fragment getItem(int position) {
            return frag.get(position);
        }

        @Override
        public int getItemPosition(Object object) {

            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return frag.size();
        }
    }

}
