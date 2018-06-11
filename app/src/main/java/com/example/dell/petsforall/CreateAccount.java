package com.example.dell.petsforall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Database.User.UserDatabase;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.User;

import java.util.ArrayList;
import java.util.List;

public class CreateAccount extends AppCompatActivity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmationEditText;
    private EditText phoneEditText;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        nameEditText = findViewById(R.id.createAccountName);
        emailEditText = findViewById(R.id.createAccountEmail);
        passwordEditText = findViewById(R.id.createAccountPassword);
        passwordConfirmationEditText = findViewById(R.id.createAccountPasswordConfirmation);
        phoneEditText = findViewById(R.id.createAccountPhone);

        createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString(), email = emailEditText.getText().toString(), phone = phoneEditText.getText().toString(),
                        password = passwordEditText.getText().toString(), passwordConfirmation = passwordConfirmationEditText.getText().toString();
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(CreateAccount.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(passwordConfirmation)) {
                    Toast.makeText(CreateAccount.this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(name,email,password,phone,0.0,0.0, new ArrayList<Pet>());
                UserDatabase.shared.create(user);
                Toast.makeText(CreateAccount.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateAccount.this, Home.class);
                startActivity(intent);
                Login.loginActivity.finish();
                finish();
            }
        });
    }
}
