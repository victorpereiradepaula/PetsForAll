package com.example.dell.petsforall.Data.Database.User;

import android.content.Context;

import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.User;

import java.util.List;

/**
 * Created by renanbenattidias on 07/06/18.
 */

public interface UserDatabaseInterface {
    void create(final User user);
    boolean delete(Long id);
    List<User> list();
    void update(User user) throws Exception;
    User findUserBy(Long id);
    User findUserBy(String email, String password);
    void add(final Pet pet, final User user) throws Exception;
    public User getCurrentUser(Context context);
}
