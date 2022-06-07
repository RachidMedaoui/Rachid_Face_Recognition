package com.codingwithme.androidfaceauthentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Secreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 8000;
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_secreen_load_info);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("");

        MediaPlayer ring= MediaPlayer.create(Splash_Secreen.this,R.raw.scannerface);
        ring.start();


        final long period = 100;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(i)+"%");
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(Splash_Secreen.this,VerifySuccess.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);
    }
    }

