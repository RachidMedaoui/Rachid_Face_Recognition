package com.codingwithme.androidfaceauthentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        btnlogout =findViewById(R.id.btnLoout);
        

        MediaPlayer ring= MediaPlayer.create(HomePage.this,R.raw.welcomesystem);
        ring.start();
       
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(HomePage.this,MainActivity.class);
                HomePage.this.startActivity(mainIntent);
                HomePage.this.finish();
                
            }
        });

    }
}