package com.gamecodeschool.dualfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Interpolator;

public class MainActivity extends AppCompatActivity implements ActivityComs{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dualfragment);
        AddressListFragment addressListFragment = new AddressListFragment();
        getFragmentManager().beginTransaction().add(R.id.listFragmentHolder,addressListFragment).commit();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemSelected(int position) {
        // Is there a layout with an id that matches the detail container?
        if (findViewById(R.id.detailFragmentHolder) == null) {
// If not we need to start a new activity
            Intent i = new Intent(this, PortraitDetailActivity.class);
            i.putExtra("Position", position);
            startActivity(i);
        } else {
            // Fragment already exists
            FragmentManager fManager = getFragmentManager();
            FragmentTransaction fTransaction = fManager.beginTransaction();
            Fragment fragOld =
                    fManager.findFragmentById(R.id.detailFragmentHolder);
            Fragment fragNew =
                    AddressDetailFragment.newInstance(position);
            if (fragOld != null) {
                fTransaction.remove(fragOld);
            }
            fTransaction.add(R.id.detailFragmentHolder, fragNew).commit();
        }
    }
}
