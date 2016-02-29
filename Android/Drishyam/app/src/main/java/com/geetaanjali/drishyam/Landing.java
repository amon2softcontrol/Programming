package com.geetaanjali.drishyam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Amon on 20/9/2558.
 */

public class Landing extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.platform_switch);

        ImageView landing_destructive1 = (ImageView) findViewById(R.id.destructive1);
        ImageView landing_remote = (ImageView) findViewById(R.id.Remote1);
        ImageView landing_sensor = (ImageView) findViewById(R.id.Sensor1);
        ImageView landing_mobilePro = (ImageView) findViewById(R.id.mobilepro);
        // set a onclick listener for when the button gets clicked
        landing_destructive1.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Destructive Message", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 120, 120);
                toast.show();
                navigateToDestructiveActivity();
            }
        });

        landing_sensor.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Sensor Application", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 120, 120);
                toast.show();
                navigatetoSensorActivity();
            }
        });

        landing_remote.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Remote  Application", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 120, 120);
                toast.show();
                navigatetoRemoteActivity();
            }
        });

        landing_mobilePro.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     Toast toast = Toast.makeText(getApplicationContext(), "Sms  Application", Toast.LENGTH_SHORT);
                                                     toast.setGravity(Gravity.CENTER, 120, 120);
                                                     toast.show();
                                                     navigatetomobileProActivity();
                                                 }
                                             }
        );
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "Not allowed", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
        toast.show();
    }
    public void navigateToDestructiveActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),Destructive.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

    public void navigatetoRemoteActivity(){
        Intent loginIntent = new Intent(getApplicationContext(),Remote.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

    public void navigatetoSensorActivity(){
        Intent loginIntent = new Intent(getApplicationContext(),bluetoothMainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

    public void navigatetomobileProActivity(){
        Intent loginIntent = new Intent(getApplicationContext(), SmsDrishyam.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

}

