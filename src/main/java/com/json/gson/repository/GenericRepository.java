package com.json.gson.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> getAll();

    T getById(ID id);

    T create(T t);

    T update(T t);

    void deleteById(Integer id);
}
