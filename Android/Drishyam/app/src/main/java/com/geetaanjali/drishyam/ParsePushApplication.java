package com.geetaanjali.drishyam;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Amon on 3/10/2558.
 */
public class ParsePushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getString(R.string.appID), getString(R.string.clientID));
        ParseInstallation.getCurrentInstallation().saveInBackground();
//        ParseUser.enableAutomaticUser();
//        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
