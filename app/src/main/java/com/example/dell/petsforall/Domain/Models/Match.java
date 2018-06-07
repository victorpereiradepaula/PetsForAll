package com.example.dell.petsforall.Domain.Models;

import com.example.dell.petsforall.Data.Entity.RealmMatch;

/**
 * Created by renanbenattidias on 07/06/18.
 */

public class Match {

    public Long id;
    public Pet pet;
    public User user;

    public Match(Long id, Pet pet, User user) {
        this.id = id;
        this.pet = pet;
        this.user = user;
    }

    public Match(Pet pet, User user) {
        this.pet = pet;
        this.user = user;
    }

    public Match(RealmMatch realmMatch) {
        this.id = realmMatch.id;
        this.pet = new Pet(realmMatch.realmPet);
        this.user = new User(realmMatch.realmUser);
    }

}
