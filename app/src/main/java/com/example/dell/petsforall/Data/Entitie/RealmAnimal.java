package com.example.dell.petsforall.Data.Entitie;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class RealmAnimal extends RealmObject {

    @PrimaryKey
    Long id;
    String name;
    Integer age;

}