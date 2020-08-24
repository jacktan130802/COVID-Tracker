package com.sp.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    int result;
    TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);


        ts = new TextToSpeech(Splash.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                String text = "Time to make the crack";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    ts.speak(text, TextToSpeech.QUEUE_FLUSH, null,null);
                }

            }
        });



            Timer timer = new Timer();
        timer.schedule(new TimerTask(){

                           @Override
                           public void run() {
                               Intent intent = new Intent(Splash.this, MainActivity.class
                               );
                               startActivity(intent);
                           }
                       }



                ,3000);

    }
}
