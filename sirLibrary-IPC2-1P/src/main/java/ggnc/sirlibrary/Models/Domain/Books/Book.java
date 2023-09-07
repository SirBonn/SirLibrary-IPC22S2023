/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Books;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Book {

    private int isbn;
    private String tittle;
    private double cost;
    private String author;
    private Category category;

    public Book(int isbn, String tittle, String cost, String author, Category category) {
        this.isbn = isbn;
        this.tittle = tittle.toLowerCase();
        this.cost = parseDoubleWithTwoDecimals(cost);
        this.author = author.toLowerCase();
        this.category = category;
    }

    public Book(int isbn) {
        this.isbn = isbn;
    }
    
    public Book() {
    }

    public static double parseDoubleWithTwoDecimals(String input) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); // Patrón de formato para dos decimales
        double parsedValue = 0.0;

        try {
            parsedValue = decimalFormat.parse(input).doubleValue();
        } catch (ParseException e) {
            
        }

        return parsedValue;
    }
}
