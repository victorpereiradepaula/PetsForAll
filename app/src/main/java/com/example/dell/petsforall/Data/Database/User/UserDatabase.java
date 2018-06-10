package com.example.dell.petsforall.Data.Database.User;

import com.example.dell.petsforall.Data.Entity.RealmAge;
import com.example.dell.petsforall.Data.Entity.RealmPet;
import com.example.dell.petsforall.Data.Entity.RealmUser;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class UserDatabase implements UserDatabaseInterface {

    private UserDatabase() {}

    public static UserDatabaseInterface shared = new UserDatabase();

    // "final" Huh....
    public void create(final User user) {

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                RealmUser realmUser = realm.createObject(RealmUser.class, id);

                realmUser.name = user.name;

                for(Pet pet: user.pets) {
                    Long petId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                    RealmPet realmPet = realm.createObject(RealmPet.class, petId);

                    // Repeating code!!! Õ-Õ
                    realmPet.name = pet.name;
                    realmPet.description = pet.description;
                    realmPet.gender = pet.gender.toString();
                    realmPet.species = pet.species;
                    realmPet.breed = pet.breed;

                    RealmAge realmAge = realm.createObject(RealmAge.class);
                    realmAge.age = pet.age.age;
                    realmAge.ageUnit = pet.age.ageUnit.toString();

                    realmPet.realmAge = realmAge;

                    realmUser.pets.add(realmPet);
                }
            }
        });
    }

    @Override
    public void add(final Pet pet, final User user) throws Exception {
        if(user == null || user.id == null)
            throw new RuntimeException("User or user id is nil");

        if(pet == null || pet.id == null)
            throw new RuntimeException("Pet or pet id is nil");

        Realm realm = Realm.getDefaultInstance();

        final RealmUser realmUser = realm.where(RealmUser.class).equalTo("id", user.id).findFirst();

        if(realmUser == null)
            throw new RuntimeException("User not found");

        realm.executeTransaction(new Realm.Transaction() {
             @Override
             public void execute(Realm realm) {
                 Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                 RealmPet realmPet = realm.createObject(RealmPet.class, id);

                 realmPet.name = pet.name;
                 realmPet.description = pet.description;
                 realmPet.gender = pet.gender.toString();
                 realmPet.species = pet.species;
                 realmPet.breed = pet.breed;

                 RealmAge realmAge = realm.createObject(RealmAge.class);
                 realmAge.age = pet.age.age;
                 realmAge.ageUnit = pet.age.ageUnit.toString();

                 realmPet.realmAge = realmAge;

                 realmUser.pets.add(realmPet);
             }
         });
    }

    @Override
    public boolean delete(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmUser realmUser = realm.where(RealmUser.class).equalTo("id", id).findFirst();

        // guard let realmUser = realmUser else { realm.close; return false } :P
        if(realmUser == null) {
            realm.close();
            return false;
        }

        realm.beginTransaction();
            realmUser.deleteFromRealm();
        realm.commitTransaction();

        realm.close();
        return true;
    }

    @Override
    public List<User> list() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<RealmUser> realmResults = realm.where(RealmUser.class).findAll();

        ArrayList<User> users = new ArrayList<>();
        for(RealmUser user: realmResults) {
            users.add(new User(user));
        }

        realm.close();
        return users;
    }

    @Override
    public void update(User user) throws Exception {
        if(user == null)
            throw new Exception("User is null");
        else if(user.id == null)
            throw new Exception("User id is null");

        Realm realm = Realm.getDefaultInstance();

        RealmUser realmUser = realm.where(RealmUser.class).equalTo("id", user.id).findFirst();

        if(realmUser == null) {
            realm.close();
            throw new Exception("Did not find user with id: " + user.id);
        }

        realm.beginTransaction();

            realmUser.name = user.name;

        realm.commitTransaction();
    }

    @Override
    public User findUserBy(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmUser realmUser = realm.where(RealmUser.class).equalTo("id", id).findFirst();

        if(realmUser == null) {
            realm.close();
            return null;
        }

        User user = new User(realmUser);

        realm.close();
        return user;
    }
}