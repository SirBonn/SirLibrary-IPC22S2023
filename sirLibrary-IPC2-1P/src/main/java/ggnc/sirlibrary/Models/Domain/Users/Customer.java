/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Users;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter

public class Customer extends User {

    private boolean isActive;
    private int maxBooks = 5;
    private double amount;
    private String status;

    public Customer(boolean isActive, double amount, int code, String email, String forename, String username, String userpswrd, int maxBooks) {
        super(code, email, forename, username, userpswrd, 3);
        this.isActive = isActive;
        this.status = setstatus(isActive);
        this.amount = amount;
        this.maxBooks = maxBooks;
    }

    public Customer(boolean isActive, double amount, int code, String email, String forename, String username, String userpswrd) {
        super(code, email, forename, username, userpswrd, 3);
        this.isActive = isActive;
        this.status = setstatus(isActive);
        this.amount = amount;
    }
    
    public Customer(String email, String forename, String username, String userpswrd) {
        super(email, forename, username, userpswrd, 3);
        this.isActive = true;
        this.status = setstatus(true);
        this.amount = 0;
    }

    public Customer(boolean isActive, double amount, int maxBooks) {
        this.isActive = isActive;
        this.status = setstatus(isActive);
        this.amount = amount;
        this.maxBooks = maxBooks;
    }

    public Customer() {
    }

    private String setstatus(boolean state) {
        if (state) {
            return "Activo";
        } else {
            return null;
        }
    }

}
