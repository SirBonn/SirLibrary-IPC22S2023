/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Loans;

import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class Loan {

    private int code;
    private Library library;
    private Book book;
    private int state = 1;
    private Date loanDate = setNowDate();

    public Loan() {
    }

    public Loan(int code) {
        this.code = code;
    }
    
    public Loan(int code, Library library, Book book, int state, String loanDate) {
        this.code = code;
        this.library = library;
        this.book = book;
        this.state = state;
        this.loanDate = setLocalLoanDate(loanDate);
    }

    public Loan(int code, Library library, Book book, String loanDate) {
        this.code = code;
        this.library = library;
        this.book = book;
        this.loanDate = setLocalLoanDate(loanDate);
    }

    public Loan(int code, Library library, Book book) {
        this.code = code;
        this.library = library;
        this.book = book;
    }

    
    
    public String getFormatedDate() {

        LocalDate localDate = this.loanDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaStr = localDate.format(formatter);

        return fechaStr;
    }
    
    private Date setNowDate() {
        Date fechaDate = null;
        try {

            LocalDate fechaLD = LocalDate.now();
            fechaDate = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha por " + e);
            e.printStackTrace(System.out);
        }
        return fechaDate;
    }
    
    private Date setLocalLoanDate(String fecha) {
        Date fechaDate = null;
        try {

            LocalDate fechaLD = LocalDate.parse(fecha);
            fechaDate = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha" + fecha + " por " + e);
            e.printStackTrace(System.out);
        }

        return fechaDate;
    }

}
