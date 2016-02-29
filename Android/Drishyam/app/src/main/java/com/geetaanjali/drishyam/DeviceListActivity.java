package com.geetaanjali.drishyam;

/**
 * Created by Amon on 1/11/2558.
 */

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

public class DeviceListActivity extends Activity {
    private ListView mListView;
    private DeviceListAdapter mAdapter;
    private ArrayList<BluetoothDevice> mDeviceList;
    private BluetoothSocket bTSocket;
    BluetoothSocket temp = null;
    boolean isConnected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paired_devices);

        mDeviceList		= getIntent().getExtras().getParcelableArrayList("device.list");
        mListView		= (ListView) findViewById(R.id.lv_paired);

        mAdapter		= new DeviceListAdapter(this);

        mAdapter.setData(mDeviceList);
        mAdapter.setListener(new DeviceListAdapter.OnPairButtonClickListener(){
            @Override
            public void onPairButtonClick(int position) {
                BluetoothDevice device = mDeviceList.get(position);

                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    Toast.makeText(getApplicationContext(),device.getAddress().toString(),Toast.LENGTH_LONG).show();
//                    device.getAddress();
                    UUID uuid = UUID.randomUUID();
                    Toast.makeText(getApplicationContext(),uuid.toString(),Toast.LENGTH_LONG).show();
                    isConnected = connectIT(device, uuid);
                    if(isConnected){
                        Intent sensorIntent = new Intent(DeviceListActivity.this, Sensor.class);
                        startActivity(sensorIntent);
                    }
                } else {
                    showToast("Pairing...");
                    pairDevice(device);
                }
            }
        });

        mListView.setAdapter(mAdapter);
        registerReceiver(mPairReceiver, new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED));
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mPairReceiver);
        super.onDestroy();
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void pairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final BroadcastReceiver mPairReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state 		= intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                final int prevState	= intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);

                if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                    showToast("Paired");
                } else if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED){
                    showToast("Unpaired");
                }

                mAdapter.notifyDataSetChanged();
            }
        }
    };


    private void sendDataToPairedDevice(String message ,BluetoothDevice device){
        byte[] toSend = message.getBytes();
        try {
            UUID applicationUUID = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
            BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(applicationUUID);
            OutputStream mmOutStream = socket.getOutputStream();
            mmOutStream.write(toSend);
            // Your Data is sent to  BT connected paired device ENJOY.
        } catch (IOException e) {

        }
    }


    public boolean connectIT(BluetoothDevice bTDevice, UUID mUUID) {

        try {
            temp = bTDevice.createRfcommSocketToServiceRecord(mUUID);
        } catch (IOException e) {
         Toast.makeText(DeviceListActivity.this,"Jai Radhekrishna1",Toast.LENGTH_SHORT);
            return false;
        }
        try {
            bTSocket.connect();
        } catch(IOException e) {
            Toast.makeText(DeviceListActivity.this, "Jai Radhekrishna1", Toast.LENGTH_SHORT);
            try {
                bTSocket.close();
            } catch(IOException close) {
                Toast.makeText(DeviceListActivity.this, "Jai Radhekrishna1", Toast.LENGTH_SHORT);
                return false;
            }
        }
        return true;
    }

    public BluetoothSocket getSocket(){
        return temp;
    }

    public boolean cancel() {
        try {
            bTSocket.close();
        } catch(IOException e) {
            Log.d("CONNECTTHREAD","Could not close connection:" + e.toString());
            return false;
        }
        return true;
    }
}
