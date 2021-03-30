package com.example.motokarkotlin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3000);
                } catch (Exception e) {

                } finally {

                    Intent firstIntent = new Intent(SplashScreen.this,LoginPage.class);
                    firstIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(firstIntent);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
