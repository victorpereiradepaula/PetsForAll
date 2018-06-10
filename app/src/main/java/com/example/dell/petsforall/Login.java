package com.example.dell.petsforall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Database.Pet.PetDatabase;
import com.example.dell.petsforall.Data.Database.User.UserDatabase;
import com.example.dell.petsforall.Domain.Models.AgeUnit;
import com.example.dell.petsforall.Domain.Models.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.PetAge;
import com.example.dell.petsforall.Domain.Models.User;

public class Login extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;
    private Button recoverPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.loginEmail);
        passwordEditText = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        recoverPasswordButton = findViewById(R.id.recoverPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString(), password = passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }
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

    }
}
