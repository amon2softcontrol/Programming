package com.geetaanjali.drishyam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class SmsDrishyam extends Activity implements View.OnClickListener{
    EditText email, password;
    TextView text1,text2;
    ClipboardManager clipboard;
    ClipData clip;
    boolean saveNewPreferences;
    Button save,submitChange,info2,info;
    private static final String tag1="save";
    private static final String tag2="info";
    private static final String tag11="guruji";
    private static final String tag12="remote";
    private static final String tag13="destructive";
    private static final String tag14="sms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        saveNewPreferences = false;
       // LayoutInflater sms = LayoutInflater.from(SmsDrishyam.this);
        //View smsView = sms.inflate(R.layout.register,null);
        //oldPassword = (EditText) smsView.findViewById(R.id.oldPassword);
        //newPassword = (EditText) smsView.findViewById(R.id.new2Password);
        //        submitChange = (Button) smsView.findViewById(R.id.submitChange);
//        info2 = (Button) smsView.findViewById(R.id.btnInfo);
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
        iconSortName13.setImageResource(R.drawable.sensor1);

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

        button1.setTag(tag13);
        button2.setTag(tag12);
        button3.setTag(tag11);
        button4.setTag(tag14);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
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
                    Toast.makeText(this, "Password Changed", Toast.LENGTH_LONG).show();
                }
                else  Toast.makeText(this, "Password Does not match", Toast.LENGTH_LONG).show();
            } else {
                if (Utility.validate(emailCheck)) {
                    if (Utility.isNotNull(passwordCheck)) {
                        SharedPreferences saveEmailnPassword = getPreferences(MODE_PRIVATE);
                        saveEmailnPassword.edit().putString("smsEmail", emailCheck).commit();
                        saveEmailnPassword.edit().putString("smsPassword", passwordCheck).commit();
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
            infos+="\r\nExample: Send 0123 getContact <contactName> to 0922828604 \r\n";
            infos+="\r\nList of commands: \r\n1)ringIt\r\n2)soundMode\r\n3)vibrateMode\r\n4)getLocation\r\n5)getContact";
            infos+="\r\n\tNOTE: commands are caseSensiTive\n";
            showInfo(infos);
        }

        if (v.getTag().equals(tag11)){
            Intent homeIntent = new Intent(getApplicationContext(),MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag12)){
            Intent homeIntent = new Intent(getApplicationContext(),Remote.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag13)){
            Intent homeIntent = new Intent(getApplicationContext(),Destructive.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (v.getTag().equals(tag14)){
            Intent homeIntent = new Intent(getApplicationContext(),bluetoothMainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
     }
    public void showInfo(String infos){
        AlertDialog alertDialog = new AlertDialog.Builder(SmsDrishyam.this,R.style.DialogSlideAnim).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage(infos);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

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
}