package com.bekrenov.panels;

import com.bekrenov.BookLenderFrame;

import javax.swing.*;
import java.awt.*;

public class LendAddedPanel extends AbstractBookLenderPanel {

    private JButton newLendButton;

    public LendAddedPanel(BookLenderFrame owner) {

        JLabel lendAddedLabel = new JLabel("A book was successfully added");
        lendAddedLabel.setBounds(60, 200, 700, 300);
        lendAddedLabel.setHorizontalTextPosition(JLabel.CENTER);
        lendAddedLabel.setVerticalTextPosition(JLabel.BOTTOM);
        lendAddedLabel.setFont(new Font("Calibri", Font.PLAIN, 45));
        lendAddedLabel.setIcon(new ImageIcon("src/main/resources/icons/checkmark.png"));
        lendAddedLabel.setIconTextGap(50);

        newLendButton = new JButton();
        newLendButton.setText("Add another lend");
        newLendButton.setBounds(230, 550, 250, 150);
        newLendButton.setFont(new Font("Calibri", Font.PLAIN, 27));
        newLendButton.setFocusable(false);
        newLendButton.addActionListener(owner);

        this.setVisible(false);
        this.setBackground(new Color(0xFCF8B0));
        this.setBounds(300, 0, 700, 820);
        this.setLayout(null);
        this.add(lendAddedLabel);
        this.add(newLendButton);
    }

    public JButton getNewLendButton() {
        return newLendButton;
    }
}

