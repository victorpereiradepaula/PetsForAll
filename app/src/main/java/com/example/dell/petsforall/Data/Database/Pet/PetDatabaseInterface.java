package com.example.dell.petsforall.Data.Database.Pet;

import com.example.dell.petsforall.Domain.Models.Pet;

import java.util.List;

/**
 * Created by renanbenattidias on 07/06/18.
 */

public interface PetDatabaseInterface {
    boolean create(Pet pet);
    boolean delete(Long id);
    List<Pet> list();
    void update(Pet pet) throws Exception;
    Pet findBy(Long id);
}
