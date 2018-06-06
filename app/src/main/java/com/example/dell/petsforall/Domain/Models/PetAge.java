package com.example.dell.petsforall.Domain.Models;

import com.example.dell.petsforall.Data.Entity.AgeUnit;
import com.example.dell.petsforall.Data.Entity.RealmAge;

/**
 * Created by renanbenattidias on 01/06/18.
 */

public class PetAge {
    public Integer age;
    public AgeUnit ageUnit;

    public PetAge(Integer age, AgeUnit ageUnit) {
        this.age = age;
        this.ageUnit = ageUnit;
    }

    public PetAge(RealmAge realmAge) {
        this.age = realmAge.age;
        this.ageUnit = AgeUnit.valueOf(realmAge.ageUnit);
    }
}
