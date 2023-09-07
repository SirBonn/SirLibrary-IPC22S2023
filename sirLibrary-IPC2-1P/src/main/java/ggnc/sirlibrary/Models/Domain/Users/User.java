/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Users;

import ggnc.sirlibrary.Utils.PasswordEncoder;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.ToString
public class User {

    private int code;
    private String email;
    private String forename;
    private String username;
    private String userpswrd;
    private int levelUser = -1;

    public User(int code, String email, String forename, String username, String userpswrd, int levelUser) {
        this.code = code;
        this.email = email;
        this.forename = forename;
        this.username = username;
        this.userpswrd = PasswordEncoder.encodePassword(userpswrd);
        this.levelUser = levelUser;
    }

    public User(String email, String forename, String username, String userpswrd, int levelUser) {
        this.code = setCode();
        this.email = email;
        this.forename = forename;
        this.username = username;
        this.userpswrd = PasswordEncoder.encodePassword(userpswrd);
        this.levelUser = levelUser;
    }

    public User(String email, String userpswrd) {
        this.email = email;
        this.userpswrd = PasswordEncoder.encodePassword(userpswrd);
    }

    public User(int code) {
        this.code = code;
    }

    public User(int code, int levelUser) {
        this.code = code;
        this.levelUser = levelUser;
    }

    private int setCode() {
        
        return ggnc.sirlibrary.Utils.KeyDropper.dropKey();
    }
    
}
