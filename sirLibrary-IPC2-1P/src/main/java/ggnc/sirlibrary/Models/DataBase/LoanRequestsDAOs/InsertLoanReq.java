/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.LoanRequest;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertLoanReq {

    public void insertLoanDetail(LoanRequest loanRequest) throws InvalidActionException {

        String SQL_INSERT_DETAIL = "INSERT INTO loan_requests (code, library, isbn, request_state, date_request) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_DETAIL);
            preparedStatement.setInt(1, loanRequest.getCode());
            preparedStatement.setInt(2, loanRequest.getLibrary().getCode());
            preparedStatement.setInt(3, loanRequest.getBook().getIsbn());
            preparedStatement.setInt(4, loanRequest.getReqState());
            preparedStatement.setString(5, loanRequest.getFormatedDate());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar la solicitud de prestamo");
            ex.printStackTrace(System.out);
            

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

    }

}
