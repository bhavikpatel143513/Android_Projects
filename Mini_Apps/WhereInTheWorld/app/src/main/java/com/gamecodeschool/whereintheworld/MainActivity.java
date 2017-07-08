package com.gamecodeschool.whereintheworld;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView txtLat;
    private TextView txtLong;
    private TextView txtSource;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLat = (TextView) findViewById(R.id.txtLat);
        txtLong = (TextView) findViewById(R.id.txtLong);
        txtSource = (TextView) findViewById(R.id.txtSource);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria,false);
        Location location = locationManager.getLastKnownLocation(provider);

        if(location != null){
            txtSource.setText("Source = " + provider);
            onLocationChanged(location);
        }else{
            txtSource.setText("Source not found");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        try {
            locationManager.requestLocationUpdates(provider, 500, 1, this);
        } catch (SecurityException e) {
            Log.i("info","Security Exception");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            Log.i("info","Security Exception");
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        txtLat.setText(String.valueOf(lat));
        txtLong.setText(String.valueOf(lng));
        txtSource.setText("Source = " + provider);
    }
    @Override
    public void onStatusChanged(String provider,
                                int status, Bundle extras) {
        txtSource.setText("Source = " + provider);
    }
    @Override
    public void onProviderEnabled(String provider) {
        txtSource.setText("Source = " + provider);
    }
    @Override
    public void onProviderDisabled(String provider) {
        txtSource.setText("Source = " + provider);
    }
}
