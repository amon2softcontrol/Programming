package com.geetaanjali.drishyam;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.entity.Slide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener{

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerListenter; //actionbar toggle
    LocationManager locationManager ;
    String provider;
    private MyAdapter myAdapter;
    private SmsReceived mSMSreceiver;
    private IntentFilter mIntentFilter;
    PowerManager.WakeLock wakeLock;
    Location location;

    IntentFilter filter1;
    private String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(isFirstTime()) introduction();

        SharedPreferences Passwords = getPreferences(MODE_PRIVATE);
        String password = Passwords.getString("smsPassword","yy");
        if(password.equals("yy"))Utility.isPasswordSet = false;
            else {
                    Utility.isPasswordSet = true;
                    Utility.setDrishyamSmsPassword(password);
        }
        Toast.makeText(getApplicationContext(),password,Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

//        //enable gps
         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);// Getting LocationManager object
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))showGPSDisabledAlertToUser();

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);

        if(provider!=null && !provider.equals("")){
            // Get the location from the given provider
            try {
                location = locationManager.getLastKnownLocation(provider);
                locationManager.requestLocationUpdates(provider, 0, 0, this);
            }catch (Exception e){

            }
            if(location!=null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
         //hold cpu
        PowerManager mgr = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyWakeLock12");
        wakeLock.acquire();

        options = this.getResources().getStringArray(R.array.Options);
        listView = (ListView) findViewById(R.id.drawerList);
        myAdapter = new MyAdapter(this, options,new int[]{R.drawable.information,R.drawable.register,R.drawable.password, R.drawable.contact});
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectedItem(position);
               }
        });

        mSMSreceiver = new SmsReceived();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //                                                                 blind people         for people with eye-sight problem
        drawerListenter = new ActionBarDrawerToggle(this,drawerLayout , R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast toast = Toast.makeText(getApplicationContext(), "drawer opened", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
                toast.show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Contact Designer");
                Toast toast = Toast.makeText(getApplicationContext(),"drawer closed ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
                toast.show();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }};

        drawerLayout.setDrawerListener(drawerListenter);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set password
//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        String password = preferences.getString("smsPassword", "yy");
//        if(password.equals("yy"));else Utility.setDrishyamSmsPassword(password);
    }

   private void startRegisterActivity(){
        Intent platformIntent = new Intent(this,SmsDrishyam.class);
        platformIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(platformIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

    private void commandList(){
        String infos = "1) Vibrate [vibrateMode]\r\n";
        infos+="2) Ring [ringIt]\r\n";
        infos+="3) Sound [soundMode]\r\n";
        infos+="4) Location [getLocation]\r\n";
        infos+="5) Contact [getContact]\r\n";
        infos+="6) Gallery [hideIt]\r\n";
        infos+="7) Gallery [unhideIt]\r\n";
        Utility.showInfo(MainActivity.this, infos);
    }

    private void startContactActivity(){
        Intent platformIntent = new Intent(this,Contact.class);
        platformIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(platformIntent);
        overridePendingTransition(R.anim.viewtransition, R.anim.slidedown);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListenter.syncState();
    }

    public void selectedItem(int position){
        listView.setItemChecked(position, true);
        setTitlee(options[position], position);
    }

    public void setTitlee(String title, final int pos){
        getSupportActionBar().setTitle(title);
        Snackbar snackbar = Snackbar
                .make(listView, options[pos], Snackbar.LENGTH_LONG)
                .setAction("Go to " + options[pos]+"->", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (pos){
                            case 0: introduction();
                                break;

                            case 1: startRegisterActivity();
                                break;

                            case 2: commandList();
                                break;

                            case 3: startContactActivity();
                                break;
                            case 4:
                                String urlStr = "https://www.youtube.com/user/almightyamon/videos";
                                Intent intent4 = new Intent(Intent.ACTION_VIEW);
                                intent4.setData(Uri.parse(urlStr));
                                startActivity(intent4);
                                break;

                            case 5:     Intent intent5 = new Intent(Intent.ACTION_DIAL);
                                intent5.setData(Uri.parse("tel:0922828604"));
                                startActivity(intent5);
                                break;

                            default:  break;
                        }
                    }
                });

        snackbar.show();
    }

    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();
        result.add(new Slide().withTitle("Monitor Your Device").withDescription(" Monitor your device remotely.\r\nRemote control your cell, get updated through sms notification and WEB LOG")
                .withColorResource(R.color.suggestion_highlight_text).withImage(R.drawable.detective));
        result.add(new Slide().withTitle("Controller").withDescription("Ring, Vibrate, Silent, get location, Lock or Unlock your device remotely. Just Fancy!!")
                .withColorResource(R.color.user_icon_7).withImage(R.drawable.sensors));
        result.add(new Slide().withTitle("Hide Gallery").withDescription("Hide your gallery content, To avoid any stranger to go through your private pictures")
                .withColorResource(R.color.yellow).withImage(R.drawable.ic_action_picture));

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem item = menu.findItem(R.id.action_search1);
//        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListenter.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerListenter.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    //ow();
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "No more back", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 120, 120);
        toast.show();
        overridePendingTransition(R.anim.platform_botton_anim_in, R.anim.slidedown);
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    private  void introduction(){
        new IntroductionBuilder(this).withSlides(generateSlides()).withStyle(IntroductionBuilder.STYLE_FULLSCREEN).introduceMyself();
    }

    private final BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            if(intent.getAction().equalsIgnoreCase("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED"))
            {
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wakeLock.release();
        unregisterReceiver(mSMSreceiver);

    }

    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
            Utility.latitude = location.getLatitude();
            Utility.latitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
