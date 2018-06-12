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
    public String email;
    public String phone;
    public String password;
    public Double latitude;
    public Double longitude;

    public List<Pet> pets;

    public User(String name, String email, String password, String phone, Double latitude, Double longitude, List<Pet> pets) {
        this.name = name;
        this.pets = pets;
        this.email= email;
        this.phone = phone;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public User(RealmUser user) {
        this.name = user.name;
        this.id = user.id;
        this.email= user.email;
        this.password = user.password;
        this.phone = user.phone;
        this.latitude = user.latitude;
        this.longitude = user.longitude;

        List<Pet> pets = new ArrayList<>();
        for(RealmPet realmPet: user.pets)
            pets.add(new Pet(realmPet));

        this.pets = pets;
    }
}