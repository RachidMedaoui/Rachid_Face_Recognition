package com.codingwithme.androidfaceauthentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Acceses_denied extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceses_denied);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Acceses_denied.this,MainActivity.class);
                Acceses_denied.this.startActivity(mainIntent);
                Acceses_denied.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }
