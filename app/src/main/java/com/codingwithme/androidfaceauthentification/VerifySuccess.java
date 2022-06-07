package com.codingwithme.androidfaceauthentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;

public class VerifySuccess extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 8000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_success);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(VerifySuccess.this,HomePage.class);
                VerifySuccess.this.startActivity(mainIntent);
                VerifySuccess.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    }
