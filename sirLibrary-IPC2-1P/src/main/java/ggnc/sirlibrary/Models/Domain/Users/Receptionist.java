/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Users;

import ggnc.sirlibrary.Models.Domain.Libraries.Library;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class Receptionist extends User {

    private boolean isActive;
    private String status;
    private Library library;

    public Receptionist(boolean isActive, Library library, int code, String email, String forename, String username, String userpswrd) {
        super(code, email, forename, username, userpswrd, 1);
        this.isActive = isActive;
        this.status = setstatus(isActive);

        this.library = library;
    }

    public Receptionist(Library library, String email, String forename, String username, String userpswrd) {
        super(email, forename, username, userpswrd, 1);
        this.isActive = true;
        this.status = setstatus(true);
        this.library = library;
    }
    public Receptionist(String email, String forename, String username, String userpswrd) {
        super(email, forename, username, userpswrd, 1);
        this.isActive = true;
        this.status = setstatus(true);
    }

    public Receptionist(boolean isActive, Library libraryCode) {
        this.isActive = isActive;
        this.status = setstatus(isActive);
        this.library = libraryCode;
    }

    public Receptionist() {
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
