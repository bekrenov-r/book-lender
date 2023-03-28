package com.bekrenov.panels;

import com.bekrenov.BookLenderFrame;
import com.bekrenov.entity.Lend;
import com.bekrenov.service.LendService;
import com.bekrenov.service.LendServiceImpl;
import com.bekrenov.util.DateConverter;
import com.bekrenov.util.DateLabelFormatter;
import com.bekrenov.util.FileManager;
import com.bekrenov.util.ImageScaler;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

public class AddNewLendPanel extends AbstractBookLenderPanel implements ActionListener {

    //TODO: Make validation
    //TODO: Make icon of book autoresizable
    private String iconPath;
    private JButton confirmButton;
    private JButton setIconButton;
    private JButton changeIconButton;

    private JTextField nameTextField;
    private JTextField titleTextField;
    private JTextField publishingYearTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField surnameTextField;
    private JTextField authorTextField;

    private JDatePickerImpl dateOfLendPicker;
    private JDatePickerImpl dateOfReturnPicker;

    private JLabel imageLabel;
    private BookLenderFrame owner;

    private LendService lendService;
    // todo: make other way to get path
    private final String BOOK_IMG_DIR = "src/main/resources/book-images/";

    public AddNewLendPanel(BookLenderFrame owner) {
        super(owner);
        this.owner = owner;

        lendService = new LendServiceImpl();

        JLabel bookInformationLabel = new JLabel("Information about a book: ");
        bookInformationLabel.setBounds(40, 10, 500, 100);
        bookInformationLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        bookInformationLabel.setHorizontalTextPosition(JLabel.CENTER);
        bookInformationLabel.setVerticalTextPosition(JLabel.CENTER);

        setIconButton = new JButton();
        setIconButton.setText("Set icon");
        setIconButton.setFont(new Font("Calibri", Font.PLAIN, 30));
        setIconButton.setBounds(40, 100, 200, 300);
        ImageIcon plusIcon = ImageScaler.scale("src/main/resources/icons/plus.png", 70, 70);
        setIconButton.setHorizontalTextPosition(SwingConstants.CENTER);
        setIconButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        setIconButton.setIcon(plusIcon);
        setIconButton.setIconTextGap(40);
        setIconButton.setFocusable(false);
        setIconButton.addActionListener(this);

        titleTextField = new JTextField();
        titleTextField.setText(" Input title");
        titleTextField.setBounds(260, 100, 350, 35);
        titleTextField.setForeground(new Color(0x9B9B9B));
        titleTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        titleTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(titleTextField.getText().equals(" Input title")){
                    titleTextField.setText("");
                }
                titleTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                titleTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(titleTextField.getText().equals("")){
                    titleTextField.setText(" Input title");
                    titleTextField.setForeground(new Color(0x9B9B9B));
                    titleTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }

            }
        });

        authorTextField = new JTextField();
        authorTextField.setText(" Input author");
        authorTextField.setBounds(260, 150, 350, 35);
        authorTextField.setForeground(new Color(0x9B9B9B));
        authorTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        authorTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(authorTextField.getText().equals(" Input author")){
                    authorTextField.setText("");
                }
                authorTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                authorTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(authorTextField.getText().equals("")){
                    authorTextField.setText(" Input author");
                    authorTextField.setForeground(new Color(0x9B9B9B));
                    authorTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }
            }
        });

        publishingYearTextField = new JTextField();
        publishingYearTextField.setText(" Input year of publishing");
        publishingYearTextField.setBounds(260, 200, 350, 35);
        publishingYearTextField.setForeground(new Color(0x9B9B9B));
        publishingYearTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        publishingYearTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(publishingYearTextField.getText().equals(" Input year of publishing")){
                    publishingYearTextField.setText("");
                }
                publishingYearTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                publishingYearTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(publishingYearTextField.getText().equals("")){
                    publishingYearTextField.setText(" Input year of publishing");
                    publishingYearTextField.setForeground(new Color(0x9B9B9B));
                    publishingYearTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }
            }
        });

        JLabel dateOfLendLabel = new JLabel("Date of lend: ");
        dateOfLendLabel.setBounds(260, 255, 150, 35);
        dateOfLendLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        dateOfLendLabel.setHorizontalTextPosition(JLabel.CENTER);
        dateOfLendLabel.setVerticalTextPosition(JLabel.CENTER);

        UtilDateModel dateOfLendModel = new UtilDateModel();
        dateOfLendModel.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth());
        dateOfLendModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dateOfLendPanel = new JDatePanelImpl(dateOfLendModel, p);
        dateOfLendPicker = new JDatePickerImpl(dateOfLendPanel, new DateLabelFormatter());
        dateOfLendPicker.setBounds(400, 255, 150, 35);
        dateOfLendPicker.setBackground(new Color(0xFCF8B0));
        dateOfLendPicker.setFont(new Font("Calibri", Font.PLAIN, 20));
        dateOfLendPicker.setButtonFocusable(false);
        dateOfLendPicker.setTextEditable(true);


        JLabel dateOfReturnLabel = new JLabel("Planned date of return: ");
        dateOfReturnLabel.setBounds(260, 310, 250, 35);
        dateOfReturnLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        dateOfReturnLabel.setHorizontalTextPosition(JLabel.CENTER);
        dateOfReturnLabel.setVerticalTextPosition(JLabel.CENTER);

        UtilDateModel dateOfReturnModel = new UtilDateModel();
        JDatePanelImpl dateOfReturnPanel = new JDatePanelImpl(dateOfReturnModel, p);
        dateOfReturnPicker = new JDatePickerImpl(dateOfReturnPanel, new DateLabelFormatter());
        dateOfReturnPicker.setBounds(490, 310, 150, 35);
        dateOfReturnPicker.setBackground(new Color(0xFCF8B0));
        dateOfReturnPicker.setFont(new Font("Calibri", Font.PLAIN, 20));
        dateOfReturnPicker.setButtonFocusable(false);
        dateOfReturnPicker.setTextEditable(true);
        //TODO: add radio button for option no exact date of return

        JLabel personDataLabel = new JLabel("A person borrowing: ");
        personDataLabel.setBounds(40, 450, 500, 100);
        personDataLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        personDataLabel.setHorizontalTextPosition(JLabel.CENTER);
        personDataLabel.setVerticalTextPosition(JLabel.CENTER);

        nameTextField = new JTextField();
        nameTextField.setText(" Input name");
        nameTextField.setBounds(40, 540, 270, 35);
        nameTextField.setForeground(new Color(0x9B9B9B));
        nameTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(nameTextField.getText().equals(" Input name")){
                    nameTextField.setText("");
                }
                nameTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                nameTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(nameTextField.getText().equals("")){
                    nameTextField.setText(" Input name");
                    nameTextField.setForeground(new Color(0x9B9B9B));
                    nameTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }
            }
        });

        surnameTextField = new JTextField();
        surnameTextField.setText(" Input surname");
        surnameTextField.setBounds(40, 605, 270, 35);
        surnameTextField.setForeground(new Color(0x9B9B9B));
        surnameTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        surnameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(surnameTextField.getText().equals(" Input surname")){
                    surnameTextField.setText("");
                }
                surnameTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                surnameTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(surnameTextField.getText().equals("")){
                    surnameTextField.setText(" Input surname");
                    surnameTextField.setForeground(new Color(0x9B9B9B));
                    surnameTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }
            }
        });

        emailTextField = new JTextField();
        emailTextField.setText(" Input email");
        emailTextField.setBounds(350, 540, 270, 35);
        emailTextField.setForeground(new Color(0x9B9B9B));
        emailTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(emailTextField.getText().equals(" Input email")){
                    emailTextField.setText("");
                }
                emailTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                emailTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(emailTextField.getText().equals("")){
                    emailTextField.setText(" Input email");
                    emailTextField.setForeground(new Color(0x9B9B9B));
                    emailTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }
            }
        });

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setText(" Input phone number");
        phoneNumberTextField.setBounds(350, 605, 270, 35);
        phoneNumberTextField.setForeground(new Color(0x9B9B9B));
        phoneNumberTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
        phoneNumberTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(phoneNumberTextField.getText().equals(" Input phone number")){
                    phoneNumberTextField.setText("");
                }
                phoneNumberTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
                phoneNumberTextField.setForeground(new Color(0x000000));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(phoneNumberTextField.getText().equals("")){
                    phoneNumberTextField.setText(" Input phone number");
                    phoneNumberTextField.setForeground(new Color(0x9B9B9B));
                    phoneNumberTextField.setFont(new Font("Calibri", Font.ITALIC, 20));
                }

            }
        });

        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        confirmButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        confirmButton.setBounds(230, 690, 200, 50);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);
        confirmButton.addActionListener(owner);

        //this.setBackground(new Color(0xABBCD9));
        this.setBackground(new Color(0xFCF8B0));
        this.setBounds(300, 0, 700, 820);
        this.setLayout(null);
        this.add(bookInformationLabel);
        this.add(setIconButton);
        this.add(titleTextField);
        this.add(authorTextField);
        this.add(publishingYearTextField);
        this.add(dateOfLendLabel);
        this.add(dateOfReturnLabel);
        this.add(personDataLabel);
        this.add(nameTextField);
        this.add(surnameTextField);
        this.add(emailTextField);
        this.add(phoneNumberTextField);
        this.add(confirmButton);
        this.add(dateOfLendPicker);
        this.add(dateOfReturnPicker);
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==setIconButton){
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:\\Users\\acer\\Desktop\\Photo\\BookLender"));
            int response = fc.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                iconPath = fc.getSelectedFile().getAbsolutePath();
                imageLabel = new JLabel();
                imageLabel.setBounds(40, 100, 200, 300);
                ImageIcon bookIcon =  ImageScaler.scale(iconPath,
                        imageLabel.getWidth(), imageLabel.getHeight());
                imageLabel.setIcon(bookIcon);
                setIconButton.setVisible(false);
                changeIconButton = new JButton();
                changeIconButton.setBounds(85, 415, 120, 30);
                changeIconButton.setText("Change icon");
                changeIconButton.setFont(new Font("Calibri", Font.PLAIN, 17));
                changeIconButton.setFocusable(false);
                changeIconButton.addActionListener(this);
                this.add(imageLabel);
                this.add(changeIconButton);
                this.revalidate();
                this.repaint();
            }
        }

        if(e.getSource() == changeIconButton){
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("C:\\Users\\acer\\Desktop\\Photo\\BookLender"));
            int response = fc.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                iconPath = fc.getSelectedFile().getAbsolutePath();
                ImageIcon bookIcon = ImageScaler.scale(iconPath,
                        imageLabel.getWidth(), imageLabel.getHeight());
                imageLabel.setIcon(bookIcon);
            }
        }

        if(e.getSource() == confirmButton) {
            LocalDate localDateOfLend = DateConverter.toLocalDate(
                    (java.util.Date) dateOfLendPicker.getModel().getValue()
            );
            LocalDate localDateOfReturn = DateConverter.toLocalDate(
                    (java.util.Date) dateOfReturnPicker.getModel().getValue()
            );
            String iconPath = null;
            try {
                iconPath = FileManager.copyFile(this.iconPath, BOOK_IMG_DIR);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Lend lend = new Lend(titleTextField.getText(),
                                 authorTextField.getText(),
                                 publishingYearTextField.getText(),
                                 iconPath,
                                 nameTextField.getText(),
                                 surnameTextField.getText(),
                                 emailTextField.getText(),
                                 phoneNumberTextField.getText(),
                                 localDateOfLend, localDateOfReturn);
            lendService.save(lend);
        }
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}

