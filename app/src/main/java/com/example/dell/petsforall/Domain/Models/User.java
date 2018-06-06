package com.example.dell.petsforall.Domain.Models;

import com.example.dell.petsforall.Data.Entity.RealmPet;
import com.example.dell.petsforall.Data.Entity.RealmUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class User {

    public Long id;
    public String name;

    public List<Pet> pets;

    public User(String name, List<Pet> pets) {
        this.name = name;
        this.pets = pets;
    }

    public User(RealmUser user) {
        this.name = user.name;
        this.id = user.id;

        List<Pet> pets = new ArrayList<>();
        for(RealmPet realmPet: user.pets)
            pets.add(new Pet(realmPet));

        this.pets = pets;
    }
}