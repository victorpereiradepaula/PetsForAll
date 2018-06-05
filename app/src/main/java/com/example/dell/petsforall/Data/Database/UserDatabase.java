package com.example.dell.petsforall.Data.Database;

import com.example.dell.petsforall.Data.Entity.RealmUser;
import com.example.dell.petsforall.Domain.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by renanbenattidias on 01/06/18.
 */

interface UserDatabaseInterface {
    boolean create(User user);
    boolean delete(Long id);
    List<User> list();
    void update(User user) throws Exception;
    User findUserBy(Long id);
}

public class UserDatabase implements UserDatabaseInterface {

    private UserDatabase() {}

    public static UserDatabaseInterface shared = new UserDatabase();

    @Override
    public boolean create(User user) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

            //         TODO Change later
            Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            RealmUser realmUser = realm.createObject(RealmUser.class, id);

            realmUser.name = user.name;

        realm.commitTransaction();

        realm.close();

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Realm realm = Realm.getDefaultInstance();

        RealmUser realmUser = realm.where(RealmUser.class).findFirst();

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