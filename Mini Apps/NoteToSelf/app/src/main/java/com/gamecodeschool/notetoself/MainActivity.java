package com.gamecodeschool.notetoself;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private NoteAdapter mNoteAdapter;
    private boolean mSound;
    private int mAnimOption;
    private SharedPreferences mPrefs;

    Animation mAnimFlash;
    Animation mFadeIn;

    int mIdBeep = -1;
    SoundPool mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // Instantiate our sound pool
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).build();
        mSp = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(5).build();
        //}else{
        //  mSp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        //}
        try{
            // Create objects of the 2 required classes
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor ;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("beep.ogg");
            mIdBeep = mSp.load(descriptor,0);

        }catch (IOException e){
            Log.i("error" , "" + e);
        }


        mNoteAdapter = new NoteAdapter();
        ListView listNote = (ListView) findViewById(R.id.listView);
        listNote.setAdapter(mNoteAdapter);

        //so we can long click it
        listNote.setLongClickable(true);
        listNote.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int whichItem, long id) {
                        mNoteAdapter.deleteNote(whichItem);
                        return true;
                    }
                }
        );

        //Handel clicks on the ListView
        listNote.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int whichItem, long id) {
                        if(mSound){
                            mSp.play(mIdBeep,1,1,0,0,1);
                        }
                        /*create a temperary Note
                        * which is a reference to the Note that has just been clicked*/
                        Note tempNote = mNoteAdapter.getItem(whichItem);
                        // Create a new dialog window
                        DialogShowNote dialog = new DialogShowNote();
                        dialog.sendNoteSelected(tempNote);
                        // Show the dialog window with the note in it
                        dialog.show(getFragmentManager(),"");

                    }
                }
        );
    }

    @Override
    protected void onResume(){
        super.onResume();
        mPrefs = getSharedPreferences("Note to self",MODE_PRIVATE);
        mSound = mPrefs.getBoolean("sound",true);
        mAnimOption = mPrefs.getInt("anim option",SettingsActivity.FAST);
        mAnimFlash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.flash);
        mFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        //set the rate of flash based on setting
        if(mAnimOption == SettingsActivity.FAST){
            mAnimFlash.setDuration(100);
            Log.i("anim = ","" + mAnimOption);
        }else if(mAnimOption == SettingsActivity.SLOW){
            mAnimFlash.setDuration(1000);
            Log.i("anim  = ","" + mAnimOption);
        }
        mNoteAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add){
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getFragmentManager(),"");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void createNewNote(Note n){
        mNoteAdapter.addNote(n);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mNoteAdapter.saveNotes();
    }

    public class NoteAdapter extends BaseAdapter {
        private JSONSerializer mSerializer;
        List<Note> noteList = new ArrayList<Note>();
        public NoteAdapter(){
            mSerializer = new JSONSerializer("NoteToSelf.json",MainActivity.this.getApplicationContext());
            try{
                noteList = mSerializer.load();
            }catch (Exception e){
                //noteList = new ArrayList<Note>();
                Log.e("Error loading notes: ", ""+ e);
            }
        }
        public void saveNotes(){
            try{
                mSerializer.save(noteList);
            }catch (Exception e){
                Log.e("Error Saving Notes","" + e);
            }
        }
        @Override
        public int getCount (){
            return noteList.size();
        }

        @Override
        public Note getItem(int whichItem){
            return noteList.get(whichItem);
        }

        @Override
        public long getItemId(int whichItem){
            return whichItem;
        }

        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup){
            //Implement this method next
            //Has view been inflated already
            if (view == null){
                //if not , do so here
                //First create a LayoutInflater
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Now instantiate view using inflater.inflate
                //using the listitem layout
                view = inflater.inflate(R.layout.listitem,viewGroup,false);
                //the false parameter is neccessary because of the way that we want to use listitem

            }
            //grab a reference to all our TextView and ImageView widgets
            TextView txtTitle = (TextView) view.findViewById(R.id.textTitle);
            TextView txtDescription = (TextView) view.findViewById(R.id.textDescription);
            ImageView ivImportant = (ImageView) view.findViewById(R.id.imageViewImportant);
            ImageView ivToDo = (ImageView) view.findViewById(R.id.imageViewTodo);
            ImageView ivIdea = (ImageView) view.findViewById(R.id.imageViewIdea);

            //Hide any ImageView widgets that are not relevent
            Note tempNote = noteList.get(whichItem);
            // to animate or not to animate
            if(tempNote.isImportant() && mAnimOption != SettingsActivity.NONE){
                view.setAnimation(mAnimFlash);
            }else{
                view.setAnimation(mFadeIn);
            }
            if(!tempNote.isIdea()){
                ivIdea.setVisibility(View.GONE);
            }
            if (!tempNote.isToDo()){
                ivToDo.setVisibility(View.GONE);
            }
            if (!tempNote.isImportant()) {
                ivImportant.setVisibility(View.GONE);
            }
            /*if (!(tempNote.isIdea()||tempNote.isImportant()||tempNote.isToDo())){
                txtTitle.setPadding(0,0,0,0);
            }*/
            // Add the text to the heading and description
            txtDescription.setText(tempNote.getDescription());
            txtTitle.setText(tempNote.getTitle());

            return view;

        }

        public void addNote(Note n){
            noteList.add(n);
            notifyDataSetChanged();
        }
        public void deleteNote(int n){
            noteList.remove(n);
            notifyDataSetChanged();
        }
    }
}
