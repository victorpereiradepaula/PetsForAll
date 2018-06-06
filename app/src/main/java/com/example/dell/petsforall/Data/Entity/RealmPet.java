package com.example.dell.petsforall.Data.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmPet extends RealmObject {

    @PrimaryKey
    public Long id;
    public String name;
    public String description;
    public String gender;
    public String species;
    public String breed;
    public RealmAge realmAge;

//    RealmUser owner;
}