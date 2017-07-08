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

public class DataActivity extends AppCompatActivity{

    //final
    public static final String CURRENT_BALANCE = "currentBalance";
    private GuniUtil.GuniColor color;
    private int dataCurrentBalance;
    private ListView dataList;
    private DataListAdapter dataListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_main);

        //set refferences
        Button rechargeButton = (Button) findViewById(R.id.dataContentAddButton);
        dataList = (ListView) findViewById(R.id.dataContentListView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dataMainToolbar);
        color = new GuniUtil.GuniColor(this);

        //initialise Adapter
        dataListAdapter = new DataListAdapter(this);
        //set adapter to list
        dataList.setAdapter(dataListAdapter);

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
        toolbar.setTitle("Data : " + getIntent().getIntExtra(CURRENT_BALANCE,0) + "MB");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class DataListAdapter extends BaseAdapter {

        Context context;
        ArrayList<Invoice.Data> dataListItems;
        DBHandler.DBIncoice dbInvoice;
        DBHandler.DBIncoice.TableData dbHandler;


        public DataListAdapter(Context context) {
            this.context = context;
            dataListItems = new ArrayList<>();
            dbInvoice = new DBHandler.DBIncoice(getApplicationContext(),null,null,1);
            dbHandler = dbInvoice.new TableData();
            dataListItems = dbHandler.getList();
            Invoice.Data invoice1 = new Invoice.Data("My 21st Birthday Celebration" ,"21/9/2017",9000,0);
            Invoice.Data invoice2 = new Invoice.Data("My 22st Birthday Celebration" ,"21/9/2018",12000,1);
            dataListItems.add(invoice1);
            dataListItems.add(invoice2);
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
            Invoice.Data dataItem = dataListItems.get(position);
            View view = convertView;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.wallet_content_list_view_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.amount.setText(dataItem.getAmount());
            holder.description.setText(dataItem.getDescription().toString());
            holder.date.setText(dataItem.getDate().toString());
            return view;
        }
        @Override
        public int getCount() {
            return dataListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return dataListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
