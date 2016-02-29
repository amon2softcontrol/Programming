package com.egci392.id0201;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class route_view extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    SQLiteDatabase db;
    ImageButton back;
    ArrayList<Float>lat,lon;
    ArrayList<String> color;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        back = (ImageButton) findViewById(R.id.backToMain);

        //initialize array list
        lat = new ArrayList<>();
        lon = new ArrayList<>();
        color = new ArrayList<>();

        try{
            db = SQLiteDatabase.openDatabase("//data/data/com.egci392.id0201/databases/Quiz.db", null, SQLiteDatabase.OPEN_READWRITE);
            getLatLon(getIntent().getStringExtra("sas_route"));
        }catch (Exception e){
            Utility.isDatabaseCreated = false;
        }

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();
        // Enabling MyLocation Layer of Google Map
        //not neccessory :p looks cool though
        googleMap.setMyLocationEnabled(true);

        setUpMapIfNeeded();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), main.class);
                main.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(main);
            }
        });
 }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(13.7934f,100.3225f));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);
                    draw();
            }
        }
    }

 public void getLatLon(String route){
        try {
            String sql = "SELECT * FROM Router WHERE route=?";
            Cursor cur = db.rawQuery(sql, new String[]{route});

            cur.moveToFirst(); //move to first row

            String[] s = cur.getString(cur.getColumnIndex("latitude")).split(" ");
            String[] s1 = cur.getString(cur.getColumnIndex("longitude")).split(" ");
            String[] s2 = cur.getString(cur.getColumnIndex("color")).split(" ");

            for(int i = 0; i < s.length; i++){
                if(!s[i].equals("")){
                    lat.add(Float.parseFloat(s[i]));
                    lon.add(Float.parseFloat(s1[i]));
                }
            }

            for(int i =0; i<s2.length;i++){ if(!s2[i].equals("")){ color.add(s2[i]);}}
            cur.close();

              }catch(Exception e){
                   makeToast(e.toString());
        }
    }

    private void makeToast(String x){
        Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
    }

    private void draw() {
        try {
                googleMap.clear();
                for (int z = 0; z < lat.size(); z++) {

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLng(lat.get(z),lon.get(z)));
                        markerOptions.title("JRK");
                        // Adding the marker to the map

                        googleMap.addMarker(markerOptions);

                        if (z < lat.size() - 1) {
                            LatLng src = new LatLng(lat.get(z), lon.get(z));
                            LatLng dest = new LatLng(lat.get(z + 1), lon.get(z + 1));
                            Polyline line = mMap.addPolyline(new PolylineOptions()
                                    .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude, dest.longitude))
                                    .width(2)
                                    .geodesic(true));
                            if (color.get(z).equals("red")) line.setColor(android.graphics.Color.RED);
                            else line.setColor(android.graphics.Color.GREEN);
                        }
                }
        }catch (Exception e){
            makeToast(e.toString());
        }
    }
}
