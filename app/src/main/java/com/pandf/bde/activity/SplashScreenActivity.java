package com.pandf.bde.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.util.TypedValue;

import com.pandf.bde.R;

/**
 * Created by pierr on 07/10/2017.
 */

public class SplashScreenActivity extends Activity {
    private static int SPLASH_OUT = 500;


    protected void onCreate(Bundle savedInstanceState) {
        Utility.themer(SplashScreenActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_OUT);
    }
}
