package com.bekrenov.service;

import com.bekrenov.entity.Lend;

import java.util.List;

public interface LendService {

    void save(Lend lend);

    List<Lend> findAll();

    Lend findById(int id);

    List<Lend> findByPattern(String searchPattern);

    void deleteById(int id);

}
