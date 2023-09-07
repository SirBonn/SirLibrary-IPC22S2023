/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.LoanReqDetail;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertLoanReqDet {

    public void insertLoanReqDet(LoanReqDetail loanRD) throws InvalidActionException {

        String SQL_INSERT_DETAIL = "INSERT INTO loan_requests_detail (code_request, user, type_request, retrieved_date) "
                + "VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_DETAIL);
            preparedStatement.setInt(1, loanRD.getLoanReq().getCode());
            preparedStatement.setInt(2, loanRD.getUser().getCode());
            preparedStatement.setInt(3, loanRD.getTypeRequest());
            preparedStatement.setInt(4, loanRD.getRetrievedDays());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar el detalle de prestamo");
            ex.printStackTrace(System.out);
            throw new InvalidActionException("error en DB" + ex);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

    }

}
