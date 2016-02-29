package com.geetaanjali.drishyam;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.greenfrvr.rubberloader.RubberLoaderView;
import com.jcmore2.shakeit.ShakeIt;
import com.jcmore2.shakeit.ShakeListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import co.aenterhy.toggleswitch.ToggleSwitchButton;

public class Sensor extends Activity implements View.OnClickListener {

        private static final String tag1="guruji";
    private static final String tag2="remote";
    private static final String tag3="destructive";
    private static final String tag4="sms";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);
        load();
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.k1);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(icon).build();
        //getTodo();
        ImageView iconSortName = new ImageView(this);
        iconSortName.setImageResource(R.drawable.destructive1);
        ImageView iconSortName1 = new ImageView(this);
        iconSortName1.setImageResource(R.drawable.computer1);
        ImageView iconSortName12 = new ImageView(this);
        iconSortName12.setImageResource(R.drawable.amon);
        ImageView iconSortName13 = new ImageView(this);
        iconSortName13.setImageResource(R.drawable.mobileprogramming1);


        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton button1 =  itemBuilder.setContentView(iconSortName).build();
        SubActionButton button2 =  itemBuilder.setContentView(iconSortName1).build();
        SubActionButton button3 =  itemBuilder.setContentView(iconSortName12).build();
        SubActionButton button4 = itemBuilder.setContentView(iconSortName13).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .build();

        button1.setTag(tag3);
        button2.setTag(tag2);
        button3.setTag(tag1);
        button4.setTag(tag4);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


//         ShakeIt.stopShakeService(this);
        ShakeIt.initializeShakeService(this,new ShakeListener() {

            @Override
            public void onAccelerationChanged(float v, float v1, float v2) {

            }
//            ParseGeoPoint point = new ParseGeoPoint(40.0, -30.0);
            @Override
            public void onShake(float force) {
                Toast toast = Toast.makeText(getApplicationContext(), "\tJai Radhekrishna \r\nSensor is working1", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
                toast.show();
                CheckBox cb = (CheckBox) findViewById(R.id.checkBox) ;
                if(cb.isChecked()) cb.setChecked(true);
                    else cb.setChecked(false);

            }

//            @Override
//            public void onAccelerationChanged(float x, float y, float z) {
//                Toast toast = Toast.makeText(getApplicationContext(), "\tJai Radhekrishna \r\nAccelerated", Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
//                toast.show();
//
//                CheckBox cb = (CheckBox) findViewById(R.id.checkBox) ;
//                cb.toggle();
//            }
        });
        load();

        ToggleSwitchButton toggle = (ToggleSwitchButton) findViewById(R.id.toggle);

        toggle.setOnTriggerListener(new ToggleSwitchButton.OnTriggerListener() {
            @Override
            public void toggledUp() {
                Toast.makeText(Sensor.this, "Whatsapp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void toggledDown() {
                Toast.makeText(Sensor.this, "Remote Controller", Toast.LENGTH_SHORT).show();
            }
        });



    }

    protected Class<? extends Activity> getActivity(Context context,Intent intent) {
        return Sensor.class;
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.platform_botton_anim_in, R.anim.slidedown);
    }

public void load(){
    RubberLoaderView s = new RubberLoaderView(this);
    s.startLoading();
}

    @Override
    public void onClick(View v) {
        if (v.getTag().equals(tag1)){
            Intent homeIntent = new Intent(getApplicationContext(),MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag2)){
            Intent homeIntent = new Intent(getApplicationContext(),Remote.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag3)){
            Intent homeIntent = new Intent(getApplicationContext(),Destructive.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag4)){
            Intent homeIntent = new Intent(getApplicationContext(),SmsDrishyam.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }
    public void sendData(BluetoothSocket socket, int data) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(4);
        output.write(data);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(output.toByteArray());
    }
}