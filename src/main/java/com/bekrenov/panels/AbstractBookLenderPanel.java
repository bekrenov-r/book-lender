package com.bekrenov.panels;

import com.bekrenov.BookLenderFrame;

import javax.swing.*;

public abstract class AbstractBookLenderPanel extends JPanel {

    private BookLenderFrame owner;

    public AbstractBookLenderPanel(){

    }

    public AbstractBookLenderPanel(BookLenderFrame owner){
        this.owner = owner;
    }

}
