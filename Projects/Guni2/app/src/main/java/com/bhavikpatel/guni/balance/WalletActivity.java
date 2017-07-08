package com.bhavikpatel.guni.balance;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bhavikpatel.guni.DBHandler;
import com.bhavikpatel.guni.GuniUtil;
import com.bhavikpatel.guni.R;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by BHAVIK PATEL on 13-Jun-17.
 */

public class WalletActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CURRENT_BALANCE = "currentBalance";
    private static final String SPEND_DIALOG_TAG = "spendDialog";
    private static final String WALLET_TYPE = "walletType";
    private static final String EDIT_WALLET_NO = "editWalletNo";

    ListView listView;
    WalletListAdapter adapter;

    View selectMenu,bottomMenu;
    Animation animBottomUp,animTopDown;
    int selectTransitionTime = 200;

    Button addButton,spendButton;
    ImageButton editButton,deleteButton,bookmarkButton;
    GuniUtil.GuniSelectEditSingle feature;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.walletMainToolbar);
        toolbar.setTitle("Wallet : " + getIntent().getIntExtra(CURRENT_BALANCE,0) + "/-");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addButton = (Button) findViewById(R.id.walletContentAddButton);
        spendButton = (Button) findViewById(R.id.walletContentSpendButton);
        editButton = (ImageButton) findViewById(R.id.walletContentEditButton);
        deleteButton = (ImageButton) findViewById(R.id.walletContentDeleteButton);
        bookmarkButton = (ImageButton) findViewById(R.id.walletContentDeleteButton);

        addButton.setOnClickListener(this);
        spendButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        bookmarkButton.setOnClickListener(this);

        selectMenu = (View) findViewById(R.id.walletContentSelectMenu);
        bottomMenu = (View) findViewById(R.id.walletContentBottomMenu);

        animBottomUp = AnimationUtils.loadAnimation(this,R.anim.bottom_up);
        animTopDown = AnimationUtils.loadAnimation(this,R.anim.top_down);

        listView = (ListView) findViewById(R.id.walletContentListView);
        adapter = new WalletListAdapter(this);
        listView.setAdapter(adapter);

        feature = new GuniUtil.GuniSelectEditSingle() {

            @Override
            public void selectFired(View view) {
                super.selectFired(view);
                WalletListAdapter.ViewHolder holder = (WalletListAdapter.ViewHolder) view.getTag();
                TransitionDrawable selectTransition = (TransitionDrawable) holder.container.getBackground();
                selectTransition.reverseTransition(selectTransitionTime);
            }

            @Override
            public void unSelectFired(View view) {
                super.unSelectFired(view);
                WalletListAdapter.ViewHolder holder = (WalletListAdapter.ViewHolder) view.getTag();
                TransitionDrawable selectTransition = (TransitionDrawable) holder.container.getBackground();
                selectTransition.reverseTransition(selectTransitionTime);
            }
        };

        feature.onSelectionMode(bottomMenu,animTopDown,true);
        feature.onUnSelectionMode(bottomMenu,animBottomUp,false);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(feature.itemSelected != -1){
                            feature.onClick(view, position);
                        }
                        else {
                            //TODO
                        }
                    }
                }
        );
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        feature.onClick(view,position);
                        return true;
                    }
                }
        );



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.walletContentAddButton:
                SpendDialog addDialog = (SpendDialog) getSupportFragmentManager().findFragmentByTag(SPEND_DIALOG_TAG);
                if( addDialog == null){
                    addDialog = new SpendDialog();
                }
                Bundle addBundle = new Bundle();
                addBundle.putBoolean(WALLET_TYPE,false);
                addDialog.setArguments(addBundle);
                addDialog.show(getSupportFragmentManager(),SPEND_DIALOG_TAG);
                break;
            case R.id.walletContentSpendButton:
                SpendDialog spendDialog = (SpendDialog) getSupportFragmentManager().findFragmentByTag(SPEND_DIALOG_TAG);
                if( spendDialog == null){
                    spendDialog = new SpendDialog();
                }
                Bundle spendBundle = new Bundle();
                spendBundle.putBoolean(WALLET_TYPE,true);
                spendDialog.setArguments(spendBundle);
                spendDialog.show(getSupportFragmentManager(),SPEND_DIALOG_TAG);
                break;
            case R.id.walletContentEditButton:
                SpendDialog editDialog = (SpendDialog) getSupportFragmentManager().findFragmentByTag(SPEND_DIALOG_TAG);
                if( editDialog == null){
                    editDialog = new SpendDialog();
                }
                Bundle editBundle = new Bundle();
                editBundle.putInt(EDIT_WALLET_NO,feature.itemSelected);
                editDialog.setArguments(editBundle);
                editDialog.show(getSupportFragmentManager(),SPEND_DIALOG_TAG);
                break;
            case R.id.walletContentDeleteButton:
                break;
            case R.id.walletContentBookmarkButton:
                break;
        }
    }

    private class WalletListAdapter extends BaseAdapter{

        Context context;
        ArrayList<Invoice.Wallet> walletListItems = new ArrayList<>();
        DBHandler.DBIncoice dbIncoice;
        DBHandler.DBIncoice.TableWallet dbHandler;

        public WalletListAdapter(Context context) {
            this.context = context;
            dbIncoice = new DBHandler.DBIncoice(getApplicationContext(),null,null,1);
            dbHandler = dbIncoice.new TableWallet();
            walletListItems = dbHandler.getList();

            Invoice.Wallet wallet1 = new Invoice.Wallet("Banana","nsd df sd  df sd  s sdc s","30/4/18",432,0,0);
            Invoice.Wallet wallet2 = new Invoice.Wallet("Tsdv","nsd df sd  df sd  s sdc s","30/4/18",432,0,0);
            Invoice.Wallet wallet3 = new Invoice.Wallet("vwssvw","nsd df sd  df sd  s sdc s","30/4/18",432,0,0);

            walletListItems.add(wallet1);
            walletListItems.add(wallet2);
            walletListItems.add(wallet3);
        }

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
            Invoice.Wallet walletItem = walletListItems.get(position);
            View view = convertView;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.wallet_content_list_view_item,null);
                ViewHolder holder = new ViewHolder(view);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.title.setText(walletItem.getTitle().toString());
            holder.amount.setText(walletItem.getAmount() + "/-");
            holder.description.setText(walletItem.getDescription().toString());
            holder.date.setText(walletItem.getDate().toString());
            return view;
        }

        public void add(Invoice.Wallet newWallet){
            dbHandler.addIncoice(newWallet);
            walletListItems.add(newWallet);
            notifyDataSetChanged();
        }

        public void edit(int position, Invoice.Wallet editedWallet){
            Invoice.Wallet wallet = walletListItems.get(position);
            wallet.setTitle(editedWallet.getTitle().toString());
            wallet.setAmount(editedWallet.getAmount());
            wallet.setDescription(editedWallet.getDescription().toString());
            wallet.setDate(editedWallet.getDate().toString());
            dbHandler.updatePosition(position,wallet);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return walletListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return walletListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }

    public static class SpendDialog extends DialogFragment implements View.OnClickListener,View.OnLongClickListener{

        int amountValue = 0;
        boolean isSpend = false;
        int edit = -1;

        WalletActivity parent;
        EditText title,amount,description,date;
        Button one,two,five,ten,fifty,hundred,fiveHundred,twoThousand,cancleButton,doneButton,dateButton;
        Calendar myCalendar;
        DatePickerDialog.OnDateSetListener dateListener;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            parent = (WalletActivity) getActivity();
            isSpend = getArguments().getBoolean(WALLET_TYPE,true);
            edit = getArguments().getInt(EDIT_WALLET_NO,-1);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.wallet_content_spend_dia,null);


            title = (EditText) view.findViewById(R.id.walletContentSpendDiaTitleET);
            amount = (EditText) view.findViewById(R.id.walletContentSpendDiaAmountET);
            description = (EditText) view.findViewById(R.id.walletContentSpendDiaDescriptionET);
            date = (EditText) view.findViewById(R.id.walletContentSpendDiaDateET);

            one = (Button) view.findViewById(R.id.walletContentSpendDiaOneButton);
            two = (Button) view.findViewById(R.id.walletContentSpendDiaTwoButton);
            five = (Button) view.findViewById(R.id.walletContentSpendDiaFiveButton);
            ten = (Button) view.findViewById(R.id.walletContentSpendDiaTenButton);
            fifty = (Button) view.findViewById(R.id.walletContentSpendDiaFiftyButton);
            hundred = (Button) view.findViewById(R.id.walletContentSpendDiaHundredButton);
            fiveHundred = (Button) view.findViewById(R.id.walletContentSpendDiaFiveHundredButton);
            twoThousand = (Button) view.findViewById(R.id.walletContentSpendDiaTwoThousandButton);

            dateButton = (Button) view.findViewById(R.id.walletContentSpendDiaDateButton);

            cancleButton = (Button) view.findViewById(R.id.walletContentSpendDiaCancleButton);
            doneButton = (Button) view.findViewById(R.id.walletContentSpendDiaDoneButton);

            one.setOnClickListener(this);
            two.setOnClickListener(this);
            five.setOnClickListener(this);
            ten.setOnClickListener(this);
            fifty.setOnClickListener(this);
            hundred.setOnClickListener(this);
            fiveHundred.setOnClickListener(this);
            twoThousand.setOnClickListener(this);
            dateButton.setOnClickListener(this);
            cancleButton.setOnClickListener(this);
            doneButton.setOnClickListener(this);

            one.setOnLongClickListener(this);
            two.setOnLongClickListener(this);
            five.setOnLongClickListener(this);
            ten.setOnLongClickListener(this);
            fifty.setOnLongClickListener(this);
            hundred.setOnLongClickListener(this);
            fiveHundred.setOnLongClickListener(this);
            twoThousand.setOnLongClickListener(this);

            myCalendar = Calendar.getInstance();

            dateListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat = "MM/dd/yy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    date.setText(sdf.format(myCalendar.getTime()));
                }
            };



            builder.setView(view);
            return builder.create();
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.walletContentSpendDiaOneButton:
                    amountValue += 1;
                    break;
                case R.id.walletContentSpendDiaTwoButton:
                    amountValue += 2;
                    break;
                case R.id.walletContentSpendDiaFiveButton:
                    amountValue += 5;
                    break;
                case R.id.walletContentSpendDiaTenButton:
                    amountValue += 10;
                    break;
                case R.id.walletContentSpendDiaFiftyButton:
                    amountValue += 50;
                    break;
                case R.id.walletContentSpendDiaHundredButton:
                    amountValue += 100;
                    break;
                case R.id.walletContentSpendDiaFiveHundredButton:
                    amountValue += 500;
                    break;
                case R.id.walletContentSpendDiaTwoThousandButton:
                    amountValue += 2000;
                    break;
                case R.id.walletContentSpendDiaDateButton:
                    new DatePickerDialog(getContext(), dateListener, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    break;
                case R.id.walletContentSpendDiaCancleButton:
                    dismiss();
                    break;
                case R.id.walletContentSpendDiaDoneButton:
                    if(edit != -1){
                        Invoice.Wallet editedWallet = new Invoice.Wallet(title.getText().toString(), description.getText().toString(),
                                date.getText().toString(), amountValue, 0, 0);
                        parent.adapter.edit(edit,editedWallet);
                    }else if(isSpend){
                        Invoice.Wallet newWallet = new Invoice.Wallet(title.getText().toString(), description.getText().toString(),
                                date.getText().toString(), amountValue, 0, 0);
                        parent.adapter.add(newWallet);
                    }else {
                        Invoice.Wallet newWallet = new Invoice.Wallet(title.getText().toString(), description.getText().toString(),
                                date.getText().toString(), amountValue, 0, 1);
                        parent.adapter.add(newWallet);
                    }
                    dismiss();
                    break;
            }
            amount.setText(amountValue + "/-");
        }

        @Override
        public boolean onLongClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.walletContentSpendDiaOneButton:
                    amountValue -= 1;
                    break;
                case R.id.walletContentSpendDiaTwoButton:
                    amountValue -= 2;
                    break;
                case R.id.walletContentSpendDiaFiveButton:
                    amountValue -= 5;
                    break;
                case R.id.walletContentSpendDiaTenButton:
                    amountValue -= 10;
                    break;
                case R.id.walletContentSpendDiaFiftyButton:
                    amountValue -= 50;
                    break;
                case R.id.walletContentSpendDiaHundredButton:
                    amountValue -= 100;
                    break;
                case R.id.walletContentSpendDiaFiveHundredButton:
                    amountValue -= 500;
                    break;
                case R.id.walletContentSpendDiaTwoThousandButton:
                    amountValue -= 2000;
                    break;
            }
            amount.setText(amountValue + "/-");
            return true;
        }
    }
}