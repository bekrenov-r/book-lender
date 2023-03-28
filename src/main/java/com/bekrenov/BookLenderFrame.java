package com.bekrenov;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.bekrenov.panels.*;


public class BookLenderFrame extends JFrame implements ActionListener, ListSelectionListener {


    private LendAddedPanel lendAddedPanel;
    private StartPanel startPanel;
    private AddNewLendPanel newLendPanel;
    private LendsListPanel lendsListPanel;
    private LendPanel lendPanel = new LendPanel();
    private JPanel currentlyVisiblePanel;


    public BookLenderFrame() {
        startPanel = new StartPanel(this);
        newLendPanel = new AddNewLendPanel(this);
        lendAddedPanel = new LendAddedPanel(this);
        lendsListPanel = new LendsListPanel(this);

        ImageIcon logoIcon = new ImageIcon("src/main/resources/icons/logo.png");
        setIconImage(logoIcon.getImage());
        setSize(new Dimension(1000, 820));
        setLocation(280, 0);
        setTitle("Book Lending Manager");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(startPanel);
        setCurrentlyVisiblePanel(startPanel);
        add(newLendPanel);
        add(lendAddedPanel);
        add(lendsListPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==startPanel.getNewLendButton()){
            startPanel.setVisible(false);
            newLendPanel.setVisible(true);
            setCurrentlyVisiblePanel(newLendPanel);
        }

        if(e.getSource() == newLendPanel.getConfirmButton()){
            this.remove(newLendPanel);
            newLendPanel = new AddNewLendPanel(this);
            newLendPanel.getConfirmButton().addActionListener(this);
            this.add(newLendPanel);
            this.revalidate();
            this.repaint();
            lendAddedPanel.setVisible(true);
            setCurrentlyVisiblePanel(lendAddedPanel);
        }

        if(e.getSource() == lendAddedPanel.getNewLendButton()){
            lendAddedPanel.setVisible(false);
            newLendPanel = new AddNewLendPanel(this);
            newLendPanel.getConfirmButton().addActionListener(this);
            this.add(newLendPanel);
            newLendPanel.setVisible(true);
            setCurrentlyVisiblePanel(newLendPanel);
            this.revalidate();
            this.repaint();
        }

        if(e.getSource() == lendPanel.getBackToMenuButton()){
            this.remove(currentlyVisiblePanel);
            startPanel.setVisible(true);
            setCurrentlyVisiblePanel(startPanel);
        }

        if(e.getSource() == lendsListPanel.getRefreshButton()){
            lendsListPanel.refreshList();
        }

        if(e.getSource() == lendPanel.getSaveButton()){
            //TODO: refresh will not work without delay
            lendsListPanel.refreshList();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == lendsListPanel.getLendsJList()){
            currentlyVisiblePanel.setVisible(false);
            int lendIndex = lendsListPanel.getLendsJList().getSelectedIndex();
            lendPanel = new LendPanel(lendsListPanel.getLends().get(lendIndex), this);
            lendPanel.getBackToMenuButton().addActionListener(this);
            lendPanel.getSaveButton().addActionListener(this);
            this.add(lendPanel);
            this.setCurrentlyVisiblePanel(lendPanel);
            this.revalidate();
            this.repaint();
        }
    }

    public void setCurrentlyVisiblePanel(JPanel panel){
        this.currentlyVisiblePanel = panel;
    }

    public JPanel getCurrentlyVisiblePanel(){
        return currentlyVisiblePanel;
    }
}
