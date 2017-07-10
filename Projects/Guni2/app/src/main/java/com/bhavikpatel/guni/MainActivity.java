package com.bhavikpatel.guni;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bhavikpatel.guni.balance.BalanceActivity;
import com.bhavikpatel.guni.balance.DataActivity;
import com.bhavikpatel.guni.balance.Invoice;
import com.bhavikpatel.guni.balance.WalletActivity;
import com.bhavikpatel.guni.study.StudyActivity;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEdit;

    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private boolean callPhoneEnabled = true;

    private static final String REFRESH_BALANCE = "refresh_balance";

    public static final String WALLET = "wallet";
    public static final String BALANCE = "balance";
    public static final String DATA = "data";

    private static int walletCurrentBalance;
    private static float balanceCurrentBalance;
    private static int dataCurrentBalance;
    private static TextView walletTV;
    private static TextView balanceTV;
    private static TextView dataTV;

    /*public static class DrawingView extends View {
        Bitmap bitmap;

        public DrawingView(Context context,Bitmap bitmap) {
            super(context);
            this.bitmap = bitmap;

        }

        @Override
        public void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            // paint.setColor(Color.CYAN);
            canvas.drawBitmap(getclip(), 30, 20, paint);
        }

        public Bitmap getclip() {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            // paint.setColor(color);
            canvas.drawCircle(bitmap.getWidth() / 2,
                    bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        }
    }*/
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getSharedPreferences("Guni",MODE_PRIVATE);
        mEdit = mPref.edit();
        if(!mPref.contains(WALLET)){
            Log.i("BHAVIK","mPref not contain walletBalance info");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Get referrence of buttons

        Button walletButton = (Button) findViewById(R.id.mainWalletButton);
        Button balanceButton = (Button) findViewById(R.id.mainBalanceButton);
        Button dataButton = (Button) findViewById(R.id.mainDataButton);
        Button studyButton = (Button) findViewById(R.id.mainStudyButton);
        Button androidButton = (Button) findViewById(R.id.mainAndroidButton);
        Button buyButton = (Button) findViewById(R.id.mainBuyButton);
        Button materialButton = (Button) findViewById(R.id.mainMaterialButton);
        Button groceryButton = (Button) findViewById(R.id.mainGroceriesButton);
        Button noteButton = (Button) findViewById(R.id.mainNoteButton);
        Button musicButton = (Button) findViewById(R.id.mainMusicButton);
        Button photosButton = (Button) findViewById(R.id.mainPhotosButton);
        Button lockedButton = (Button) findViewById(R.id.mainLockedButton);
        ImageView profileImage = (ImageView) findViewById(R.id.mainProfileImageView);
        Bitmap profileBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.mipmap.ic_launcher);
        profileImage.setImageBitmap(getCroppedBitmap(profileBitmap));


        //set longClick listeners of buttons
        balanceButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(callPhoneEnabled) {
                            // Here, thisActivity is the current activity
                            if (ContextCompat.checkSelfPermission(MainActivity.this,
                                    Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED) {

                                // Should we show an explanation?
                                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                        Manifest.permission.CALL_PHONE)) {

                                    // Show an expanation to the user *asynchronously* -- don't block
                                    // this thread waiting for the user's response! After the user
                                    // sees the explanation, try again to request the permission.

                                } else {

                                    // No explanation needed, we can request the permission.

                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);

                                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                                    // app-defined int constant. The callback method gets the
                                    // result of the request.
                                }
                            }
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "*125" + Uri.encode("#")));
                            startActivity(intent);
                        }
                        return true;
                    }
                }
        );

        //Set click listeners of buttons
        walletButton.setOnClickListener(this);
        balanceButton.setOnClickListener(this);
        dataButton.setOnClickListener(this);
        studyButton.setOnClickListener(this);
        androidButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
        materialButton.setOnClickListener(this);
        groceryButton.setOnClickListener(this);
        noteButton.setOnClickListener(this);
        musicButton.setOnClickListener(this);
        photosButton.setOnClickListener(this);
        lockedButton.setOnClickListener(this);


        //Get referrence of textView
        walletTV = (TextView) findViewById(R.id.mainWalletTV);
        balanceTV = (TextView) findViewById(R.id.mainBalanceTV);
        dataTV = (TextView) findViewById(R.id.mainDataTV);

    }


    @Override
    protected void onResume() {
        super.onResume();
        walletCurrentBalance = mPref.getInt(WALLET,0);
        balanceCurrentBalance = mPref.getInt(BALANCE,0);
        dataCurrentBalance = mPref.getInt(DATA,0);

        //set TextView
        walletTV.setText(Integer.toString(walletCurrentBalance) + "/-");
        balanceTV.setText(Float.toString(balanceCurrentBalance) + "/-");
        dataTV.setText(Integer.toString(dataCurrentBalance) + " MB");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.mainWalletButton:
                Intent walletIntent = new Intent(this,WalletActivity.class);
                walletIntent.putExtra(WalletActivity.CURRENT_BALANCE,walletCurrentBalance);
                startActivity(walletIntent);
                break;
            case R.id.mainBalanceButton:
                Intent balanceIntent = new Intent(this,BalanceActivity.class);
                balanceIntent.putExtra(BalanceActivity.CURRENT_BALANCE,balanceCurrentBalance);
                startActivity(balanceIntent);
                break;
            case R.id.mainDataButton:
                /*Intent dataIntent = new Intent(this,DataActivity.class);
                dataIntent.putExtra(DataActivity.CURRENT_BALANCE,dataCurrentBalance);
                startActivity(dataIntent);*/
                sendBroadcast(new Intent("com.bhavikpatel.guni.balance.UPDATE"));
                break;
            case R.id.mainStudyButton:
                Intent studyIntent = new Intent(this,StudyActivity.class);
                startActivity(studyIntent);
                break;
            case R.id.mainGroceriesButton:
                Intent sIntent = new Intent(REFRESH_BALANCE);
                sIntent.putExtra("key","PASSED VALUE");
                sendBroadcast(sIntent);
                break;
            default:break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "*125#"));
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                        startActivity(intent);

                } else {
                    callPhoneEnabled = false;

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            default:super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public static class MainService  extends AccessibilityService{
        public  String TAG = "XXXX";

        @Override
        public void onAccessibilityEvent(AccessibilityEvent event) {
            Log.i(TAG,"ser");
            if(!event.getClassName().toString().toLowerCase().contains("alert")){
                return;
            }
            AccessibilityNodeInfo source = event.getSource();
            String responseRaw = getResponseText(source).toLowerCase();
            Log.i(TAG,"responseRaw = " + responseRaw);
            String response = filter(responseRaw);
            Log.i(TAG,"response = " + response);
            performGlobalAction(GLOBAL_ACTION_BACK);
            try {
                balanceCurrentBalance = Float.parseFloat(response);
                balanceTV.setText(response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        private String filter(String raw){
            Pattern p = Pattern.compile("\\d+\\.\\d+");
            Matcher m = p.matcher(raw);
            if(raw.contains("bal") && raw.contains("rs")){
                while (m.find()) {
                    return m.group();
                }
            }
            return "";
        }

        private String getResponseText ( AccessibilityNodeInfo source){
            StringBuilder response = new StringBuilder();
            if(source != null){
                if(source.getClassName().toString().equals(TextView.class.getName())){
                    String message = source.getText() == null ? "" : source.getText().toString();
                    if(message.length() > 10)
                        response.append(message);
                }
                else {
                    if(source.getChildCount() > 0){
                        for(int i = 0 ; i < source.getChildCount() ; i++){
                            String message = getResponseText(source.getChild(i));
                            if(message.length() > 10){
                                response.append(message);
                            }
                        }
                    }
                }
            }
            return response.toString();
        }


        @Override
        public void onInterrupt() {

        }

        @Override
        protected void onServiceConnected() {
            super.onServiceConnected();
            Log.d(TAG, "  ser onServiceConnected");
            AccessibilityServiceInfo info = new AccessibilityServiceInfo();
            info.flags = AccessibilityServiceInfo.DEFAULT;
            info.packageNames = new String[]{"com.android.phone"};
            info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
            info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
            setServiceInfo(info);
        }

    }
}
