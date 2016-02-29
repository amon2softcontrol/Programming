package com.geetaanjali.drishyam;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class sensorManager extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private boolean color = false;
    private long lastUpdate;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensormanager);
        view = findViewById(R.id.sensorManager);
        view.setBackgroundColor(Color.GREEN);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == android.hardware.Sensor.TYPE_ACCELEROMETER){
            method(event);
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

    }


    public void method(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float acclt = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);

        long actualtime = System.currentTimeMillis();
        if(acclt>=2){
            if(actualtime - lastUpdate <200){
                return;
            }
            lastUpdate = actualtime;
            Toast.makeText(this, "working", Toast.LENGTH_LONG).show();
            if(color){
                view.setBackgroundColor(Color.GREEN);

            }else  view.setBackgroundColor(Color.RED);
            color = !color;
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

    }
//    @Override
//    protected void onPause(){
//        super.onResume();
//        sensorManager.unregisterListener(this);
//    }

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
}