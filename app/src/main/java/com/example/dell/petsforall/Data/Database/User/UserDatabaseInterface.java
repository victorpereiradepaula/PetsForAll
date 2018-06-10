package com.example.dell.petsforall.Data.Database.User;

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
    void add(final Pet pet, final User user) throws Exception;
}
