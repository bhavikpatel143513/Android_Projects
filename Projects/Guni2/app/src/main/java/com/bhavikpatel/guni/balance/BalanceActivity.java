package com.bhavikpatel.guni.balance;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhavikpatel.guni.DBHandler;
import com.bhavikpatel.guni.GuniUtil;
import com.bhavikpatel.guni.MainActivity;
import com.bhavikpatel.guni.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by BHAVIK PATEL on 13-Jun-17.
 */

public class BalanceActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEdit;

    public static final String CURRENT_BALANCE = "currentBalance";

    ListView listView;
    BalanceListAdapter adapter;
    Toolbar toolbar;

    float currentBalanceBalance;

    Button rechargeButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mPref = getSharedPreferences("Guni",MODE_PRIVATE);
        mEdit = mPref.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_main);

        currentBalanceBalance = getIntent().getFloatExtra(CURRENT_BALANCE,0);
        toolbar = (Toolbar) findViewById(R.id.balanceMainToolbar);
        toolbar.setTitle("Balance : " + currentBalanceBalance + "/-");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rechargeButton = (Button) findViewById(R.id.balanceContentRechargeButton);
        rechargeButton.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.balanceContentListVlew);
        adapter = new BalanceListAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.balanceContentRechargeButton:
                Intent rechargeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freecharge.in/"));
                startActivity(rechargeIntent);
                break;
            default:break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*Toast.makeText(this,"ON_RESUME",Toast.LENGTH_LONG).show();
        sendBroadcast(new Intent("com.bhavikpatel.guni.balance.UPDATE"));*/
        for(Invoice.Balance newBalance : RechargeReceiver.balanceList){
            adapter.add(newBalance);
        }
        RechargeReceiver.balanceList.clear();
    }

    public void updateBalanceBalance(float newBalance){
        currentBalanceBalance = newBalance;
        mEdit.putFloat(MainActivity.BALANCE,newBalance);
        mEdit.commit();
        toolbar.setTitle("Balance : " + currentBalanceBalance + "/-");
    }

    private class BalanceListAdapter extends BaseAdapter {

        Context context;
        ArrayList<Invoice.Balance> balanceListItems = new ArrayList<>();
        DBHandler.DBIncoice dbIncoice;
        DBHandler.DBIncoice.TableBalance dbHandler;

        public BalanceListAdapter(Context context) {
            this.context = context;
            dbIncoice = new DBHandler.DBIncoice(getApplicationContext(),null,null,1);
            dbHandler = dbIncoice.new TableBalance();
            balanceListItems = dbHandler.getList();
        }

        private class ViewHolder {
            TextView amount,date,remaining,description;

            public ViewHolder(View view) {
                amount = (TextView) view.findViewById(R.id.balanceContentListViewItemAmountTV);
                date = (TextView) view.findViewById(R.id.balanceContentListViewItemDateTV);
                remaining = (TextView) view.findViewById(R.id.balanceContentListViewItemRemainingTV);
                description = (TextView) view.findViewById(R.id.balanceContentListViewItemDescriptionTV);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Invoice.Balance balanceItem = balanceListItems.get(position);
            View view = convertView;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.balance_content_list_view_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.amount.setText(balanceItem.getAmount() + "/-");
            holder.description.setText(balanceItem.getDescription().toString());
            holder.date.setText(balanceItem.getDate().toString());
            holder.remaining.setText(balanceItem.getRemaining() + "/-");

            //Description
            if(balanceItem.getDescription().equals("")){
                holder.description.setVisibility(View.GONE);
            }else holder.description.setVisibility(View.VISIBLE);

            return view;
        }

        public void add(Invoice.Balance newBalance){
            dbHandler.addIncoice(newBalance);
            balanceListItems.add(newBalance);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return balanceListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return balanceListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }

    public static class RechargeReceiver extends BroadcastReceiver{

        public static ArrayList<Invoice.Balance> balanceList = new ArrayList<>();

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle myBundle = null;
            SmsMessage [] messages = null;
            String strMessage = "";

            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                myBundle = intent.getExtras();
            }
            Toast.makeText(context,"ON_RECEIVE",Toast.LENGTH_LONG).show();
            Invoice.Balance demoBalance = new Invoice.Balance("12/5/18",513,675,"recharge of MRP 513 is successful.");
            NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Recharge of " + demoBalance.getAmount())
                    .setContentText(demoBalance.getDescription());

            Intent resultIntent = new Intent(context, BalanceActivity.class);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            context,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            notification.setContentIntent(resultPendingIntent);
            int mNotificationId = 001;

            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, notification.build());


            if (myBundle != null)
            {
                Object [] pdus = (Object[]) myBundle.get("pdus");

                messages = new SmsMessage[pdus.length];

                for (int i = 0; i < messages.length; i++)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = myBundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    }
                    else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                    Log.i("BHAVIK","messages[i].getOriginatingAddress() = " + messages[i].getOriginatingAddress());
                    strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                    strMessage += " : ";
                    strMessage += messages[i].getMessageBody();
                    strMessage += "\n";
                }

                Invoice.Balance balance = new Invoice.Balance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy",Locale.US);
                balance.setDate(sdf.format(new Date()));
                balance.setDescription(strMessage);
                Matcher patternMatcher = Pattern.compile("MRP \\d+").matcher(strMessage);
                if(patternMatcher.find()){
                    Matcher amount = Pattern.compile("\\d+").matcher(patternMatcher.group());
                    if(amount.find()){
                        balance.setAmount(Integer.valueOf(amount.group()));
                    }
                }
                patternMatcher = Pattern.compile("Balance \\d+\\.\\d+").matcher(strMessage);
                if(patternMatcher.find()){
                    Matcher remaining = Pattern.compile("\\d+\\.\\d+").matcher(patternMatcher.group());
                    if(remaining.find()){
                        balance.setRemaining(Float.valueOf(remaining.group()));
                    }
                }
                balanceList.add(balance);
                NotificationCompat.Builder balanceNoti = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Recharge of " + balance.getAmount())
                        .setContentText(balance.getDescription());

                Intent result = new Intent(context, BalanceActivity.class);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                context,
                                0,
                                result,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                balanceNoti.setContentIntent(pendingIntent);
                int balanceNotiId = 101;

                NotificationManager balanceNotifyMgr =
                        (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                balanceNotifyMgr.notify(balanceNotiId, balanceNoti.build());
                Log.e("SMS", strMessage);
            }
        }
    }

    public static class balanceService extends Service{
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}

/*
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.bhavikpatel.guni.GuniUtil;
import com.bhavikpatel.guni.R;
import com.bhavikpatel.guni.DBHandler;

import java.util.ArrayList;

*/
/**
 * Created by BHAVIK PATEL on 14-Jun-17.
 *//*


public class BalanceActivity extends AppCompatActivity {

    //final
    public static final String CURRENT_BALANCE = "currentBalance";
    private GuniUtil.GuniColor color;
    private int balanceCurrentBalance;
    private ListView balanceList;
    private BalanceListAdapter balanceListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_main);

        //set refferences
        Button rechargeButton = (Button) findViewById(R.id.balanceContentRechargeButton);
        balanceList = (ListView) findViewById(R.id.balanceContentListVlew);
        Toolbar toolbar = (Toolbar) findViewById(R.id.balanceMainToolbar);
        color = new GuniUtil.GuniColor(this);

        //initialise Adapter
        balanceListAdapter = new BalanceListAdapter(this);
        //set adapter to list
        balanceList.setAdapter(balanceListAdapter);

        //set Button
        rechargeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent rechargeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freecharge.in/"));
                        startActivity(rechargeIntent);
                    }
                }
        );

        //set actionbar
        toolbar.setTitle("Balance : " + getIntent().getIntExtra(CURRENT_BALANCE,0) + "/-");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private class BalanceListAdapter extends BaseAdapter{

        Context context;
        ArrayList<Invoice.Balance> balanceListItems;
        DBHandler.DBIncoice dbInvoice;
        DBHandler.DBIncoice.TableBalance dbHandler;


        public BalanceListAdapter(Context context) {
            this.context = context;
            balanceListItems = new ArrayList<>();
            dbInvoice = new DBHandler.DBIncoice(getApplicationContext(),null,null,1);
            dbHandler = dbInvoice.new TableBalance();
            balanceListItems = dbHandler.getList();
            Invoice.Balance invoice1 = new Invoice.Balance("My 21st Birthday Celebration" ,"21/9/2017",400,0);
            Invoice.Balance invoice2 = new Invoice.Balance("My 22st Birthday Celebration" ,"21/9/2018",50000,1);
            Invoice.Balance invoice3 = new Invoice.Balance("My 22st Birthday Celebration" ,"21/9/2018",50000,1);
            Invoice.Balance invoice4 = new Invoice.Balance("My 22st Birthday Celebration" ,"21/9/2018",50000,1);
            balanceListItems.add(invoice1);
            balanceListItems.add(invoice2);
            balanceListItems.add(invoice3);
            balanceListItems.add(invoice4);
        }

        //ViewHolder
        private class ViewHolder {
            View container;
            TextView title,amount,description,date,remaining;

            public ViewHolder(View view) {
                container = (View) view.findViewById(R.id.walletContentListViewItemContainer);
                title = (TextView) view.findViewById(R.id.walletContentListViewItemTitleTV);
                amount = (TextView) view.findViewById(R.id.walletContentListViewItemAmountTV);
                description = (TextView) view.findViewById(R.id.walletContentListViewItemDescriptionTV);
                date = (TextView) view.findViewById(R.id.walletContentListViewItemDateTV);
                remaining = (TextView) view.findViewById(R.id.walletContentListViewItemRemainingTV);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Invoice.Balance balanceItem = balanceListItems.get(position);
            View view = convertView;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.wallet_content_list_view_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.amount.setText(balanceItem.getAmount());
            holder.description.setText(balanceItem.getDescription().toString());
            holder.date.setText(balanceItem.getDate().toString());
            return view;
        }

        @Override
        public int getCount() {
            return balanceListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return balanceListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
*/
