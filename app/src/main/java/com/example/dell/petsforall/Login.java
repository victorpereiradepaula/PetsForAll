package com.example.dell.petsforall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Database.Pet.PetDatabase;
import com.example.dell.petsforall.Data.Database.User.UserDatabase;
import com.example.dell.petsforall.Domain.Models.AgeUnit;
import com.example.dell.petsforall.Domain.Models.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.PetAge;
import com.example.dell.petsforall.Domain.Models.User;

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

        User user = new User("Renan", "renanbenattidias@gmail.com", "secret123", "21341235", 12342.0, 2343.0, null);
        UserDatabase.shared.create(user);

        Pet pet = new Pet("Garu", "asdfsadg", Gender.F, "asdfasdf", "asdfsad", new PetAge(5, AgeUnit.Years));

        user.pets.add(pet);

        try {
            UserDatabase.shared.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
