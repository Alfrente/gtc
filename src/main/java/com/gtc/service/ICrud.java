package com.gtc.service;

import java.util.List;

public interface ICrud<O, I> {

    O getById(Long id);

    List<O> getAll();

    O save(I i);

    void delete(Long id);

    O update(Long id, I i);
}
