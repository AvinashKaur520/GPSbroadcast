package com.example.rajnish.gpsbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class MainActivity extends Activity
{

    Button button;
    TextView textview;
    LocationManager locationManager;
    boolean GpsStatus, networkStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button1);
        textview = findViewById(R.id.textView1);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                  CheckGpsStatus(MainActivity.this);
                  textview.setText("Location Services Is GpsStatus "+GpsStatus+" networkStatus "+networkStatus);
                  Toast.makeText(MainActivity.this, "Location Services Is GpsStatus "+GpsStatus+" networkStatus "+networkStatus, Toast.LENGTH_SHORT).show();
            }
        });

        this.registerReceiver(this.Receiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
    }

    private BroadcastReceiver Receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
                CheckGpsStatus(context);
                textview.setText("Location Services Is GpsStatus "+GpsStatus+" networkStatus "+networkStatus);
                Toast.makeText(MainActivity.this, "Location Services Is GpsStatus "+GpsStatus+" networkStatus "+networkStatus, Toast.LENGTH_SHORT).show();
        }
    };

    public void CheckGpsStatus(Context context)
    {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        networkStatus = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}