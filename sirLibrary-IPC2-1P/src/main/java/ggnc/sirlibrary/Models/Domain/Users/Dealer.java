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
public class Dealer extends User {

    private int deliveries;
    private boolean isActive;
    private String status;

    public Dealer(int deliveries, boolean isActive, int code, String email, String forename, String username, String userpswrd) {
        super(code, email, forename, username, userpswrd, 2);
        this.deliveries = 0;
        this.isActive = true;
        this.status = setstatus(true);

    }

    public Dealer(String email, String forename, String username, String userpswrd) {
        super(email, forename, username, userpswrd, 2);
        this.deliveries = 0;
        this.isActive = true;
        this.status = setstatus(true);

    }

    public Dealer(int deliveries, boolean isActive) {
        this.deliveries = deliveries;
        this.isActive = isActive;
        this.status = setstatus(isActive);

    }

        public Dealer(int code, String email, String forename, String username, String userpswrd) {
        super(code, email, forename, username, userpswrd, 2);
        this.deliveries = 0;
        this.isActive = true;
        this.status = setstatus(true);

    }
    
    
    public Dealer() {
    }

    public int isActive() {
        if (isActive) {
            return 1;
        } else {
            return 0;
        }
    }

    private String setstatus(boolean state) {
        if (state) {
            return "Activo";
        } else {
            return null;
        }
    }
}
