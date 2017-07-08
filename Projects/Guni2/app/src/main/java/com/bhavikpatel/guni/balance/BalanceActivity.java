package com.bhavikpatel.guni.balance;

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

/**
 * Created by BHAVIK PATEL on 14-Jun-17.
 */

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
