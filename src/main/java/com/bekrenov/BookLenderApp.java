package com.bekrenov;

import com.bekrenov.dao.LendDAO;
import com.bekrenov.dao.LendDAOImpl;
import com.bekrenov.entity.Lend;

import java.sql.SQLException;

public class BookLenderApp {

    public static void main(String[] args) throws SQLException {

        new BookLenderFrame();
    }
}
