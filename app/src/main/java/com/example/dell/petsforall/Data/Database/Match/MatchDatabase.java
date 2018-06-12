package com.example.dell.petsforall.Data.Database.Match;

import com.example.dell.petsforall.Data.Database.DatabaseInterface;
import com.example.dell.petsforall.Data.Entity.RealmMatch;
import com.example.dell.petsforall.Data.Entity.RealmPet;
import com.example.dell.petsforall.Data.Entity.RealmUser;
import com.example.dell.petsforall.Domain.Models.Match;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by renanbenattidias on 07/06/18.
 */

public class MatchDatabase implements MatchDatabaseInterface {

    public static final MatchDatabaseInterface shared = new MatchDatabase();

    private MatchDatabase() {}

    private void throwErrorWith(String message) {
        throw new RuntimeException(message);
    }

    @Override
    public boolean create(Match match) throws Exception {
        Realm realm = Realm.getDefaultInstance();

        Pet pet = match.pet;
        User user = match.user;

        if(pet == null || pet.id == null)
            throwErrorWith("Pet or pet id is null");

        if(user == null || user.id == null)
            throwErrorWith("User or user id is null");

        RealmPet realmPet = realm.where(RealmPet.class).equalTo("id", pet.id).findFirst();

        if(realmPet == null)
            throwErrorWith("Did not find Pet with id: " + pet.id);

        RealmUser realmUser = realm.where(RealmUser.class).equalTo("id", user.id).findFirst();

        if(realmUser == null)
            throwErrorWith("Did not find User with id: " + user.id);

        realm.beginTransaction();
            Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            RealmMatch realmMatch = realm.createObject(RealmMatch.class, id);

            realmMatch.realmPet = realmPet;
            realmMatch.realmUser = realmUser;

        realm.commitTransaction();
        realm.close();

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmMatch realmMatch = realm.where(RealmMatch.class).equalTo("id", id).findFirst();

        if(realmMatch == null) {
            realm.close();
            return false;
        }

        realm.beginTransaction();
            realmMatch.deleteFromRealm();
        realm.commitTransaction();

        realm.close();
        return true;
    }

    @Override
    public List<Match> list() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<RealmMatch> realmResults = realm.where(RealmMatch.class).findAll();

        List<Match> matches = new ArrayList<>();

        for(RealmMatch realmMatch: realmResults)
            matches.add(new Match(realmMatch));

        realm.close();
        return matches;
    }

    @Override
    public Match findBy(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmMatch realmMatch = realm.where(RealmMatch.class).equalTo("id", id).findFirst();

        if(realmMatch == null) {
            realm.close();
            return null;
        }

        Match match = new Match(realmMatch);
        return match;
    }
}
