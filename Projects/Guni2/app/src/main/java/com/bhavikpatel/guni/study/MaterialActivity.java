package com.bhavikpatel.guni.study;


import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bhavikpatel.guni.Camera;
import com.bhavikpatel.guni.DBHandler;
import com.bhavikpatel.guni.GuniUtil;
import com.bhavikpatel.guni.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BHAVIK PATEL on 14-Jun-17.
 */

public class MaterialActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_main);

        // Set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.materialMainToolbar);
        toolbar.setTitle("Material");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set ViewPager
        viewPager = (ViewPager) findViewById(R.id.materialContentViewPager);
        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFrag(new PDFFrag(),"PDF");
        pagerAdapter.addFrag(new VideoFrag(),"Video");
        pagerAdapter.addFrag(new PhotoFrag(),"Photo");
        viewPager.setAdapter(pagerAdapter);

        //set TabLayout
        tabLayout = (TabLayout) findViewById(R.id.materialContentTabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }


    public  class FragPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> fragmentsTitle;

        public FragPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragmentsTitle = new ArrayList<>();
        }

        public void addFrag(Fragment frag , String title){
            fragments.add(frag);
            fragmentsTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitle.get(position);
        }
    }

    public static class PDFFrag extends Fragment{

        private PDFFragListAdapter listAdapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.material_frag_pdf,null);

            //Set References
            ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.materialFragPDFExpandableListView);
            EditText searchET = (EditText) view.findViewById(R.id.materialFragPDFSearchET);
            Button searchButton = (Button) view.findViewById(R.id.materialFragPDFSearchButton);

            //set Adapter
            listAdapter = new PDFFragListAdapter(getContext());
            listView.setAdapter(listAdapter);
            return view;
        }

        public static class PDFFragListAdapter extends BaseExpandableListAdapter {

            private Context context;
            private List<String> listGroup;
            private HashMap<String, List<Study.Material.PDF>> child;
            private DBHandler.DBStudy dbStudy;
            private DBHandler.DBStudy.TableMaterialPDF dbHandler;

            public PDFFragListAdapter(Context context) {
                dbStudy = new DBHandler.DBStudy(context,null,null,1);
                dbHandler = dbStudy.new TableMaterialPDF();
                this.context = context;
                this.listGroup = dbHandler.getListGroup();
                this.child = new HashMap<>();
                for(int i = 0 ; i < listGroup.size() ; i++){
                    this.child.put(this.listGroup.get(i),dbHandler.getListChild(this.listGroup.get(i)));
                }

                listGroup.add("Android");
                listGroup.add("Chemistry");
                Study.Material.PDF pdf1 = new Study.Material.PDF("Android 1","Android for Beginners" ,"Bhavik" ,null,null);
                Study.Material.PDF pdf2 = new Study.Material.PDF("Android 2","Android for Imtermediates" ,"Bhavik" ,null,null);
                Study.Material.PDF pdf3 = new Study.Material.PDF("Chemistry 1","Chemistry for Beginners" ,"Bhavik" ,null,null);
                Study.Material.PDF pdf4 = new Study.Material.PDF("Chemistry 2","Chemistry for Imtermediates" ,"Bhavik" ,null,null);
                List<Study.Material.PDF> child1 = new ArrayList<>();
                List<Study.Material.PDF> child2 = new ArrayList<>();
                child1.add(pdf1);
                child1.add(pdf2);
                child2.add(pdf3);
                child2.add(pdf4);
                child.put("Android",child1);
                child.put("Chemistry",child2);
            }

            //View Holder
            public class GroupViewHolder {
                TextView textView;
                ImageView indicator;

                public GroupViewHolder(View view) {
                    textView =(TextView) view.findViewById(R.id.materialFragPdfListGroupItemTV);
                    indicator = (ImageView) view.findViewById(R.id.materialFragPdfListGroupItemIndicatorImageView);
                }
            }
            public class ChildViewHolder {
                TextView textView;

                public ChildViewHolder(View view) {
                    textView =(TextView) view.findViewById(R.id.materialFragPdfListChildItemTV);
                }
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                View view;
                if(convertView == null){
                    view = LayoutInflater.from(context).inflate(R.layout.material_frag_pdf_list_group_item,null);
                    GroupViewHolder viewHolder = new GroupViewHolder(view);
                    view.setTag(viewHolder);
                }else {
                    view = convertView;
                }
                GroupViewHolder groupViewHolder = (GroupViewHolder) view.getTag();
                groupViewHolder.textView.setText(listGroup.get(groupPosition));
                if(isExpanded){
                    groupViewHolder.indicator.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);
                }else {
                    groupViewHolder.indicator.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
                }
                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view;
                if(convertView == null){
                    view = LayoutInflater.from(context).inflate(R.layout.material_frag_pdf_list_child_item,null);
                    ChildViewHolder viewHolder = new ChildViewHolder(view);
                    view.setTag(viewHolder);
                }else {
                    view = convertView;
                }
                ChildViewHolder childViewHolder = (ChildViewHolder) view.getTag();
                childViewHolder.textView.setText(child.get(listGroup.get(groupPosition)).get(childPosition).getSub());
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

    public static class VideoFrag extends Fragment{

        private VideoFragListAdapter listAdapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.material_frag_video,null);

            //Set References
            ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.materialFragVideoExpandableListView);
            EditText searchET = (EditText) view.findViewById(R.id.materialFragPDFSearchET);
            Button searchButton = (Button) view.findViewById(R.id.materialFragVideoSearchButton);

            //set Adapter
            listAdapter = new VideoFragListAdapter(getContext());
            listView.setAdapter(listAdapter);
            return view;
        }

        public static class VideoFragListAdapter extends BaseExpandableListAdapter{

            private Context context;
            private List<String> listGroup;
            private HashMap<String, List<Study.Material.Video>> child;
            private DBHandler.DBStudy dbStudy;
            private DBHandler.DBStudy.TableMaterialVideo dbHandler;

            public VideoFragListAdapter(Context context) {
                dbStudy = new DBHandler.DBStudy(context,null,null,1);
                dbHandler = dbStudy.new TableMaterialVideo();
                this.context = context;
                this.listGroup = dbHandler.getListGroup();
                this.child = new HashMap<>();
                for(int i = 0 ; i < listGroup.size() ; i++){
                    this.child.put(this.listGroup.get(i),dbHandler.getListChild(this.listGroup.get(i)));
                }

                listGroup.add("Web");
                listGroup.add("Art");
                Study.Material.Video video1 = new Study.Material.Video("Web 1","Web for Beginners" ,null,null);
                Study.Material.Video video2 = new Study.Material.Video("Web 2","Web for Imtermediates" ,null,null);
                Study.Material.Video video3 = new Study.Material.Video("Art 1","Art for Beginners" ,null,null);
                Study.Material.Video video4 = new Study.Material.Video("Art 2","Art for Imtermediates" ,null,null);
                List<Study.Material.Video> child1 = new ArrayList<>();
                List<Study.Material.Video> child2 = new ArrayList<>();
                child1.add(video1);
                child1.add(video2);
                child2.add(video3);
                child2.add(video4);
                child.put("Web",child1);
                child.put("Art",child2);
            }

            //View Holder
            public class GroupViewHolder {
                TextView textView;
                ImageView indicator;

                public GroupViewHolder(View view) {
                    textView =(TextView) view.findViewById(R.id.materialFragVideoListGroupItemTV);
                    indicator = (ImageView) view.findViewById(R.id.materialFragVideoListGroupItemIndicatorImageView);
                }
            }
            public class ChildViewHolder {
                TextView textView;

                public ChildViewHolder(View view) {
                    textView =(TextView) view.findViewById(R.id.materialFragVideoListChildItemTV);
                }
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                View view;
                if(convertView == null){
                    view = LayoutInflater.from(context).inflate(R.layout.material_frag_video_list_group_item,null);
                    GroupViewHolder viewHolder = new GroupViewHolder(view);
                    view.setTag(viewHolder);
                }else {
                    view = convertView;
                }
                GroupViewHolder groupViewHolder = (GroupViewHolder) view.getTag();
                groupViewHolder.textView.setText(listGroup.get(groupPosition));
                if(isExpanded){
                    groupViewHolder.indicator.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);
                }else {
                    groupViewHolder.indicator.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
                }
                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view;
                if(convertView == null){
                    view = LayoutInflater.from(context).inflate(R.layout.material_frag_video_list_child_item,null);
                    ChildViewHolder viewHolder = new ChildViewHolder(view);
                    view.setTag(viewHolder);
                }else {
                    view = convertView;
                }
                ChildViewHolder childViewHolder = (ChildViewHolder) view.getTag();
                childViewHolder.textView.setText(child.get(listGroup.get(groupPosition)).get(childPosition).getSub());
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

    public static class PhotoFrag extends Fragment{

        private ViewPager viewPager;
        private PhotoFragPagerAdapter pagerAdapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.material_frag_photo,null);
            viewPager = (ViewPager) view.findViewById(R.id.materialFragPhotoViewPager);
            pagerAdapter = new PhotoFragPagerAdapter(getChildFragmentManager(),getContext());
            viewPager.setAdapter(pagerAdapter);
            viewPager.addOnPageChangeListener(
                    new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            if(position == 1){
                                ((PhotoFragDetailFrag)pagerAdapter.photoFrags.get(1)).cancleSelection();
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    }
            );
            return view;
        }


        public static class PhotoFragPagerAdapter extends FragmentPagerAdapter {

            private ArrayList<Fragment> photoFrags;
            private FragmentManager mFM;
            private Context context;

            public PhotoFragPagerAdapter(FragmentManager fm,Context context) {
                super(fm);
                mFM = fm;
                this.context = context;
                photoFrags = new ArrayList<>();
                if(mFM.getFragments() == null){
                    photoFrags.add(new PhotoFragListFrag());
                    photoFrags.add(new PhotoFragDetailFrag());
                }else {
                    photoFrags = (ArrayList<Fragment>) mFM.getFragments();
                }
            }

            @Override
            public Fragment getItem(int position) {
                return photoFrags.get(position);
            }

            @Override
            public int getCount() {
                return photoFrags.size();
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        }

        public static class PhotoFragListFrag extends Fragment implements View.OnClickListener, Animation.AnimationListener{

            private ListView listView;
            private PhotoFragListAdapter listAdapter;
            private Animation animMenuRightLeftIn,animMenuRightLeftOut;
            private RelativeLayout menuRL;
            private EditText newSubjectET;
            private ImageButton deleteButton,cancleButton,checkButton,unCheckButton,editButton;
            GuniUtil.GuniSelectEditMultiple feature;


            @Nullable
            @Override
            public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.material_frag_photo_frag_list,container,false);

                deleteButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragMenuDeleteButton);
                cancleButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragMenuCancleButton);
                checkButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragMenuCheckButton);
                unCheckButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragMenuUnCheckButton);
                editButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragMenuEditButton);

                deleteButton.setOnClickListener(this);
                cancleButton.setOnClickListener(this);
                checkButton.setOnClickListener(this);
                unCheckButton.setOnClickListener(this);
                editButton.setOnClickListener(this);

                animMenuRightLeftIn = AnimationUtils.loadAnimation(getContext(),R.anim.right_left_in);
                animMenuRightLeftOut = AnimationUtils.loadAnimation(getContext(),R.anim.right_left_out);

                animMenuRightLeftIn.setAnimationListener(this);
                animMenuRightLeftOut.setAnimationListener(this);

                ImageButton menuButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragMenuButton);
                Button addButton = (Button) view.findViewById(R.id.materialFragPhotoFragAddButton);
                menuRL = (RelativeLayout) view.findViewById(R.id.materialFragPhotoFragMenuRL);
                newSubjectET = (EditText) view.findViewById(R.id.materialFragPhotoFragNewSubET);

                //set Buttons
                menuButton.setOnClickListener(this);
                addButton.setOnClickListener(this);

                listView = (ListView) view.findViewById(R.id.materialFragPhotoFragListView);
                listAdapter = new PhotoFragListAdapter(getContext(),(PhotoFrag)getParentFragment());
                listView.setAdapter(listAdapter);
                feature = new GuniUtil.GuniSelectEditMultiple<Study.Subject>(listAdapter.listPhoto){

                    @Override
                    public void selectionModeFired() {
                        super.selectionModeFired();
                        menuRL.startAnimation(animMenuRightLeftIn);
                    }

                    @Override
                    public void unSelectionModeFired() {
                        super.unSelectionModeFired();
                        menuRL.startAnimation(animMenuRightLeftOut);
                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectFired(final View view) {
                        Log.i("BHAVIK","Select Fired");
                        super.selectFired(view);
                        if(view != null){
                            Animation animItemLeftRight = new Animation() {
                                @Override
                                protected void applyTransformation(float interpolatedTime, Transformation t) {
                                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.getLayoutParams();
                                    lp.leftMargin = (int) (dpToPx(10) * interpolatedTime);
                                    ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.setLayoutParams(lp);

                                }
                            };
                            animItemLeftRight.setDuration(200);
                            ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.startAnimation(animItemLeftRight);
                        }
                    }

                    @Override
                    public void unSelectFired(final View view) {
                        Log.i("BHAVIK","UnSelect Fired");
                        super.unSelectFired(view);
                        if(view != null){
                            Animation animItemRightLeft = new Animation() {
                                @Override
                                protected void applyTransformation(float interpolatedTime, Transformation t) {
                                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.getLayoutParams();
                                    lp.leftMargin = (int) (dpToPx(2) * interpolatedTime);
                                    ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.setLayoutParams(lp);

                                }
                            };
                            animItemRightLeft.setDuration(200);
                            ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.startAnimation(animItemRightLeft);
                        }
                    }

                    @Override
                    public void selectLastFired() {
                        super.selectLastFired();
                        checkButton.setVisibility(View.GONE);
                    }

                    @Override
                    public void unSelectLastFired() {
                        super.unSelectLastFired();
                        checkButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void unSelectFirstFired() {
                        super.unSelectFirstFired();
                        editButton.setVisibility(View.GONE);
                    }

                    @Override
                    public void selectFirstFired() {
                        super.selectFirstFired();
                        editButton.setVisibility(View.VISIBLE);
                    }
                };

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                if(feature.inSelectionMode){
                                    feature.onClick(position,view);
                                }
                                else {
                                    PhotoFrag parentFrag = ((PhotoFrag)getParentFragment());
                                    PhotoFragDetailFrag detailFrag = (PhotoFragDetailFrag) parentFrag.pagerAdapter.getItem(1);
                                    String sub = ((PhotoFragListAdapter.ViewHolder)view.getTag()).textView.getText().toString();
                                    if(detailFrag.defaultTitle == null){
                                        return;
                                    }
                                    detailFrag.defaultTitle.setText("");
                                    detailFrag.title.setText(sub);
                                    detailFrag.subject = sub;
                                    detailFrag.listDetailAdapter.setPhotosOfSub(sub);
                                    detailFrag.camera.setVisibility(View.VISIBLE);
                                    detailFrag.cancleSelection();
                                    parentFrag.viewPager.setCurrentItem(1);
                                }
                            }
                        }
                );
                listView.setOnItemLongClickListener(
                        new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                feature.onClick(position,view);
                                return true;
                            }
                        }
                );
                return view;
            }

            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id){
                    case R.id.materialFragPhotoFragMenuButton:
                        if(menuRL.getVisibility() == View.GONE ){
                            //menuRL.startAnimation(animMenubottomUp);
                            menuRL.setVisibility(View.VISIBLE);
                            menuRL.startAnimation(animMenuRightLeftIn);
                            //menuRL.setVisibility(View.GONE);
                        }
                        else {
                            //menuRL.setVisibility(View.VISIBLE);
                            //menuRL.startAnimation(animMenuTopDown);
                            menuRL.startAnimation(animMenuRightLeftOut);
                        }
                        break;
                    case R.id.materialFragPhotoFragAddButton:
                        String newSub = newSubjectET.getText().toString().trim();
                        newSubjectET.setText("");
                        if(newSub.matches("")){
                            Toast.makeText(getContext(), "Not Valid Subject Name!", Toast.LENGTH_SHORT).show();
                        }else {
                            boolean alreadyContains = false;
                            for(String s : listAdapter.getSubjectsTitle()){
                                if(s.equals(newSub)){
                                    Toast.makeText(getContext(), "Subject Name Already Exists!", Toast.LENGTH_SHORT).show();
                                    alreadyContains = true;
                                    break;
                                }
                            }
                            if(!alreadyContains){
                                listAdapter.addSubject(newSub);
                            }
                        }
                        break;
                    case R.id.materialFragPhotoFragMenuDeleteButton:
                        // when wanted to delete selected list items a dialog popups with three buttons
                        DeleteFrag deleteFrag = (DeleteFrag) getChildFragmentManager().findFragmentByTag("list_frag_delete_dia");
                        if(deleteFrag == null) deleteFrag = new DeleteFrag();
                        deleteFrag.show(getChildFragmentManager(),"list_frag_delete_dia");
                        break;
                    case R.id.materialFragPhotoFragMenuCancleButton:
                        feature.fireEvent(GuniUtil.GuniSelectEditMultiple.UNSELECTION_MODE);
                        break;
                    case R.id.materialFragPhotoFragMenuCheckButton:
                        checkButton.setVisibility(View.GONE);
                        feature.fireEvent(GuniUtil.GuniSelectEditMultiple.SELECT_LAST);
                        listAdapter.notifyDataSetChanged();
                        break;
                    case R.id.materialFragPhotoFragMenuUnCheckButton:
                        checkButton.setVisibility(View.VISIBLE);
                        feature.fireEvent(GuniUtil.GuniSelectEditMultiple.UNSELECTION_MODE);
                        listAdapter.notifyDataSetChanged();
                        break;
                    case R.id.materialFragPhotoFragMenuEditButton:
                        EditFrag editFrag = (EditFrag) getChildFragmentManager().findFragmentByTag("list_frag_edit_dia");
                        if(editFrag == null) editFrag = new EditFrag();
                        Bundle bundle = new Bundle();
                        bundle.putString("edit",listAdapter.listPhoto.get((Integer)feature.itemsSelected.get(0)).getTitle());
                        editFrag.setArguments(bundle);
                        editFrag.show(getChildFragmentManager(),"list_frag_edit_dia");
                        break;
                    default:break;
                }
            }


            private int dpToPx (int dp){
                DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
                return Math.round(dp * (displayMetrics.xdpi/displayMetrics.DENSITY_DEFAULT));
            }


            public class PhotoFragListAdapter extends BaseAdapter {

                public static final int as = 5;
                private PhotoFrag parentFrag;
                private Context context;
                private ArrayList<Study.Subject> listPhoto;
                private DBHandler.DBStudy dbStudy;
                private DBHandler.DBStudy.TableSubject dbHandler;

                public PhotoFragListAdapter(Context context,PhotoFrag parentFrag) {
                    this.context = context;
                    this.parentFrag = parentFrag;
                    dbStudy = new DBHandler.DBStudy(context,null,null,1);
                    dbHandler = dbStudy.new TableSubject();
                    listPhoto = dbHandler.getSubject(DBHandler.DBStudy.TableSubject.TYPE_MATERIAL_PHOTO_LIST);
                    /*listPhoto.add("BhavikPatel");
                    listPhoto.add("Mom");
                    listPhoto.add("Papa");*/
                }

                public class ViewHolder{
                    TextView textView;

                    public ViewHolder(View view) {
                        textView = (TextView) view.findViewById(R.id.materialFragPhotoFragListItemTV);
                    }
                }

                @NonNull
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view;
                    if(convertView == null){
                        view = LayoutInflater.from(context).inflate(R.layout.material_frag_photo_frag_list_item,null);
                        ViewHolder holder = new ViewHolder(view);
                        view.setTag(holder);
                    }
                    else {
                        view = convertView;
                    }
                    ViewHolder holder = (ViewHolder) view.getTag();
                    holder.textView.setText(listPhoto.get(position).getTitle());

                    if(feature.itemsSelected.contains(position)){
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.textView.getLayoutParams();
                        lp.leftMargin = (int) dpToPx(10);
                        holder.textView.setLayoutParams(lp);
                    }else {
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.textView.getLayoutParams();
                        lp.leftMargin = (int) dpToPx(2);
                        holder.textView.setLayoutParams(lp);
                    }
                    return view;
                }


                public void addSubject(String newSub){
                    Study.Subject subject = new Study.Subject(newSub,dbHandler.TYPE_MATERIAL_PHOTO_LIST);
                    dbHandler.addSubject(subject);
                    listPhoto.add(subject);
                    notifyDataSetChanged();
                }
                public ArrayList<String> getSubjectsTitle(){
                    ArrayList<String> subTitles = new ArrayList<>();
                    for(Study.Subject s : listPhoto){
                        subTitles.add(s.getTitle());
                    }
                    return subTitles;
                }
                public void delete(boolean deletePhoto){
                    ArrayList<Integer> itemsSelected = feature.itemsSelected;
                    Collections.sort(itemsSelected);
                    Collections.reverse(itemsSelected);
                    dbHandler.delete(dbHandler.TYPE_MATERIAL_PHOTO_LIST,itemsSelected,true);

                    PhotoFragDetailFrag detailFrag = (PhotoFragDetailFrag) parentFrag.pagerAdapter.photoFrags.get(1);
                    DBHandler.DBStudy.TableMaterialPhoto detailDbHandler = detailFrag.listDetailAdapter.dbHandler;
                    ArrayList<String> subs = new ArrayList<>();
                    ArrayList<Study.Material.Photo> photos;

                    for(int i : itemsSelected){
                        subs.add(listPhoto.get(i).getTitle());
                        listPhoto.remove(i);
                    }
                    notifyDataSetChanged();

                    for(String s : subs){
                        photos = detailDbHandler.getList(s);
                        for(Study.Material.Photo photo : photos){
                            File deleteFile = new File(photo.getAddress());
                            if(deleteFile.exists())deleteFile.delete();
                            detailDbHandler.delete(photo);
                        }
                    }
                    detailFrag.selectAll.performClick();
                }
                public void editTitle(String newTitle){
                    Study.Subject editItem = listPhoto.get((Integer) feature.itemsSelected.get(0));
                    dbHandler.setSubTitle(dbHandler.TYPE_MATERIAL_PHOTO_LIST,newTitle,editItem.getTitle());
                    listPhoto.get((Integer) feature.itemsSelected.get(0)).setTitle(newTitle);
                    notifyDataSetChanged();
                }


                @Override
                public int getCount() {
                    return listPhoto.size();
                }

                @Override
                public Object getItem(int position) {
                    return listPhoto.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }


            }

            @Override
            public void onAnimationStart(Animation animation) {
                if(animation.equals(animMenuRightLeftIn)){
                    menuRL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation.equals(animMenuRightLeftOut)){
                    menuRL.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            public static class DeleteFrag extends DialogFragment {

                /*private PhotoFrag photoFrag;
                private PhotoFragListFrag listFrag;*/

                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    /*photoFrag = (PhotoFrag) ((MaterialActivity)getActivity()).pagerAdapter.fragments.get(2);
                    listFrag = (PhotoFragListFrag) photoFrag.pagerAdapter.photoFrags.get(0);*/
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    View view = inflater.inflate(R.layout.material_frag_photo_frag_list_delete_dia,null);
                    Button cancle = (Button) view.findViewById(R.id.materialFragPhotoFragListDeleteDiaCancleButton);
                    Button delete = (Button) view.findViewById(R.id.materialFragPhotoFragListDeleteDiaDeleteButton);
                    cancle.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dismiss();
                                }
                            }
                    );
                    delete.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((PhotoFragListFrag)getParentFragment()).listAdapter.delete(true);
                                    dismiss();
                                }
                            }
                    );
                    builder.setView(view).setMessage("Delete Subjects");
                    return builder.create();
                }
            }

            public static class EditFrag extends DialogFragment {

                PhotoFragListFrag parent;

                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {

                    parent = (PhotoFragListFrag) getParentFragment();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    View view = inflater.inflate(R.layout.material_frag_photo_frag_list_edit_dia,null);
                    final EditText editText = (EditText) view.findViewById(R.id.materialFragPhotoFragListEditDiaET);
                    editText.setText(getArguments().getString("edit"));
                    Button cancle = (Button) view.findViewById(R.id.materialFragPhotoFragListEditDiaCancleButton);
                    Button done = (Button) view.findViewById(R.id.materialFragPhotoFragListEditDiaDoneButton);
                    cancle.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    parent.feature.fireEvent(GuniUtil.GuniSelectEditMultiple.UNSELECTION_MODE);
                                    dismiss();
                                }
                            }
                    );
                    done.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String editedSub = editText.getText().toString();
                                    boolean alreadyPresent = false;
                                    for(String s : parent.listAdapter.getSubjectsTitle()){
                                        if(s.equals(editedSub)){
                                            alreadyPresent = true;
                                            break;
                                        }
                                    }
                                    if(alreadyPresent){
                                        Toast.makeText(getContext(), "Edited Subject Already Present!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        parent.listAdapter.editTitle(editedSub);
                                        parent.feature.fireEvent(GuniUtil.GuniSelectEditMultiple.UNSELECTION_MODE);
                                        dismiss();
                                    }
                                }
                            }
                    );
                    builder.setView(view).setMessage("Edit Subject Title");
                    return builder.create();
                }
            }
        }

        public static class PhotoFragDetailFrag extends Fragment implements View.OnClickListener{

            public String subject = "";
            public TextView title,defaultTitle;
            private ImageButton back,camera,selectAll,importButton,menuDeleteButton,menuCancleButton,menuCheckButton,menuUnCkeckButton;
            static final int REQUEST_CAMERA = 1,SELECT_PICTURES = 2;
            private GridView gridView;
            private PhotoFragListDetailAdapter listDetailAdapter;
            private static boolean multipleSelectionMode = false;
            private static ArrayList<Integer> selectedItemsPositions = new ArrayList<>();

            @Override
            public void onDestroyView() {
                super.onDestroyView();
            }

            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.material_frag_photo_frag_detail,container,false);
                gridView = (GridView) view.findViewById(R.id.materialFragPhotoFragDetailGridView);
                title = (TextView) view.findViewById(R.id.materialFragPhotoFragDetailTitleTV);
                defaultTitle = (TextView) view.findViewById(R.id.materialFragPhotoFragDetailDefaultTitleTV);
                title.setText("");
                back = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailBackButton);
                menuDeleteButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailDeleteButton);
                menuCancleButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailCancleButton);
                menuUnCkeckButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailUnCheckButton);
                menuCheckButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailCheckButton);
                importButton = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailImportButton);
                selectAll = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailSelectAllButton);
                camera = (ImageButton) view.findViewById(R.id.materialFragPhotoFragDetailCameraButton);
                listDetailAdapter = new PhotoFragListDetailAdapter(getContext());
                gridView.setAdapter(listDetailAdapter);
                gridView.setOnItemLongClickListener(
                        new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                selectionClicks(position,view);
                                return true;
                            }
                        }
                );
                gridView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if(multipleSelectionMode){
                                    selectionClicks(position,view);
                                }else{
                                    ItemView itemView = (ItemView) getChildFragmentManager().findFragmentByTag("item_view_dia");
                                    if(itemView == null) itemView = new ItemView();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("address",listDetailAdapter.listPhotoDetail.get(position).getAddress());
                                    itemView.setArguments(bundle);
                                    itemView.show(getChildFragmentManager(),"item_view_dia");
                                }

                            }
                        }
                );
                back.setOnClickListener(this);
                camera.setOnClickListener(this);
                selectAll.setOnClickListener(this);
                importButton.setOnClickListener(this);
                menuDeleteButton.setOnClickListener(this);
                menuCancleButton.setOnClickListener(this);
                menuCheckButton.setOnClickListener(this);
                menuUnCkeckButton.setOnClickListener(this);
                return view;
            }

            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id){
                    case R.id.materialFragPhotoFragDetailBackButton:
                        ((PhotoFrag)getParentFragment()).viewPager.setCurrentItem(0);
                        break;
                    case R.id.materialFragPhotoFragDetailCameraButton:
                        if(subject.matches("")){
                            Toast.makeText(getContext(),"Must have a subject",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        File photoFile = Camera.createImageFile(Camera.STUDY);
                        if (photoFile != null) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            startActivityForResult(Intent.createChooser(cameraIntent,"Take Picture"), REQUEST_CAMERA);
                        }
                        break;
                    case R.id.materialFragPhotoFragDetailSelectAllButton:
                        listDetailAdapter.setPhotos();
                        subject = "";
                        title.setText(subject);
                        defaultTitle.setText("All Photos");
                        camera.setVisibility(View.GONE);
                        break;
                    case R.id.materialFragPhotoFragDetailImportButton:
                        Intent importIntent = new Intent();
                        importIntent.setType("image/*");
                        importIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                        importIntent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(importIntent,"Select Picture"),SELECT_PICTURES);
                        break;
                    case R.id.materialFragPhotoFragDetailDeleteButton:
                        ArrayList<Study.Material.Photo> photos = new ArrayList<>();
                        for(int i : selectedItemsPositions){
                            photos.add(listDetailAdapter.listPhotoDetail.get(i));
                        }
                        selectedItemsPositions.clear();
                        cancleSelection();
                        listDetailAdapter.dbHandler.delete(photos);
                        listDetailAdapter.listPhotoDetail.removeAll(photos);
                        listDetailAdapter.notifyDataSetChanged();
                        for(Study.Material.Photo photo : photos){
                            File deletePhoto = new File(photo.getAddress());
                            if(deletePhoto.exists()) deletePhoto.delete();
                        }
                        break;
                    case R.id.materialFragPhotoFragDetailCheckButton:
                        multipleSelectionMode = true;
                        selectedItemsPositions.clear();
                        for(int i = 0 ; i < listDetailAdapter.listPhotoDetail.size() ; i++){
                            selectedItemsPositions.add(i);
                            gridView.getChildAt(i).findViewById(R.id.materialFragPhotoFragDetailItemCheckImageView).setVisibility(View.VISIBLE);
                        }

                        menuCheckButton.setVisibility(View.GONE);
                        menuUnCkeckButton.setVisibility(View.VISIBLE);
                        break;
                    case R.id.materialFragPhotoFragDetailCancleButton:
                    case R.id.materialFragPhotoFragDetailUnCheckButton:
                        cancleSelection();
                        break;
                }
            }

            private void selectionClicks(int position,View view){
                startSelection();
                if(selectedItemsPositions.contains(position)){
                    view.findViewById(R.id.materialFragPhotoFragDetailItemCheckImageView).setVisibility(View.GONE);
                    if(selectedItemsPositions.size() == listDetailAdapter.getCount()){
                        menuCheckButton.setVisibility(View.VISIBLE);
                        menuUnCkeckButton.setVisibility(View.GONE);
                    }
                    selectedItemsPositions.remove(new Integer(position));
                    if(selectedItemsPositions.size() == 0){
                        cancleSelection();
                    }
                } else {
                    view.findViewById(R.id.materialFragPhotoFragDetailItemCheckImageView).setVisibility(View.VISIBLE);
                    selectedItemsPositions.add(position);
                    if(selectedItemsPositions.size() == listDetailAdapter.getCount()){
                        menuCheckButton.setVisibility(View.GONE);
                        menuUnCkeckButton.setVisibility(View.VISIBLE);
                    }
                }
            }

            private void cancleSelection(){
                multipleSelectionMode = false;
                for(int i : selectedItemsPositions){
                    gridView.getChildAt(i).findViewById(R.id.materialFragPhotoFragDetailItemCheckImageView).setVisibility(View.GONE);
                }
                selectedItemsPositions.clear();
                menuDeleteButton.setVisibility(View.GONE);
                menuCheckButton.setVisibility(View.GONE);
                menuUnCkeckButton.setVisibility(View.GONE);
                menuCancleButton.setVisibility(View.GONE);

                back.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                defaultTitle.setVisibility(View.VISIBLE);
                selectAll.setVisibility(View.VISIBLE);
            }
            private void startSelection(){
                if(!multipleSelectionMode){
                    multipleSelectionMode = true;
                    selectedItemsPositions.clear();
                    menuDeleteButton.setVisibility(View.VISIBLE);
                    menuCheckButton.setVisibility(View.VISIBLE);
                    menuUnCkeckButton.setVisibility(View.GONE);
                    menuCancleButton.setVisibility(View.VISIBLE);

                    back.setVisibility(View.GONE);
                    title.setVisibility(View.GONE);
                    defaultTitle.setVisibility(View.GONE);
                    selectAll.setVisibility(View.GONE);
                }
            }



            public static class PhotoFragListDetailAdapter extends BaseAdapter{

                private Context context;
                private ArrayList<Study.Material.Photo> listPhotoDetail ;
                private DBHandler.DBStudy dbStudy;
                private DBHandler.DBStudy.TableMaterialPhoto dbHandler;

                public PhotoFragListDetailAdapter(Context context) {
                    this.context = context;
                    dbStudy = new DBHandler.DBStudy(context,null,null,1);
                    dbHandler = dbStudy.new TableMaterialPhoto();
                    listPhotoDetail = dbHandler.getList();

                   /* BitmapDrawable bd1 = (BitmapDrawable) ContextCompat.getDrawable(context,R.drawable.bhavik);
                    BitmapDrawable bd2 = (BitmapDrawable) ContextCompat.getDrawable(context,R.drawable.mom);
                    BitmapDrawable bd3 = (BitmapDrawable) ContextCompat.getDrawable(context,R.drawable.papa);
                    Bitmap bitmap1 = bd1.getBitmap();
                    Bitmap bitmap2 = bd2.getBitmap();
                    Bitmap bitmap3 = bd3.getBitmap();
                    if(bitmap1 == null){
                        Log.i("BHAVIK"," bitmap1 == null ");
                    }
                    String addressBhavik = Camera.save(bitmap1,Camera.STUDY,"Bhavik");
                    String addressMom = Camera.save(bitmap2,Camera.STUDY,"Mom");
                    String addressPapa = Camera.save(bitmap3,Camera.STUDY,"Papa");
                    Study.Material.Photo photo1 = new Study.Material.Photo("Family","Bhavik.jpg",null,addressBhavik);
                    Study.Material.Photo photo2 = new Study.Material.Photo("Family","Mom.jpg",null,addressMom);
                    Study.Material.Photo photo3 = new Study.Material.Photo("Family","Papa.jpg",null,addressPapa);
                    listPhotoDetail.add(photo1);
                    listPhotoDetail.add(photo2);
                    listPhotoDetail.add(photo3);*/
                }

                /*public byte[] getBytes(Bitmap bitmap){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
                    return stream.toByteArray();
                }*/

                public void setPhotosOfSub(String sub){
                    listPhotoDetail.clear();
                    notifyDataSetChanged();
                    listPhotoDetail = dbHandler.getList(sub);
                    notifyDataSetChanged();
                }
                public void setPhotos(){
                    listPhotoDetail.clear();
                    notifyDataSetChanged();
                    listPhotoDetail = dbHandler.getList();
                    notifyDataSetChanged();
                }

                public void addPhoto(Study.Material.Photo photo){
                    dbHandler.addPhoto(photo);
                    listPhotoDetail.add(photo);
                    notifyDataSetChanged();
                }
                public void addPhoto(ArrayList<Study.Material.Photo> photos){
                    dbHandler.addPhoto(photos);
                    listPhotoDetail.addAll(photos);
                    notifyDataSetChanged();
                }

                private class ViewHolder{
                    ImageView imageView;
                    TextView textView;

                    public ViewHolder(View view) {
                        imageView = (ImageView) view.findViewById(R.id.materialFragPhotoFragDetailItemImageView);
                        textView = (TextView) view.findViewById(R.id.materialFragPhotoFragDetailItemTV);
                    }
                }

                @NonNull
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view;
                    if(convertView == null){
                        view = LayoutInflater.from(context).inflate(R.layout.material_frag_photo_frag_detail_item,null);
                        ViewHolder holder = new ViewHolder(view);
                        view.setTag(holder);
                    }
                    else {
                        view = convertView;
                    }
                    final ViewHolder holder = (ViewHolder) view.getTag();
                    holder.textView.setText(listPhotoDetail.get(position).getTitle());
                    final String address = listPhotoDetail.get(position).getAddress();
                    holder.imageView.setImageBitmap(null);
                    if(selectedItemsPositions.contains(new Integer(position))){
                        view.findViewById(R.id.materialFragPhotoFragDetailItemCheckImageView).setVisibility(View.VISIBLE);
                    }else view.findViewById(R.id.materialFragPhotoFragDetailItemCheckImageView).setVisibility(View.GONE);
                    AsyncTask<Void,Void,Bitmap> task = new AsyncTask<Void, Void, Bitmap>() {
                        @Override
                        protected Bitmap doInBackground(Void... params) {
                            return Camera.load(address,100,100);
                        }

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            super.onPostExecute(bitmap);
                            holder.imageView.setImageBitmap(bitmap);
                        }
                    };
                    task.execute();
                    return view;
                }
                @Override
                public int getCount() {
                    return listPhotoDetail.size();
                }

                @Override
                public Object getItem(int position) {
                    return listPhotoDetail.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }



            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                //Log.i("BHAVIK","onActivityResult");
                //if(resultCode == RESULT_OK) Log.i("BHAVIK","resultCode == RESULT_OK is true");
                //if(data == null) Log.i("BHAVIK","data == null is true");
                //if(requestCode == SELECT_PICTURES) Log.i("BHAVIK","requestCode == SELECT_PICTURES is true");
                if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK ){
                   // Log.i("BHAVIK","in REQUEST_CAMERA");

                    String add = Camera.mCurrentPhotoPath;
                    Study.Material.Photo photo = new Study.Material.Photo(subject,null,null,add);
                    listDetailAdapter.addPhoto(photo);
                }else if(requestCode == SELECT_PICTURES && resultCode == RESULT_OK ){
                    //Log.i("BHAVIK","in SELECT_PICTURES");
                    try {
                        // When an Image is picked
                        if (requestCode == SELECT_PICTURES && resultCode == RESULT_OK && data != null) {

                            // Get the Image from data
                            String[] filePathColumn = { MediaStore.Images.Media.DATA };
                            if(data.getData()!=null){
                                Uri mImageUri=data.getData();

                                // Get the cursor
                                Cursor cursor = getContext().getContentResolver().query(mImageUri,
                                        filePathColumn, null, null, null);
                                // Move to first row
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String imageEncoded  = cursor.getString(columnIndex);
                                //Log.i("BHAVIK","imageEncoded in onActivityResult() = " + imageEncoded);
                                if(imageEncoded != null && imageEncoded.contains("/storage/sdcard0/")){
                                    Study.Material.Photo photo = new Study.Material.Photo(subject,null,null,imageEncoded);
                                    listDetailAdapter.addPhoto(photo);
                                }else {
                                    Toast.makeText(getContext(),"Files cannot be selected",Toast.LENGTH_SHORT).show();
                                }
                                cursor.close();

                            }else {
                                if (data.getClipData() != null) {
                                    ClipData mClipData = data.getClipData();
                                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                                    ArrayList<Study.Material.Photo> photos = new ArrayList<>();
                                    int notSelected = 0;
                                    for (int i = 0; i < mClipData.getItemCount(); i++) {

                                        ClipData.Item item = mClipData.getItemAt(i);
                                        Uri uri = item.getUri();
                                        mArrayUri.add(uri);
                                        // Get the cursor
                                        Cursor cursor = getContext().getContentResolver().query(uri, filePathColumn, null, null, null);
                                        // Move to first row
                                        cursor.moveToFirst();

                                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                        String imageEncoded  = cursor.getString(columnIndex);
                                        //Log.i("BHAVIK","imageEncoded[" + i + "] in onActivityResult() = " + imageEncoded);
                                        if(imageEncoded != null && imageEncoded.contains("/storage/sdcard0/")){
                                            Study.Material.Photo photo = new Study.Material.Photo(subject,null,null,imageEncoded);
                                            photos.add(photo);
                                        }else {
                                            notSelected++;
                                        }
                                        cursor.close();
                                    }
                                    Toast.makeText(getContext(),notSelected + " Files cannot be selected",Toast.LENGTH_SHORT).show();
                                    listDetailAdapter.addPhoto(photos);
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                    super.onActivityResult(requestCode, resultCode, data);

                }
            }

            public static class ItemView extends DialogFragment implements View.OnTouchListener{

                private static final String TAG = "Touch";
                @SuppressWarnings("unused")
                private static final float MIN_ZOOM = 1f, MAX_ZOOM = 1f;

                private Matrix matrix = new Matrix();
                private Matrix savedMatrix = new Matrix();

                // The 3 states (events) which the user is trying to perform
                private static final int NONE = 0;
                private static final int DRAG = 1;
                private static final int ZOOM = 2;
                private int mode = NONE;

                // these PointF objects are used to record the point(s) the user is touching
                private PointF start = new PointF();
                private PointF mid = new PointF();
                private float oldDist = 1f;


                @NonNull
                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    View view = inflater.inflate(R.layout.material_frag_photo_frag_deteil_item_view_dia,null);
                    final ImageView image = (ImageView) view.findViewById(R.id.materialFragPhotoFragDetailItemViewImageView);
                    image.setOnTouchListener(this);
                    matrix.setScale(2.383f,2.383f);
                    matrix.postTranslate(0f,94f);
                    RelativeLayout menuRL = (RelativeLayout) view.findViewById(R.id.materialFragPhotoFragDetailItemViewMenuRL);
                    AsyncTask<Integer,Void,Bitmap> task = new AsyncTask<Integer, Void, Bitmap>() {
                        @Override
                        protected Bitmap doInBackground(Integer... params) {
                            return Camera.load(getArguments().getString("address"),params[0],params[1]);
                        }

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            super.onPostExecute(bitmap);
                            image.setImageBitmap(bitmap);
                        }
                    };
                    task.execute(image.getWidth(),image.getHeight());
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(view);
                    return builder.create();
                }
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView view = (ImageView) v;
                    view.setScaleType(ImageView.ScaleType.MATRIX);
                    float scale;


                    dumpEvent(event);
                    // Handle touch events here...

                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN: // first finger down only
                            savedMatrix.set(matrix);
                            start.set(event.getX(), event.getY());
                            Log.d(TAG, "mode=DRAG"); // write to LogCat
                            mode = DRAG;
                            break;

                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_POINTER_UP:

                            mode = NONE;
                            Log.d(TAG, "mode=NONE");
                            break;

                        case MotionEvent.ACTION_POINTER_DOWN:
                            Log.d(TAG, "CASE : ACTION_POINTER_DOWN");
                            oldDist = spacing(event);
                            Log.d(TAG, "oldDist=" + oldDist);
                            if (oldDist > 5f) {
                                savedMatrix.set(matrix);
                                midPoint(mid, event);
                                mode = ZOOM;
                                Log.d(TAG, "mode=ZOOM");
                            }
                            break;

                        case MotionEvent.ACTION_MOVE:
                            Log.d(TAG, "CASE : ACTION_MOVE");

                            if (mode == DRAG) {
                                matrix.set(savedMatrix);
                                matrix.postTranslate(event.getX() - start.x, event.getY()
                                        - start.y); /*
                                     * create the transformation in the matrix
                                     * of points
                                     */
                            } else if (mode == ZOOM) {
                                // pinch zooming
                                float newDist = spacing(event);
                                Log.d(TAG, "newDist=" + newDist);
                                if (newDist > 5f) {
                                    matrix.set(savedMatrix);
                                    scale = newDist / oldDist;
                    /*
                     * setting the scaling of the matrix...if scale > 1 means
                     * zoom in...if scale < 1 means zoom out
                     */
                                    matrix.postScale(scale, scale, mid.x, mid.y);
                                }
                            }
                            break;
                    }

                    view.setImageMatrix(matrix); // display the transformation on screen

                    return true;
                }

                private float spacing(MotionEvent event) {
                    float x = event.getX(0) - event.getX(1);
                    float y = event.getY(0) - event.getY(1);
                    return (float) Math.sqrt(x * x + y * y);
                }
                private void midPoint(PointF point, MotionEvent event) {
                    float x = event.getX(0) + event.getX(1);
                    float y = event.getY(0) + event.getY(1);
                    point.set(x / 2, y / 2);
                }
                private void dumpEvent(MotionEvent event) {
                    String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                            "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
                    StringBuilder sb = new StringBuilder();
                    int action = event.getAction();
                    int actionCode = action & MotionEvent.ACTION_MASK;
                    sb.append("event ACTION_").append(names[actionCode]);

                    if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                            || actionCode == MotionEvent.ACTION_POINTER_UP) {
                        sb.append("(pid ").append(
                                action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
                        sb.append(")");
                    }

                    sb.append("[");
                    for (int i = 0; i < event.getPointerCount(); i++) {
                        sb.append("#").append(i);
                        sb.append("(pid ").append(event.getPointerId(i));
                        sb.append(")=").append((int) event.getX(i));
                        sb.append(",").append((int) event.getY(i));
                        if (i + 1 < event.getPointerCount())
                            sb.append(";");
                    }

                    sb.append("]");
                    Log.d("Touch Event", sb.toString());
                }
            }

        }
    }
}
