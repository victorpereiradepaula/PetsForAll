package com.example.dell.petsforall;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Realm.init(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN_TIME);
    }
}
