/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Loans.LoanDetail;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertLoanDetail {

    public void insertLoanDetail(LoanDetail loanDetail) throws InvalidActionException {
        
        String SQL_INSERT_DETAIL = "INSERT INTO loan_details (loan_code, user_code, loan_finisher, retrieved_date, late_fee) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_DETAIL);
            preparedStatement.setInt(1, loanDetail.getLoan().getCode());
            preparedStatement.setInt(2, loanDetail.getUser().getCode());
            if (loanDetail.getFinisher() == null) {
                preparedStatement.setInt(3, 0);
            } else {
                preparedStatement.setInt(3, loanDetail.getFinisher().getCode());
            }
            preparedStatement.setString(4, loanDetail.getFormatedRetrieveDate());
            preparedStatement.setDouble(5, loanDetail.getLate_fee());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar el detalle de prestamo");
            throw new InvalidActionException("error en DB" + ex);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

    }

}
