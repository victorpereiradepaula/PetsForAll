package com.example.dell.petsforall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPet extends AppCompatActivity {
    private EditText nameEditText, ageEditText, timeUnitEditText, genderEditText, raceEditText, descriptionEditText;
    private Spinner speciesSpinner;
    private Button addPetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);


        addPetButton = findViewById(R.id.addPetButton);
        speciesSpinner = findViewById(R.id.addSpeciesSpinner);

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String species = speciesSpinner.getSelectedItem().toString();
                if (species.isEmpty()) {
                    Toast.makeText(AddPet.this, "Informe a espécie!", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });
    }
}
