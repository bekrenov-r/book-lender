package com.bekrenov.panels;

import com.bekrenov.BookLenderFrame;
import com.bekrenov.entity.Lend;
import com.bekrenov.service.LendService;
import com.bekrenov.service.LendServiceImpl;
import com.bekrenov.util.DateConverter;
import com.bekrenov.util.DateLabelFormatter;
import com.bekrenov.util.ImageScaler;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;


public class LendPanel extends AbstractBookLenderPanel implements ActionListener {
    //TODO: make icon resize automatically
    private JButton backToMenuButton;
    private JButton editButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private JTextField bookYearTextField;
    private JTextField dateOfLendTextField;
    private JTextField dateOfReturnTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JDatePickerImpl dateOfLendPicker;
    private JDatePickerImpl dateOfReturnPicker;

    private Lend lend;
    private BookLenderFrame owner;
    private LendService lendService;



    public LendPanel(){
    }

    public LendPanel(Lend lend, BookLenderFrame owner) {
        super(owner);

        this.lend = lend;
//        this.lendsListPanel = lendsListPanel;
        this.owner = owner;

        lendService = new LendServiceImpl();

        JLabel iconLabel = new JLabel();
        iconLabel.setBounds(40, 50, 200, 250);
        ImageIcon icon = ImageScaler.scale(lend.getImgPath(), iconLabel.getWidth(), iconLabel.getHeight());
        iconLabel.setIcon(icon);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(310, 40, 200, 40);
        titleLabel.setFont(new Font("Calibri", Font.PLAIN, 27));
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);

        titleTextField = new JTextField(lend.getBookTitle());
        titleTextField.setBounds(310, 80, 250, 35);
        titleTextField.setFont(new Font("Calibri", Font.PLAIN, 23));
        titleTextField.setBackground(new Color(0xffffff));
        titleTextField.setEditable(false);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(310, 140, 200, 40);
        authorLabel.setFont(new Font("Calibri", Font.PLAIN, 27));
        authorLabel.setVerticalTextPosition(JLabel.CENTER);
        authorLabel.setHorizontalTextPosition(JLabel.CENTER);

        authorTextField = new JTextField(lend.getBookAuthor());
        authorTextField.setBounds(310, 185, 250, 35);
        authorTextField.setFont(new Font("Calibri", Font.PLAIN, 23));
        authorTextField.setBackground(new Color(0xffffff));
        authorTextField.setEditable(false);

        JLabel bookYearLabel = new JLabel("Publishing year:");
        bookYearLabel.setBounds(310, 245, 300, 40);
        bookYearLabel.setFont(new Font("Calibri", Font.PLAIN, 27));
        bookYearLabel.setVerticalTextPosition(JLabel.CENTER);
        bookYearLabel.setHorizontalTextPosition(JLabel.CENTER);

        bookYearTextField = new JTextField(String.valueOf(lend.getBookYear()));
        bookYearTextField.setBounds(310, 290, 250, 35);
        bookYearTextField.setFont(new Font("Calibri", Font.PLAIN, 23));
        bookYearTextField.setBackground(new Color(0xffffff));
        bookYearTextField.setEditable(false);

        JLabel dateOfLendLabel = new JLabel("Date of lend:");
        dateOfLendLabel.setBounds(30, 360, 200, 40);
        dateOfLendLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        dateOfLendLabel.setVerticalTextPosition(JLabel.CENTER);
        dateOfLendLabel.setHorizontalTextPosition(JLabel.CENTER);

        dateOfLendTextField = new JTextField(lend.getDateOfLend().toString());
        dateOfLendTextField.setBounds(160, 360, 120, 35);
        dateOfLendTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
        dateOfLendTextField.setBackground(new Color(0xffffff));
        dateOfLendTextField.setEditable(false);
        dateOfLendTextField.setHorizontalAlignment(JTextField.CENTER);

        JLabel dateOfReturnLabel = new JLabel("Date of return:");
        dateOfReturnLabel.setBounds(330, 360, 200, 40);
        dateOfReturnLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        dateOfReturnLabel.setVerticalTextPosition(JLabel.CENTER);
        dateOfReturnLabel.setHorizontalTextPosition(JLabel.CENTER);

        dateOfReturnTextField = new JTextField(lend.getDateOfReturn().toString());
        dateOfReturnTextField.setBounds(480, 360, 120, 35);
        dateOfReturnTextField.setFont(new Font("Calibri", Font.PLAIN, 20));
        dateOfReturnTextField.setBackground(new Color(0xffffff));
        dateOfReturnTextField.setEditable(false);
        dateOfReturnTextField.setHorizontalAlignment(JTextField.CENTER);

        UtilDateModel dateOfLendModel = new UtilDateModel();
        LocalDate localDate = lend.getDateOfLend();
        dateOfLendModel.setDate(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        dateOfLendModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dateOfLendPanel = new JDatePanelImpl(dateOfLendModel, p);
        dateOfLendPicker = new JDatePickerImpl(dateOfLendPanel, new DateLabelFormatter());
        dateOfLendPicker.setBounds(160, 360, 120, 35);
        dateOfLendPicker.setBackground(new Color(0xFCF8B0));
        dateOfLendPicker.setFont(new Font("Calibri", Font.PLAIN, 20));
        dateOfLendPicker.setButtonFocusable(false);
        dateOfLendPicker.setTextEditable(true);
        dateOfLendPicker.setVisible(false);

        UtilDateModel dateOfReturnModel = new UtilDateModel();
        localDate = lend.getDateOfReturn();
        dateOfReturnModel.setDate(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        dateOfReturnModel.setSelected(true);
        JDatePanelImpl dateOfReturnPanel = new JDatePanelImpl(dateOfReturnModel, p);
        dateOfReturnPicker = new JDatePickerImpl(dateOfReturnPanel, new DateLabelFormatter());
        dateOfReturnPicker.setBounds(480, 360, 120, 35);
        dateOfReturnPicker.setBackground(new Color(0xFCF8B0));
        dateOfReturnPicker.setFont(new Font("Calibri", Font.PLAIN, 20));
        dateOfReturnPicker.setButtonFocusable(false);
        dateOfReturnPicker.setTextEditable(true);
        dateOfReturnPicker.setVisible(false);

        JLabel whoBorrowedLabel = new JLabel("Borrowed by: ");
        whoBorrowedLabel.setBounds(40, 430, 350, 40);
        whoBorrowedLabel.setFont(new Font("Calibri", Font.PLAIN, 32));
        whoBorrowedLabel.setVerticalTextPosition(JLabel.CENTER);
        whoBorrowedLabel.setHorizontalTextPosition(JLabel.CENTER);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(70, 495, 150, 30);
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
        nameLabel.setVerticalTextPosition(JLabel.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);

        firstNameTextField = new JTextField(lend.getPersonFirstName());
        firstNameTextField.setBounds(150, 490, 150, 35);
        firstNameTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
        firstNameTextField.setBackground(new Color(0xffffff));
        firstNameTextField.setEditable(false);

        JLabel surnameLabel = new JLabel("Surname: ");
        surnameLabel.setBounds(40, 555, 150, 30);
        surnameLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
        surnameLabel.setVerticalTextPosition(JLabel.CENTER);
        surnameLabel.setHorizontalTextPosition(JLabel.CENTER);

        lastNameTextField = new JTextField(lend.getPersonLastName());
        lastNameTextField.setBounds(150, 550, 150, 35);
        lastNameTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
        lastNameTextField.setBackground(new Color(0xffffff));
        lastNameTextField.setEditable(false);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(340, 495, 150, 30);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
        emailLabel.setVerticalTextPosition(JLabel.CENTER);
        emailLabel.setHorizontalTextPosition(JLabel.CENTER);

        emailTextField = new JTextField(lend.getPersonEmail());
        emailTextField.setBounds(410, 490, 200, 35);
        emailTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
        emailTextField.setBackground(new Color(0xffffff));
        emailTextField.setEditable(false);

        JLabel phoneNumberLabel = new JLabel("Phone: ");
        phoneNumberLabel.setBounds(330, 555, 150, 30);
        phoneNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
        phoneNumberLabel.setVerticalTextPosition(JLabel.CENTER);
        phoneNumberLabel.setHorizontalTextPosition(JLabel.CENTER);

        phoneNumberTextField = new JTextField(lend.getPersonPhoneNumber());
        phoneNumberTextField.setBounds(410, 550, 200, 35);
        phoneNumberTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
        phoneNumberTextField.setBackground(new Color(0xffffff));
        phoneNumberTextField.setEditable(false);

        editButton = new JButton("Edit");
        editButton.setBounds(240, 650, 80, 40);
        editButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        editButton.setFocusable(false);
        editButton.addActionListener(this);

        backToMenuButton = new JButton("To menu");
        backToMenuButton.setBounds(90, 650, 120, 40);
        backToMenuButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        backToMenuButton.setFocusable(false);

        saveButton = new JButton("Save");
        saveButton.setBounds(240, 650, 80, 40);
        saveButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        saveButton.setFocusable(false);
        saveButton.setVisible(false);
        saveButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(350, 650, 120, 40);
        deleteButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        deleteButton.setFocusable(false);
        deleteButton.setVisible(true);
        deleteButton.addActionListener(this);

        this.setBounds(300, 0, 700, 820);
        this.setBackground(new Color(0xFCF8B0));
        this.setLayout(null);
        this.add(iconLabel);
        this.add(backToMenuButton);
        this.add(titleLabel);
        this.add(titleTextField);
        this.add(authorLabel);
        this.add(authorTextField);
        this.add(bookYearLabel);
        this.add(bookYearTextField);
        this.add(dateOfLendLabel);
        this.add(dateOfLendTextField);
        this.add(dateOfReturnLabel);
        this.add(dateOfReturnTextField);
        this.add(dateOfLendPicker);
        this.add(dateOfReturnPicker);
        this.add(whoBorrowedLabel);
        this.add(nameLabel);
        this.add(firstNameTextField);
        this.add(surnameLabel);
        this.add(lastNameTextField);
        this.add(emailLabel);
        this.add(emailTextField);
        this.add(phoneNumberLabel);
        this.add(phoneNumberTextField);
        this.add(editButton);
        this.add(saveButton);
        this.add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editButton){
            setAllTextFieldsEditable(true);
            dateOfLendTextField.setVisible(false);
            dateOfReturnTextField.setVisible(false);
            dateOfLendPicker.setVisible(true);
            dateOfReturnPicker.setVisible(true);
            editButton.setVisible(false);
            saveButton.setVisible(true);
        }

        if(e.getSource() == saveButton){
            setAllTextFieldsEditable(false);
            LocalDate dateOfLend = DateConverter.toLocalDate(
                    (java.util.Date) dateOfLendPicker.getModel().getValue()
            );
            dateOfLendTextField.setText(dateOfLend.toString());
            dateOfLendTextField.setVisible(true);
            LocalDate dateOfReturn = DateConverter.toLocalDate(
                    (java.util.Date) dateOfReturnPicker.getModel().getValue()
            );
            dateOfReturnTextField.setText(dateOfReturn.toString());
            dateOfReturnTextField.setVisible(true);
            lend.setBookTitle(titleTextField.getText());
            lend.setBookAuthor(authorTextField.getText());
            lend.setBookYear(bookYearTextField.getText());
            lend.setPersonFirstName(firstNameTextField.getText());
            lend.setPersonLastName(lastNameTextField.getText());
            lend.setPersonEmail(emailTextField.getText());
            lend.setPersonPhoneNumber(phoneNumberTextField.getText());
            lend.setDateOfLend(dateOfLend);
            lend.setDateOfReturn(dateOfReturn);
            lendService.save(lend);
            saveButton.setVisible(false);
            editButton.setVisible(true);
        }

        if(e.getSource() == deleteButton){
            String[] options = {"Delete", "Cancel"};
            JOptionPane pane = new JOptionPane("Delete this lend without \npossibility of restoration?",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null , options, -2);
            JDialog d = pane.createDialog("Confirm action");
            d.setLocation(650, 350);
            d.setVisible(true);
            Object selectedOption = pane.getValue();
            if(!(selectedOption == null) && selectedOption.equals(options[0])){
                lendService.deleteById(lend.getId());
                // todo: refresh LendsListPanel after delete
                // .refreshList(owner);
            }
        }
    }

    private void setAllTextFieldsEditable(boolean param){
        titleTextField.setEditable(param);
        authorTextField.setEditable(param);
        bookYearTextField.setEditable(param);
        firstNameTextField.setEditable(param);
        lastNameTextField.setEditable(param);
        emailTextField.setEditable(param);
        phoneNumberTextField.setEditable(param);
    }


    public JButton getBackToMenuButton(){
        return backToMenuButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
