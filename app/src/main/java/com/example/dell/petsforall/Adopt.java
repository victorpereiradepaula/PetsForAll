package com.example.dell.petsforall;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.petsforall.Domain.Models.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Adopt extends AppCompatActivity {

    Handler handler = new Handler();
    ImageView petImageView;
    TextView petNameTextView;
    TextView petAgeTextView;
    TextView descriptionTextView;
    TextView petGenderTextView;
    Button adoptButton;

    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt);
        findViews();

        pet = (Pet) getIntent().getSerializableExtra("pet");

        setUpOnClickListeners();
        populate();
    }

    private void findViews() {
        petImageView = findViewById(R.id.petImageView);
        petNameTextView = findViewById(R.id.petNameTextView);
        petAgeTextView = findViewById(R.id.detailsPetAgeTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        petGenderTextView = findViewById(R.id.petGenderTextView);
        adoptButton = findViewById(R.id.adoptButton);

    }

    private void populate() {
        petNameTextView.setText(pet.name);
        petAgeTextView.setText(pet.age.age.toString());
        descriptionTextView.setText(pet.description);
        petGenderTextView.setText(pet.gender == Gender.M ? "Macho" : "Fêmea");

        new Thread() {
            public void run() {
                try {
                    URL url = new URL("http://thecatapi.com/api/images/get?format=src&results_per_page=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    InputStream inputStream = connection.getInputStream();
                    final Bitmap imagem = BitmapFactory.decodeStream(inputStream);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            petImageView.setImageBitmap(imagem);
                        }
                    });
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setUpOnClickListeners() {

    }
}
