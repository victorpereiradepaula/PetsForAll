package com.example.dell.petsforall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Entity.AgeUnit;
import com.example.dell.petsforall.Data.Entity.PetlAge;
import com.example.dell.petsforall.Data.Entity.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;

public class Login extends AppCompatActivity {
    private Button loginButton;
    private Button createAccountButton;
    private Button recoverPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        recoverPasswordButton = findViewById(R.id.recoverPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Em breve!", Toast.LENGTH_LONG).show();
            }
        });

        Pet pet = new Pet("Dog", "A dog", Gender.M, "Dogus dog", "Dogga", new PetlAge(12, AgeUnit.Days));


    }
}
