package com.geetaanjali.drishyam;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SmsDrishyam extends AppCompatActivity implements View.OnClickListener{

    EditText email, password;
    TextView text1,text2;
    ClipboardManager clipboard;
    ClipData clip;
    boolean saveNewPreferences;
    Button save,submitChange,info2,info;
    private static final String tag1="save";
    private static final String tag2="info";
    private static final String tag14="sms";
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        saveNewPreferences = false;
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        text1 = (TextView) findViewById(R.id.smsheader);
        text2 = (TextView) findViewById(R.id.textView6);
        save = (Button)  findViewById(R.id.btnLogin);
        info = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        text2.setVisibility(View.INVISIBLE);
////
//        //give buttons tag
        info.setTag(tag2);
        save.setTag(tag1);
//
//        //give them a listerner for click event
        save.setOnClickListener(SmsDrishyam.this);
        info.setOnClickListener(SmsDrishyam.this);

        if(Registerd()){
            doChanges();
        }
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clip = ClipData.newPlainText("label", "amon@geetaanjali.com");
        clipboard.setPrimaryClip(clip);

        //initiate sensor
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals(tag1)) {
            String emailCheck = email.getText().toString();
            String passwordCheck = password.getText().toString();
            if (saveNewPreferences) {
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                String check = preferences.getString("smsPassword","yy");
                if(emailCheck.equals(check)){
                    preferences.edit().putString("smsPassword", passwordCheck.toString()).commit();
                    Utility.setDrishyamSmsPassword(passwordCheck.toString());
                    Toast.makeText(this, "Password: "+passwordCheck, Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "Password Changed", Toast.LENGTH_LONG).show();
                }
                else  Toast.makeText(this, "Password Does not match", Toast.LENGTH_LONG).show();
            } else {
                if (Utility.validate(emailCheck)) {
                    if (Utility.isNotNull(passwordCheck)) {
                        SharedPreferences saveEmailnPassword = getPreferences(MODE_PRIVATE);
                        saveEmailnPassword.edit().putString("smsEmail", emailCheck).commit();
                        saveEmailnPassword.edit().putString("smsPassword", passwordCheck).commit();
                        Toast.makeText(this, "Password: " + passwordCheck, Toast.LENGTH_LONG).show();
                        Toast.makeText(this, "Your information has been saved\r\n You can now enjoy our services", Toast.LENGTH_LONG).show();
                        Utility.setDrishyamSmsPassword(passwordCheck.toString());
                        doChanges();
                    } else
                        Toast.makeText(this, "Password Field Can not be blank", Toast.LENGTH_LONG).show();
                } else Toast.makeText(this, "Enter a valid email", Toast.LENGTH_LONG).show();
            }
        }

        if (v.getTag().equals(tag2)){
            String infos = "This helps to control or access your smartphone through sms ";
            infos+="rather than through the traditional internet protocol.\r\n";
            infos+="\r\n\r\nEmail: enter your email to activate free subscription.\r\n";
            infos+="\r\n\r\nPassword: enter 4 digit numerical password for valid authentication.\r\n";
            infos+="\r\n\t\t\tTo use our service -:\r\n\r\nSend your 4 digit password space commands to your phone no.\r\n";
            infos+="\r\nExample1): Send 0123 selfDestruct <timeLimit,default 30> <message> to <phone number> \r\n";
            infos+="\r\nExample2): Send 0123 getContact <contactName> to 0922828604 \r\n";
            infos+="\r\nList of commands: can be accessed from the homepage drawerpane";
            infos += "\r\n\r\n\tNOTE: commands are caseSensiTive\n";
            Utility.showInfo(SmsDrishyam.this, infos);
        }
     }

    private boolean Registerd()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String  savedEmailnPassword = preferences.getString("smsEmail", "xx");
        String  savedEmailnPassword1 = preferences.getString("smsPassword", "xx");

        if (savedEmailnPassword.equals("xx") || savedEmailnPassword1.equals("xx"))
            return false;

        return true;
    }

    public  void doChanges(){
        email.setHint("Enter your old password");
        password.setHint("Enter your new password");
        email.setText("");
        password.setText("");
        save.setBackgroundResource(R.color.violet);
        save.setText("Save Changes");
        text1.setText("Change Preferences");
        text2.setVisibility(View.VISIBLE);
        saveNewPreferences = true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private final SensorEventListener mSensorListener = new SensorEventListener() {

    public void onSensorChanged(SensorEvent se) {
        float x = se.values[0];
        float y = se.values[1];
        float z = se.values[2];
        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        if (mAccel > 2) {
            random();
        }
    }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void random(){
        int minX = 1212,maxX = 9999;
        Random rand = new Random();
        int result = rand.nextInt(maxX - minX + 1) + minX;
        password.setText(Integer.toString(result));

    }
}