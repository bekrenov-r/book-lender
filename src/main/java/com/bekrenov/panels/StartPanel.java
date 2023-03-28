package com.bekrenov.panels;

import com.bekrenov.BookLenderFrame;
import com.bekrenov.util.ImageScaler;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends JPanel{



    private JButton newLendButton;

    public StartPanel(BookLenderFrame owner){

        JLabel startLabel = new JLabel("<html><center>Welcome to<br> Book Lending Manager!");
        startLabel.setBounds(80, 130, 700, 150);
        startLabel.setHorizontalTextPosition(JLabel.CENTER);
        startLabel.setVerticalTextPosition(JLabel.CENTER);
        startLabel.setFont(new Font("Calibri", Font.PLAIN, 55));


        newLendButton = new JButton();
        newLendButton.setText("Add new lend");
        newLendButton.setFont(new Font("Calibri", Font.PLAIN, 30));
        newLendButton.setBounds(200, 350, 300, 200);
        newLendButton.setIcon(ImageScaler.scale("src/main/resources/icons/plus.png", 60, 60));
        newLendButton.setHorizontalTextPosition(JLabel.CENTER);
        newLendButton.setVerticalTextPosition(JLabel.BOTTOM);
        newLendButton.setIconTextGap(30);
        newLendButton.setFocusable(false);
        newLendButton.addActionListener(owner);

        setBackground(new Color(0xFCF8B0));
        setBounds(300, 0, 700, 820);
        setLayout(null);
        add(startLabel);
        add(newLendButton);
    }

    public JButton getNewLendButton() {
        return newLendButton;
    }
}
