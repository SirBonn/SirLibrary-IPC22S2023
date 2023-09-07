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
@lombok.Getter @lombok.Setter
public class Admin extends User{
    
    
    public Admin(int code, String email, String forename, String username, String userpswrd) {
        super(code, email, forename, username, userpswrd, 0);
    }

    public Admin() {
    }
    
    
    
}
