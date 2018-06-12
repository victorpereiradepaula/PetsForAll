package com.example.dell.petsforall.Data.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by renanbenattidias on 07/06/18.
 */

public class RealmMatch extends RealmObject {

    @PrimaryKey
    public Long id;
    public RealmPet realmPet;
    public RealmUser realmUser;

}
