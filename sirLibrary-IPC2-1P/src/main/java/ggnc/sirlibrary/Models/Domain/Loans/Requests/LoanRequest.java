/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Loans.Requests;

import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class LoanRequest {

    private int code = ggnc.sirlibrary.Utils.KeyDropper.dropKey();
    private Library library;
    private Book book;
    private int reqState;
    private Date reqDate = setNowDate();

    public LoanRequest(int code, Library library, Book book, int reqState) {
        this.code = code;
        this.library = library;
        this.book = book;
        this.reqState = reqState;
    }

    public LoanRequest(Library library, Book book) {
        this.library = library;
        this.book = book;
        this.reqState = 0;

    }

    public LoanRequest(int code, Library library, Book book, int reqState, String reqDate) {
        this.code = code;
        this.library = library;
        this.book = book;
        this.reqState = reqState;
        this.reqDate = setLocalRequestDate(reqDate);
    }

    public LoanRequest(int code) {
        this.code = code;
    }

    public LoanRequest() {
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

    private Date setLocalRequestDate(String fecha) {
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

    public String getFormatedDate() {

        LocalDate localDate = this.reqDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaStr = localDate.format(formatter);

        return fechaStr;
    }
}
