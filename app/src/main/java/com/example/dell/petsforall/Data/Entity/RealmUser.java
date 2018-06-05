package com.example.dell.petsforall.Data.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class RealmUser extends RealmObject {

    @PrimaryKey
    public Long id;
    public String name;
    public RealmList<RealmPet> animals;

}
