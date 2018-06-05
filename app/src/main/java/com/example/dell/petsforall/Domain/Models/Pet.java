package com.example.dell.petsforall.Domain.Models;

import com.example.dell.petsforall.Data.Entity.PetlAge;
import com.example.dell.petsforall.Data.Entity.Gender;
import com.example.dell.petsforall.Data.Entity.RealmPet;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class Pet {

    public Long id;
    public String name;
    public String description;
    public Gender gender;
    public String species;
    public String breed;
    public PetlAge age;

    public Pet(Long id, String name, String description, Gender gender, String species, String breed, PetlAge age) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.species = species;
        this.breed = breed;
        this.age = age;
    }

    public Pet(String name, String description, Gender gender, String species, String breed, PetlAge age) {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.species = species;
        this.breed = breed;
        this.age = age;
    }

    public Pet(RealmPet realmPet) {
        this.id = realmPet.id;
        this.name = realmPet.name;
        this.description = realmPet.description;
        this.gender = realmPet.gender;
        this.species = realmPet.species;
        this.breed = realmPet.breed;
        this.age = realmPet.age;
    }

}