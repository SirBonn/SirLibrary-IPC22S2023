/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Loans.Requests;

import ggnc.sirlibrary.Models.Domain.Users.User;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class LoanReqDetail {

    private LoanRequest loanReq;
    private User user;
    private User loanFinisher;
    private User dealerAssigned;
    private int retrievedDays;
    private int typeRequest;

    public LoanReqDetail(LoanRequest loanReq, User user, User userReceiver, int typeRequest) {
        this.loanReq = loanReq;
        this.user = user;
        this.loanFinisher = userReceiver;
        this.typeRequest = typeRequest;
    }

    public LoanReqDetail(LoanRequest loanReq, User user, int typeRequest, int days) {
        this.loanReq = loanReq;
        this.user = user;
        this.typeRequest = typeRequest;
        this.retrievedDays = days;

    }

    public LoanReqDetail(LoanRequest loanReq, User user, User loanFinisher, User dealerAssigned, int typeRequest) {
        this.loanReq = loanReq;
        this.user = user;
        this.loanFinisher = loanFinisher;
        this.dealerAssigned = dealerAssigned;
        this.typeRequest = typeRequest;
    }

    
    
    public LoanReqDetail(LoanRequest loanReq, User user, User loanFinisher, User dealerAssigned, int retrievedDays, int typeRequest) {
        this.loanReq = loanReq;
        this.user = user;
        this.loanFinisher = loanFinisher;
        this.dealerAssigned = dealerAssigned;
        this.retrievedDays = retrievedDays;
        this.typeRequest = typeRequest;
    }
    
    public LoanReqDetail(LoanRequest loanReq, User user, User loanFinisher, int retrievedDays, int typeRequest) {
        this.loanReq = loanReq;
        this.user = user;
        this.loanFinisher = loanFinisher;
        this.retrievedDays = retrievedDays;
        this.typeRequest = typeRequest;
    }

    public LoanReqDetail() {
    }

}
