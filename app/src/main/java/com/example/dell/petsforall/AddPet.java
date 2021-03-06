package com.example.dell.petsforall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Database.User.UserDatabase;
import com.example.dell.petsforall.Domain.Models.AgeUnit;
import com.example.dell.petsforall.Domain.Models.Gender;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.PetAge;
import com.example.dell.petsforall.Domain.Models.User;

public class AddPet extends AppCompatActivity {
    private EditText nameEditText, ageEditText, raceEditText, descriptionEditText;
    private Spinner speciesSpinner, timeUnitSpinner, genderSpinner;
    private Button addPetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);


        addPetButton = findViewById(R.id.addPetButton);
        speciesSpinner = findViewById(R.id.addSpeciesSpinner);

        nameEditText = findViewById(R.id.petNameEditText);
        ageEditText = findViewById(R.id.ageEditTex);
        timeUnitSpinner = findViewById(R.id.addUnitSpinner);
        genderSpinner = findViewById(R.id.addGenderSpinner);
        raceEditText = findViewById(R.id.raceEditTex);
        descriptionEditText = findViewById(R.id.descriptionEditTex);

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String species = speciesSpinner.getSelectedItem().toString();
                if (species.isEmpty()) {
                    Toast.makeText(AddPet.this, "Informe a espécie!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = nameEditText.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(AddPet.this, "Informe o nome!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ageString = ageEditText.getText().toString();
                if (ageString.isEmpty()) {
                    Toast.makeText(AddPet.this, "Informe a idade!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer age = Integer.parseInt(ageString);

                String unitString = timeUnitSpinner.getSelectedItem().toString();
                if (unitString.isEmpty()) {
                    Toast.makeText(AddPet.this, "Informe a unidade!", Toast.LENGTH_SHORT).show();
                    return;
                }

                AgeUnit unit;

                // Did but don't agree
                if(unitString.equals("Dias"))
                    unit = AgeUnit.Dias;
                else if(unitString.equals("Semanas"))
                    unit = AgeUnit.Semanas;
                else if(unitString.equals("Meses"))
                    unit = AgeUnit.Meses;
                else if(unitString.equals("Anos"))
                    unit = AgeUnit.Anos;
                else
                    unit = AgeUnit.Dias;

                String race = raceEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String gender = genderSpinner.getSelectedItem().toString();
                if (gender.isEmpty()) {
                    Toast.makeText(AddPet.this, "Informe o gênero!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Pet pet = new Pet(name, description, gender.equals("Macho") ? Gender.M : Gender.F, species, race, new PetAge(age, unit));

                User user = UserDatabase.shared.getCurrentUser(getApplicationContext());

                try {
                    UserDatabase.shared.add(pet, user);
                } catch(Exception exception) {
                    Toast.makeText(getApplicationContext(), "Erro ao criar pet", Toast.LENGTH_LONG).show();
                    exception.printStackTrace();
                }

                finish();
            }
        });
    }
}
