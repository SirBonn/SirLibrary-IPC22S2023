/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.Domain.Loans;

import ggnc.sirlibrary.Models.Domain.Users.User;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author sirbon
 */
@lombok.Getter
@lombok.Setter
public class LoanDetail {

    private Loan loan;
    private User user;
    private User finisher;
    private Date retrievedDate;
    private double late_fee;

    public LoanDetail() {
    }

    public LoanDetail(Loan loan, User user, User finisher, Date retrievedDate, double late_fee) {
        this.loan = loan;
        this.user = user;
        this.finisher = finisher;
        this.retrievedDate = retrievedDate;
        this.late_fee = late_fee;
    }

    public LoanDetail(Loan loan, User user, User finisher, int days, double late_fee) {
        this.loan = loan;
        this.user = user;
        this.finisher = finisher;
        this.retrievedDate = setRetDate(days);
        this.late_fee = late_fee;
    }

    public LoanDetail(Loan loan, User user, User finisher, double late_fee) {
        this.loan = loan;
        this.user = user;
        this.finisher = finisher;
        this.late_fee = late_fee;
    }

    private Date setRetDate(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days); // Sumar la duración en días
        java.util.Date utilDate = cal.getTime();
        java.sql.Date retrDate = new java.sql.Date(utilDate.getTime());
        return retrDate;
    }

    public LoanDetail(Loan loan, User user, int days) {
        this.loan = loan;
        this.user = user;
        this.retrievedDate = setRetDate(days);
    }

    public String getFormatedRetrieveDate() {
        if (retrievedDate != null) {

            LocalDate localDate = this.retrievedDate.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = localDate.format(formatter);

            return fechaStr;
        } else {
            return "";
        }
    }

}
