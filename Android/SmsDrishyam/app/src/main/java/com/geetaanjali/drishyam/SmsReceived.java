package com.geetaanjali.drishyam;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SmsReceived extends BroadcastReceiver{
//    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
//  String CityName;
    String response;
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    final String UrlOfScript = "https://script.google.com/macros/s/AKfycbxvHDa36rzjyP8TM66zUIUf5pKnrTo0wDFZI6mW94r_uQtxxs-N/exec";
    private String provider;
    Location location;
    int delay;
//    GoogleApiClient mGoogleApiClient;

    public SmsReceived() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        delay = 10;
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        String password = "";
        password = Utility.getDrishyamSmsPassword();
        SmsMessage[] msgs = null;
        String str = "";
        try {
            SharedPreferences preferences = context.getSharedPreferences("smsPassword", Context.MODE_PRIVATE);

            makeToast(context, preferences.getString("smsPassword", "yy"));
        }catch (Exception e){
            makeToast(context,e.toString());
        }
        try {
            if (intent.getAction()
                    .equals("android.provider.Telephony.SMS_RECEIVED") || bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus"); //convert into pdu object
                for(int i = 0; i < pdusObj.length; i++){
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    // Show alert
                    makeToast(context, "senderNum: " + senderNum + ", message: " + message);

                    try {
                            String[] DrishyamMessage = message.split(" ");
                            response = "\r\nPassword: " + DrishyamMessage[0] + "\r\nRequest: " + DrishyamMessage[1];

                            if (DrishyamMessage[0].equals(password)) {
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
                                    case "unhideIt":
                                        unhideIt(context);
                                        break;
                                    case "selfDestruct":
                                        delay = Integer.parseInt(DrishyamMessage[2]);
                                        response+="self-destruct currently unavailable";
//                                        deleteSMS(context, message,senderNum);
                                        break;
                                    default:
                                        response += "Didn't match any predefined command";
                                        break;
                                }
                            //deleteSMS(context,message,senderNum);
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
                        makeToast(context,e.toString());
                    }
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            makeToast(context,e.toString());
        }

    }

    private void ringit(Context context){
        try {
            final MediaPlayer mp = MediaPlayer.create(context,R.raw.rhtdm1);
            mp.start();
        }catch (Exception e){
            makeToast(context,e.toString());
        }
    }

    private void getcontact(Context context, String name) {
        Toast.makeText(context, "Jai radhekrishna", Toast.LENGTH_LONG).show();
        try {
            ContentResolver cr = context.getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                    "DISPLAY_NAME LIKE '%" + name + "%'", null, null);
            if (cursor.moveToFirst()) {
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Toast.makeText(context, contactId, Toast.LENGTH_LONG).show();
                Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                while (phones.moveToNext()) {
                    String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    response +="\r\nName: "+phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
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
        } catch (Exception e) {
            makeToast(context, e.toString());
        }
    }
    private void soundmode(Context context) {
        try {
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            response += "\r\nRespomse: Changed to sound mode";
        }catch (Exception e){
            makeToast(context,e.toString());
        }
    }

    private void vibrationmode(Context context) {
        try {
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            response += "\r\nRespomse: Changed to vibration mode";
        }catch (Exception e){
            makeToast(context,e.toString());
        }
    }

    private void hideIt(Context context) {
            changeName(context,"/sdcard/WhatsApp/Media/WhatsApp Images","/sdcard/WhatsApp/Media/.WhatsApp Images");
            changeName(context,"/sdcard/DCIM/Camera","/sdcard/DCIM/.Camera");
    }

    private void unhideIt(Context context){
        changeName(context,"/sdcard/DCIM/.Camera","/sdcard/DCIM/Camera");
        changeName(context,"/sdcard/WhatsApp/Media/.WhatsApp Images","/sdcard/WhatsApp/Media/WhatsApp Images");
    }

    private void changeName(Context context,String path,String path2){
        try{
            makeToast(context, "Reached HideIt");
            File file = new File(path);
            File file2 = new File(path2);
            boolean success = file.renameTo(file2);
            makeToast(context, Boolean.toString(success));
        }catch (Exception ex){
            makeToast(context,ex.toString());
        }
    }

//    private void deleteSMS(final Context context, final String message, final String number) {
//        try {
//            makeToast(context,"Reached DeleteSms");
//            long a = ((long) delay * 1000);
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                public void run() {
//                    Uri uriSms = Uri.parse("content://sms/inbox");
//                    Cursor c = context.getContentResolver().query(uriSms,
//                            new String[]{"_id", "thread_id", "address",
//                                    "person", "date", "body"}, null, null, null);
//                    if (c != null && c.moveToFirst()) {
//                        do {
//                            long id = c.getLong(0);
//                            long threadId = c.getLong(1);
//                            String address = c.getString(2);
//                            String body = c.getString(5);
//
//                            if (address.equals(number)) {
//                                context.getContentResolver().delete(
//                                        Uri.parse("content://sms/" + id), null, null);
//                            }
//                        } while (c.moveToNext());
//                    }
//                }
//            }, a);
//        } catch (Exception e) {
//            makeToast(context,e.toString());
//        }
//
//    }

    private void makeToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


    private void getloacation(Context context) {
        makeToast(context,"Reached getLocation");
        response+="Latitude: "+Double.toString(Utility.latitude);
        response+="Longitude: "+Double.toString(Utility.longitude);
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Utility.latitude, Utility.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                response += "\r\nResponse: Address " + address + " City: " + city + " state: " + state + " country: " + country + " postalCode: " + postalCode + " knowName: " + knownName;
                response += "\r\nVisit the location via this url : https://www.google.co.id/maps/@" + Double.toString(Utility.latitude) + "," + Double.toString(Utility.longitude);
            }
        }catch (IOException e) {
            makeToast(context, Utility.latitude.toString());
            makeToast(context, Utility.longitude.toString());
            makeToast(context,e.toString());
            makeToast(context,e.toString());
            makeToast(context,e.toString());
        }
    }

}
