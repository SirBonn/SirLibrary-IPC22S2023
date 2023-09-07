/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Loans.Loan;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertLoan {

    public void inserLoan(Loan loan) throws InvalidActionException {

        String SQL_INSERT_DETAIL = "INSERT INTO loans (code, library, loan_book, loan_state, loaned_date) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_DETAIL);
            preparedStatement.setInt(1, loan.getCode());
            preparedStatement.setInt(2, loan.getLibrary().getCode());
            preparedStatement.setInt(3, loan.getBook().getIsbn());
            preparedStatement.setInt(4, loan.getState());
            preparedStatement.setString(5, loan.getFormatedDate());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar el prestamo");
            throw new InvalidActionException("error en DB" + ex);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

    }
}
