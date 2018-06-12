package com.example.dell.petsforall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.petsforall.Domain.Models.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;

public class Adopt extends AppCompatActivity {

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
    }

    private void setUpOnClickListeners() {

    }
}
