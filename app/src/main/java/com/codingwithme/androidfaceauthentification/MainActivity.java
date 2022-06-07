package com.codingwithme.androidfaceauthentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codingwithme.androidfaceauthentification.R;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    LinearLayout face_scanner , acceses_denied;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        face_scanner=findViewById(R.id.facescann);
        acceses_denied = findViewById(R.id.accese_denied);

        face_scanner.setVisibility(View.INVISIBLE);
        acceses_denied.setVisibility(View.INVISIBLE);


        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.facedetection);
        ring.start();

        btnLogin = findViewById(R.id.btnLogin);

        final BiometricManager biometricManager = BiometricManager.from(this);

        switch (biometricManager.canAuthenticate()){

            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this,"You can use your face to login",Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this,"No face sensor",Toast.LENGTH_LONG).show();

                btnLogin.setVisibility(View.INVISIBLE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this,"Biometric sensor is not available",Toast.LENGTH_LONG).show();
                btnLogin.setVisibility(View.INVISIBLE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(this,"Your device don't have any face, check your security setting",Toast.LENGTH_LONG).show();
                    btnLogin.setVisibility(View.INVISIBLE);
                    break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);


        final BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this,executor,new BiometricPrompt.AuthenticationCallback(){

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                face_scanner.setVisibility(View.INVISIBLE);
                acceses_denied.setVisibility(View.VISIBLE);

                MediaPlayer ring2 = MediaPlayer.create(MainActivity.this,R.raw.worng);
                ring2.start();
                
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Splash_Secreen.class);
                startActivity(intent);
                finish();


            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                acceses_denied.setVisibility(View.VISIBLE);
                face_scanner.setVisibility(View.INVISIBLE);
                MediaPlayer ring3 = MediaPlayer.create(MainActivity.this,R.raw.worng);
                ring3.start();



            }
        });

        final BiometricPrompt.PromptInfo  promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("User face  to login")
                .setNegativeButtonText("cancel")
                .build();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                face_scanner.setVisibility(View.VISIBLE);
                acceses_denied.setVisibility(View.INVISIBLE);
                MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.welcome);
                ring.start();
                biometricPrompt.authenticate(promptInfo);


            }
        });


    }
}
