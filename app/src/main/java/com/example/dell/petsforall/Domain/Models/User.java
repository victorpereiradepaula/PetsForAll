package com.example.dell.petsforall.Domain.Models;

import com.example.dell.petsforall.Data.Entity.RealmUser;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class User {

    public Long id;
    public String name;

    public User(String name) {
        this.name = name;
    }

    public User(RealmUser user) {
        this.name = user.name;
        this.id = user.id;
    }
}