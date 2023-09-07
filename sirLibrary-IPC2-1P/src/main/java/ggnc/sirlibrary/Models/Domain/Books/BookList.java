/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Books;

import ggnc.sirlibrary.Models.Domain.Libraries.Library;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class BookList {

    private Book book;
    private Library library;
    private int units;

    public BookList(Book book, Library library, int units) {
        this.book = book;
        this.library = library;
        this.units = units;
    }

    public BookList() {
    }
    
    
    
}
