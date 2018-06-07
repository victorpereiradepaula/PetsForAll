package com.example.dell.petsforall.Data.Database;

import java.util.List;

/**
 * Created by renanbenattidias on 07/06/18.
 */

public interface DatabaseInterface<E> {
    boolean create(E e) throws Exception;
    boolean delete(Long id);
    List<E> list();
    E findBy(Long id);
}
