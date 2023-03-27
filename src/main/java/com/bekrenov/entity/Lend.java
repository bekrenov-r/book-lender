package com.bekrenov.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="lend")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_year")
    private String bookYear;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "person_first_name")
    private String personFirstName;

    @Column(name = "person_last_name")
    private String personLastName;

    @Column(name = "person_email")
    private String personEmail;

    @Column(name = "person_phone_number")
    private String personPhoneNumber;

    @Column(name = "date_of_lend")
    private LocalDate dateOfLend;

    @Column(name = "date_of_return")
    private LocalDate dateOfReturn;

    public Lend(){}

    public Lend(String bookTitle,
                String bookAuthor,
                String bookYear,
                String imgPath,
                String personFirstName,
                String personLastName,
                String personEmail,
                String personPhoneNumber,
                LocalDate dateOfLend,
                LocalDate dateOfReturn) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
        this.imgPath = imgPath;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.personEmail = personEmail;
        this.personPhoneNumber = personPhoneNumber;
        this.dateOfLend = dateOfLend;
        this.dateOfReturn = dateOfReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookYear() {
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public LocalDate getDateOfLend() {
        return dateOfLend;
    }

    public void setDateOfLend(LocalDate dateOfLend) {
        this.dateOfLend = dateOfLend;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public String toString() {
        return "<html>" + personFirstName + " " + personLastName + "<br/>" + "\"" + bookTitle + "\"";
    }
}
