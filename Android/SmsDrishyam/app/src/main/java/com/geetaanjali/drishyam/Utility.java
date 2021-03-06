package com.geetaanjali.drishyam;

/**
 * Created by Amon on 20/9/2558.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which has Utility methods
 *
 */
public class Utility {

    private static Pattern pattern;
    private static Matcher matcher;
    public static Boolean isPasswordSet = false;
    private static String DrishyamSmsPassword;
    public static Double latitude = 13.7934;
    public static Double longitude = 100.3225;

    //Email Pattern
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validate(String email) {
        String[] emailCheck = email.split(",");
        boolean emailValid = true;
        for (int i=0;i<emailCheck.length;i++){
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(emailCheck[i]);
            emailValid = matcher.matches();
            if(!emailValid)return false;
        }
        return true;
    }

    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
    public static boolean isValidMobile(String phone2) {

        if (phone2.length() < 6 || phone2.length() > 13)       return false;
            else  return true;

    }
    public static String setDrishyamSmsPassword(String pswd) {
        DrishyamSmsPassword = pswd;
        return "";

    }
    public static String getDrishyamSmsPassword(){
        return DrishyamSmsPassword;
    }

    public static void showInfo(Context context,String infos){
        AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.DialogSlideAnim).create();
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
}