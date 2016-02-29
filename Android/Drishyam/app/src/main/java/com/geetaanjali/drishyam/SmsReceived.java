package com.geetaanjali.drishyam;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SmsReceived extends BroadcastReceiver  {

    String CityName;
    String response;
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    final String UrlOfScript = "https://script.google.com/macros/s/AKfycbxvHDa36rzjyP8TM66zUIUf5pKnrTo0wDFZI6mW94r_uQtxxs-N/exec";
    private SoundPool soundPool;
    private int soundID;
    boolean loaded = false;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    Location location;

    public SmsReceived() {

    }


    @Override
    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        try {

            if (intent.getAction()
                    .equals("android.provider.Telephony.SMS_RECEIVED") || bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    // Show alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration);
                    toast.show();
                    try {
                        String[] DrishyamMessage = message.split(" ");
                        response = "\r\nPassword: " + DrishyamMessage[0] + "\r\nRequest: " + DrishyamMessage[1];

                        if (DrishyamMessage[0].equals(Utility.getDrishyamSmsPassword())) {
                            switch (DrishyamMessage[1]) {
                                case "ringIt":
                                    ringit(context);
                                    break;
                                case "soundMode":
                                    soundmode(context);
                                    break;
                                case "vibrateMode":
                                    vibrationmode(context);
                                    break;
                                case "getLocation":
                                    getloacation(context);
                                    break;
                                case "getContact":
                                    getcontact(context, DrishyamMessage[2]);
                                    break;
                                case "hideIt":
                                    hideIt(context);
                                    break;
                                default: response +="Didn't match any predefined command";
                                    break;
                            }
                            WebRequest.callService(new AsyncHttpResponseHandler() {

                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                }
                            }, senderNum, message + response, UrlOfScript);
                        }
                    } catch (Exception e) {

                    }
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }

    }

    public void ringit(Context context) {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        soundID = soundPool.load(context, R.raw.rhtdm1, 1);
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        float maxVolume = (float) audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = maxVolume;
        // Is the sound loaded already?
        if (loaded) {
            soundPool.play(soundID, volume, volume, 1, 0, 1f);
        }
        response += "\r\nResponse: Playing rhtdm";
    }

    public void getcontact(Context context, String name) {

        Toast.makeText(context, "Jai radhekrishna" ,Toast.LENGTH_LONG).show();

        try {
            ContentResolver cr = context.getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                    "DISPLAY_NAME LIKE '%" + name + "%'", null, null);
            if (cursor.moveToFirst()) {
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Toast.makeText(context,contactId,Toast.LENGTH_LONG).show();
                Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                while (phones.moveToNext()) {
                    String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                    switch (type) {
                        case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                            response += "\r\nHome Number: " + number;
                            // do something with the Home number here...
                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                            response += "\r\nMobile Number: " + number;
                            // do something with the Mobile number here...
                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                            response += "\r\nWork Number: " + number;
                            // do something with the Work number here...
                            break;
                        default:
                            response += "\r\nError achieving contact";
                            break;
                    }
                }
                phones.close();
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void getloacation(Context context) {
        double longitude;
        double latitude;
        try {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

        }catch (Exception e){
        }
        longitude = location.getLongitude();
        latitude = location.getLatitude();

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(longitude, latitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                response+="\r\nRespomse: Address " +address + " City: " + city + " state: " + state + " country: " + country + " postalCode: " + postalCode + " knowName: " + knownName;
                response+="\r\nVisit the location via this url : https://www.google.co.id/maps/@"+latitude+","+longitude;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void soundmode(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        response += "\r\nRespomse: Changed to sound mode";
    }

    public void vibrationmode(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        response += "\r\nRespomse: Changed to vibration mode";
    }

    public void hideIt(Context context){

    }

}