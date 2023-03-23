package com.gtc.service;

import java.util.List;

public interface ICrud<O, I> {

    O getById(String id);

    List<O> getAll();

    O save(I i);

    void delete(String id);


}
