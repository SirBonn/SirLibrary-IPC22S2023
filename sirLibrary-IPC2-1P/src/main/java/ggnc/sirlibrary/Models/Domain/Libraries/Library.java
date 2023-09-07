/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Libraries;

/**
 *
 * @author sirbon
 */
@lombok.Getter @lombok.Setter
@lombok.NoArgsConstructor
public class Library {
    
    private int code;
    private String alias;
    private String direction;

    public Library(int code, String alias, String direction) {
        this.code = code;
        this.alias = alias;
        this.direction = direction;
    }
    
    
}
