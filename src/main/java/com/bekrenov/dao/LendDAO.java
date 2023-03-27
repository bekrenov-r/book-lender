package com.bekrenov.dao;

import com.bekrenov.entity.Lend;

import java.util.List;

public interface LendDAO {

    void save(Lend lend);

    List<Lend> findAll();

    Lend findById(int id);

    void deleteById(int id);

    List<Lend> findByPattern(String searchPattern);
}
