package com.bekrenov.panels;

import com.bekrenov.BookLenderFrame;
import com.bekrenov.entity.Lend;
import com.bekrenov.service.LendService;
import com.bekrenov.service.LendServiceImpl;
import com.bekrenov.util.ImageScaler;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LendsListPanel extends JPanel implements ActionListener {

    //TODO: make JList refresh on confirmButton and on LendPanel.saveButton
    //TODO: add sorting

    private JList<String> lendsJList;
    private JLabel allLendsLabel;
    private JLabel searchResultLabel;
    private JLabel noMatchesFoundLabel;
    private JButton backButton;
    private JButton refreshButton;
    private JButton searchButton;
    private JTextField searchTextField;
    private List<Lend> lends;
    private JScrollPane scrollPane;
    private BookLenderFrame owner;
    private LendPanel lendPanel;
    
    private LendService lendService;

    public LendsListPanel(BookLenderFrame owner) {
        this.owner = owner;

        lendService = new LendServiceImpl();
        
        allLendsLabel = new JLabel("All lends: ");
        allLendsLabel.setBounds(30, 30, 150, 50);
        allLendsLabel.setFont(new Font("Calibri", Font.PLAIN, 35));

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(20, 730, 90, 30);
        refreshButton.setFont(new Font("Calibri", Font.PLAIN, 17));
        refreshButton.setFocusable(false);
        refreshButton.addActionListener(this);
        refreshButton.addActionListener(owner);

        lends = lendService.findAll();
        lendsJList = fillJList(lends);
        //        lendsJList.setBounds(30, 80, 264, 550);
        lendsJList.setBackground(new Color(0xFCF8B0));
        lendsJList.setFont(new Font("Calibri", Font.PLAIN, 20));
        lendsJList.setFixedCellHeight(60);
        lendsJList.addListSelectionListener(owner);

        scrollPane = new JScrollPane(lendsJList);
        scrollPane.setBounds(30, 80, 264, 550);

        backButton = new JButton("Back");
        backButton.setBounds(30, 590, 70, 30);
        backButton.setFont(new Font("Calibri", Font.PLAIN, 17));
        backButton.setFocusable(false);
        backButton.setVisible(false);
        backButton.addActionListener(this);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(20, 640, 150, 50);
        searchLabel.setFont(new Font("Calibri", Font.PLAIN, 25));

        searchTextField = new JTextField();
        searchTextField.setBounds(20, 680, 200, 30);
        searchTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
        searchTextField.setBackground(new Color(0xffffff));
        searchTextField.setEditable(true);
        searchTextField.addActionListener(this);

        searchButton = new JButton();
        ImageIcon loupeIcon = ImageScaler.scale("src/main/resources/icons/loupe.png", 20, 20);
        searchButton.setIcon(loupeIcon);
        searchButton.setBounds(230, 680, 50, 30);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);


        this.setBounds(0, 0, 300, 820);
        this.setBackground(new Color(0xFCF8B0));
        this.setLayout(null);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.gray));
        this.add(allLendsLabel);
        this.add(refreshButton);
//        this.add(lendsJList);
        this.add(scrollPane);
        this.add(searchLabel);
        this.add(searchTextField);
        this.add(searchButton);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == searchButton || e.getSource() == searchTextField){
            displaySearchResult();
        }

        if(e.getSource() == backButton){
            this.remove(searchResultLabel);
            this.revalidate();
            this.repaint();
            allLendsLabel.setVisible(true);
            scrollPane.setViewportView(lendsJList);
            scrollPane.setBounds(30, 80, 264, 550);
            backButton.setVisible(false);
            searchTextField.setText("");
        }
    }

    private void displaySearchResult(){
        allLendsLabel.setVisible(false);

        searchResultLabel = new JLabel("Search results:");
        searchResultLabel.setBounds(30, 40, 250, 50);
        searchResultLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
        this.add(searchResultLabel);

        backButton.setVisible(true);

        String searchPattern = searchTextField.getText();
        List<Lend> searchResult = lendService.findByPattern(searchPattern);
        if(!searchResult.isEmpty()){
            JList<String> searchResultJList = fillJList(searchResult);
            searchResultJList.addListSelectionListener((listSelectionEvent) -> {
                owner.getCurrentlyVisiblePanel().setVisible(false);
                int lendIndex = searchResultJList.getSelectedIndex();
                lendPanel = new LendPanel(searchResult.get(lendIndex), owner);
                lendPanel.getBackToMenuButton().addActionListener(owner);
                lendPanel.getSaveButton().addActionListener(owner);
                owner.add(lendPanel);
                owner.setCurrentlyVisiblePanel(lendPanel);
                owner.revalidate();
                owner.repaint();
            });
            searchResultJList.setBackground(new Color(0xFCF8B0));
            searchResultJList.setFont(new Font("Calibri", Font.PLAIN, 20));
            searchResultJList.setFixedCellHeight(60);

            scrollPane.setViewportView(searchResultJList);
        } else{
            noMatchesFoundLabel = new JLabel("No matches found.");
            noMatchesFoundLabel.setBackground(new Color(0xFCF8B0));
            noMatchesFoundLabel.setOpaque(true);
            noMatchesFoundLabel.setHorizontalAlignment(JLabel.LEFT);
            noMatchesFoundLabel.setVerticalAlignment(JLabel.TOP);
            noMatchesFoundLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
            scrollPane.setViewportView(noMatchesFoundLabel);
        }
        scrollPane.setBounds(30, 80, 264, 500);
    }

    public void refreshList() {
        this.remove(lendsJList);
        lends = lendService.findAll();
        lendsJList = fillJList(lends);
//        lendsJList.setBounds(30, 80, 264, 550);
        lendsJList.setBackground(new Color(0xFCF8B0));
        lendsJList.setFont(new Font("Calibri", Font.PLAIN, 20));
        lendsJList.setFixedCellHeight(60);
        lendsJList.addListSelectionListener(owner);
//        scrollPane = new JScrollPane(lendsJList);
        scrollPane.setViewportView(lendsJList);
//        this.add(scrollPane);
//        this.revalidate();
//        this.repaint();
    }


    private JList<String> fillJList(List<Lend> list) {
        JList<String> resultJList = new JList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            arrayList.add(list.get(i).toString());
        }
        String[] arr = new String[arrayList.size()];
        arr = arrayList.toArray(arr);
        resultJList.setListData(arr);
        return resultJList;
    }

   /*private ArrayList<Lend> search(ArrayList<Lend> list, String pattern){
        ArrayList<Lend> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            String str = list.get(i).toString();
            for(int j = 0; j <= str.length() - pattern.length(); j++){
                String substring = str.substring(j, j + pattern.length());
                if(pattern.equalsIgnoreCase(substring)){
                    result.add(list.get(i));
                    break;
                }
            }
        }
        return result;
    }*/

    public JButton getRefreshButton(){
        return refreshButton;
    }

    public JList<String> getLendsJList() {
        return lendsJList;
    }

    public List<Lend> getLends() {
        return lends;
    }
}


