package com.example.dell.petsforall.Domain.Models;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class Animal {

    Long id;
    String name;
    Integer age;

    public Animal(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}