package com.example.dell.petsforall;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dell.petsforall.Data.Database.Pet.PetDatabase;
import com.example.dell.petsforall.Data.Database.User.UserDatabase;
import com.example.dell.petsforall.Data.Entity.RealmUser;
import com.example.dell.petsforall.Domain.Models.AgeUnit;
import com.example.dell.petsforall.Domain.Models.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.PetAge;
import com.example.dell.petsforall.Domain.Models.User;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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
                Long userId = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE).getLong("current_user_id", -1);

                if(userId == -1) {
                    goToLogin();
                    return;
                }

                User user = UserDatabase.shared.findUserBy(userId);

                if(user != null) {
                    goToHome();
                    return;
                }
            }
        },SPLASH_SCREEN_TIME);
    }

    private void goToLogin() {
        Intent intent = new Intent(SplashScreen.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void goToHome() {
        Intent intent = new Intent(SplashScreen.this, Home.class);
        startActivity(intent);
        finish();
    }
}
