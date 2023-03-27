package com.bekrenov.service;

import com.bekrenov.dao.LendDAO;
import com.bekrenov.dao.LendDAOImpl;
import com.bekrenov.entity.Lend;

import java.util.List;

public class LendServiceImpl implements LendService{

    private LendDAO lendDAO;

    public LendServiceImpl() {
        lendDAO = new LendDAOImpl();
    }

    @Override
    public void save(Lend lend) {
        lendDAO.save(lend);
    }

    @Override
    public List<Lend> findAll() {
        return lendDAO.findAll();
    }

    @Override
    public Lend findById(int id) {
        return lendDAO.findById(id);
    }

    @Override
    public List<Lend> findByPattern(String searchPattern) {
        return lendDAO.findByPattern(searchPattern);
    }

    @Override
    public void deleteById(int id) {
        lendDAO.deleteById(id);
    }

    @Override
    public String toString() {
        return "LendServiceImpl";
    }
}
