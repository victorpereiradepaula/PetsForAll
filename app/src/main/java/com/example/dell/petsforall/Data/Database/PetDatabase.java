package com.example.dell.petsforall.Data.Database;

import com.example.dell.petsforall.Data.Entity.RealmPet;
import com.example.dell.petsforall.Data.Entity.RealmUser;
import com.example.dell.petsforall.Domain.Models.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by renanbenattidias on 04/06/18.
 */

interface PetDatabaseInterface {
    boolean create(Pet pet);
    boolean delete(Long id);
    List<Pet> list();
    void update(Pet pet) throws Exception;
    Pet findBy(Long id);
}

public class PetDatabase implements PetDatabaseInterface {

    public static PetDatabaseInterface shared = new PetDatabase();

    private PetDatabase() {}

    @Override
    public boolean create(Pet pet) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        RealmPet realmPet = realm.createObject(RealmPet.class, id);

        // TODO make a semi-constructor method later...
        realmPet.name = pet.name;
        realmPet.description = pet.description;
        realmPet.gender = pet.gender;
        realmPet.species = pet.species;
        realmPet.breed = pet.breed;
        realmPet.age = pet.age;

        realm.commitTransaction();
        realm.close();

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmPet realmPet = realm.where(RealmPet.class).findFirst();

        if(realmPet == null) {
            realm.close();
            return false;
        }

        realm.beginTransaction();
        realmPet.deleteFromRealm();
        realm.commitTransaction();

        realm.close();
        return true;
    }

    @Override
    public List<Pet> list() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<RealmPet> realmResults = realm.where(RealmPet.class).findAll();

        ArrayList<Pet> pets = new ArrayList<>();
        for(RealmPet realmPet: realmResults)
            pets.add(new Pet(realmPet));

        realm.close();
        return pets;
    }

    @Override
    public void update(Pet pet) throws Exception {
        if(pet == null)
            throw new Exception("Pet is null");
        else if(pet.id == null)
            throw new Exception("Pet id is null");

        Realm realm = Realm.getDefaultInstance();

        RealmPet realmPet = realm.where(RealmPet.class).equalTo("id", pet.id).findFirst();
        if(realmPet == null) {
            realm.close();
            throw new Exception("Did not find pet with id: " + pet.id);
        }

        realm.beginTransaction();

        realmPet.name = pet.name;
        realmPet.age = pet.age;
        realmPet.breed = pet.breed;
        realmPet.description = pet.description;
        realmPet.gender = pet.gender;
        realmPet.species = pet.species;

        realm.commitTransaction();
    }

    @Override
    public Pet findBy(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmPet realmPet = realm.where(RealmPet.class).equalTo("id", id).findFirst();

        if(realmPet == null) {
            realm.close();
            return null;
        }

        Pet pet = new Pet(realmPet);

        realm.close();
        return pet;
    }
}