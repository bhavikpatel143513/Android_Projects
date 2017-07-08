package com.bhavikpatel.guni;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHAVIK PATEL on 02-Jun-17.
 */

public class CalanderActivity extends AppCompatActivity{

    private List<Fragment> calanderFragList;
    private SimpleFragmentPagerAdapter pagerAdapter;
    public CalanderEventsFrag calanderEventsFrag;
    private int pagerPosition = 0;
    private final int CAMERA_REQUEST = 123;
    public boolean hideMenu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander_main);
        calanderFragList = new ArrayList<Fragment>();
        calanderFragList.add(CalanderImgFragment.getInstance(R.drawable.calander));
        calanderEventsFrag = new CalanderEventsFrag();
        calanderFragList.add((Fragment)calanderEventsFrag);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),calanderFragList);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter((FragmentPagerAdapter) pagerAdapter);
        pager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        pagerPosition = position;
                        invalidateOptionsMenu();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calander_menu, menu);
        if(pagerPosition == 0 || hideMenu == true){
            MenuItem deleteAll = menu.findItem(R.id.calanderMenuDeleteAll);
            deleteAll.setVisible(false);
        }
        if(pagerPosition == 1){
            MenuItem captureCalander = menu.findItem(R.id.calanderMenuCaptureCalander);
            captureCalander.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.calanderMenuDeleteAll){
            getCalanderEventsFrag().calanderAdapter.deleteAllCalanderEvents();
            return true;
        }
        if(id == R.id.calanderMenuCaptureCalander){
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap calander = (Bitmap) data.getExtras().get("data");
            CalanderImgFragment imgFragment = (CalanderImgFragment) calanderFragList.get(0);
            imgFragment.addCalanderImage(calander);
        }
    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    public CalanderEventsFrag getCalanderEventsFrag(){
        return this.calanderEventsFrag;
    }

    public static class CalanderImgFragment extends Fragment{

        private ImageView calanderImage;
        //private static String imageRef;
        public CalanderImgFragment(){
        }

        public static CalanderImgFragment getInstance(int imageRef) {
            //CalanderActivity.CalanderImgFragment.imageRef = imageRef;
            CalanderImgFragment fragment = new CalanderImgFragment();
            Bundle bundle = new Bundle(1);
            bundle.putInt("imageRef",imageRef);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.guni_image_frag,container,false);
            calanderImage = (ImageView) view.findViewById(R.id.imageViewCalander);
            calanderImage.setImageResource(getArguments().getInt("imageRef"));
            return view;
        }
        public void addCalanderImage(Bitmap bitmap){
            calanderImage.setImageBitmap(bitmap);
        }
    }


}






























