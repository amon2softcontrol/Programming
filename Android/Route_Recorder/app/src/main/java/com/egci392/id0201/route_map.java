package com.egci392.id0201;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Random;

public class route_map extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    ImageButton back,addLatLan,save;
    TextView title;
    Button color;
    String  rang;
    SQLiteDatabase db;
    EditText latitude, longitude;
    ArrayList<String> Lat,Lon,sas_color;
    GoogleMap googleMap;
    boolean MAXspeed;

    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {

            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter

            if (mAccel > 2) {
                computeLatitudeLongitude(0.0012f);
                        color.setBackgroundColor(android.graphics.Color.parseColor("#ffb93221"));
                        rang = "red";
            }
            if (mAccel > 4) {
                computeLatitudeLongitude(0.005f);
                rang = "green";
                color.setBackgroundColor(android.graphics.Color.parseColor("#ff8bc34a"));
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        title = (TextView) findViewById(R.id.mapHeader);
        addLatLan = (ImageButton) findViewById(R.id.tempAdd);
        color = (Button) findViewById(R.id.speedColor);
        back = (ImageButton) findViewById(R.id.imageButton2);
        save = (ImageButton) findViewById(R.id.imageButton);

        Lat = new ArrayList<>();
        Lon = new ArrayList<>();
        sas_color = new ArrayList<>();

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();
        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);//not neccessory :p looks cool though

        //initialize sensor detect
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        latitude = (EditText) findViewById(R.id.editText2);
        longitude = (EditText) findViewById(R.id.editText3);

        try{
            createDatabase();
        }catch (Exception e){
            makeToast("Error opening database for readWrite");
        }

        setUpMapIfNeeded();

        back = (ImageButton) findViewById(R.id.imageButton2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newRoute = new Intent(getApplicationContext(),newRoute.class);
                newRoute.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(newRoute);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToDatabase(getIntent().getStringExtra("routeName"));
                Intent main = new Intent(getApplicationContext(), main.class);
                main.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(main);
            }
        });

        addLatLan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MAXspeed = false;
                Lat.add(latitude.getText().toString());
                Lon.add(longitude.getText().toString());
                sas_color.add(rang);
                draw();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void createDatabase(){
        db = openOrCreateDatabase(
                "Quiz.db"
                , MODE_PRIVATE
                , null
        );
        final String CREATE_USER_TABLE =
                "CREATE TABLE IF NOT EXISTS Router ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "route VARCHAR,"
                        + "latitude VARCHAR,"
                        + "longitude VARCHAR,"
                        + "color VARCHAR);";

        db.execSQL(CREATE_USER_TABLE);
        Utility.isDatabaseCreated = true;
    }

    private void insertToDatabase(String route){
        try {
            String latitude=" ",longitude=" ",color=" ";
            for(int i = 0; i< Lat.size();i++){
                latitude+=Lat.get(i)+" ";
                longitude+=Lon.get(i)+" ";
            }
            for(int i = 0; i< sas_color.size();i++){
                color+=sas_color.get(i)+" ";
            }
            makeToast(latitude);
            makeToast(longitude);
            makeToast(color);
            ContentValues values = new ContentValues();
            values.put("route", route);
            values.put("latitude", latitude);
            values.put("longitude", longitude);
            values.put("color", color);
            db.insert("Router", null, values);
        } catch (Exception e) {
            makeToast("Error occured during sql insert" + e.toString());
        }
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
}
    private void setUpMap() {

        Lat.add(Float.toString(13.7934f));
        Lon.add(Float.toString(100.3225f));

        latitude.setText(Float.toString(13.7934f));
        longitude.setText(Float.toString(100.3225f));

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(13.7934f,
                        100.3225f));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
              mMap.addMarker(new MarkerOptions().position(new LatLng(13.7934f,
                    100.3225f)).title("Marker")).setIcon(BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_action_place_light));
    }

    private void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        makeToast("Press back button at the topleft");
    }

    private void computeLatitudeLongitude(float speed){

        float minX = -85.0f, maxX = 85.0f;
        float minX1 = -179.999989f, maxX1 = 179.999989f;

        //rand.nextFloat() * (maxX - minX) + minX; FORMULA
        //in order to achieve non-linear line
        Random rand = new Random(), rand1 = new Random();
        float lat,lon;
        lat = Float.parseFloat(latitude.getText().toString());
        lon = Float.parseFloat(longitude.getText().toString());
        //

        float finalX = rand.nextFloat() * (lat+speed - lat) + lat;
        float finalX1 = rand1.nextFloat() * (lon+speed - lon) + lon;

        if(finalX<minX)finalX = -85.0f;
            if(finalX>maxX)finalX = 85.0f;
                if(finalX1<minX1) finalX1 = -179.999989f;
                    if(finalX1>maxX1) finalX1 = 179.999989f;

        latitude.setText(Float.toString(finalX));
        longitude.setText(Float.toString(finalX1));
  }

    private void draw() {

        googleMap.clear();

        for (int z = 0; z < Lat.size(); z++) {

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(Float.parseFloat(Lat.get(z)), Float.parseFloat(Lon.get(z))));
            markerOptions.title("JRK");
        // Adding the marker to the map

            googleMap.addMarker(markerOptions);

            if(z<Lat.size()-1){
                LatLng src = new LatLng(Float.parseFloat(Lat.get(z)), Float.parseFloat(Lon.get(z)));
                LatLng dest = new LatLng(Float.parseFloat(Lat.get(z + 1)), Float.parseFloat(Lon.get(z + 1)));
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude, dest.longitude))
                        .width(2)
                        .geodesic(true));
                if (sas_color.get(z).equals("red")) line.setColor(android.graphics.Color.RED);
                    else line.setColor(android.graphics.Color.GREEN);
            }
        }
    }
}
