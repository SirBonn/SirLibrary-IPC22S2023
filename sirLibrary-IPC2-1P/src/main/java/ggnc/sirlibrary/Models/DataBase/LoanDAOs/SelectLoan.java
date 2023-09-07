/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanDAOs;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.SelectBook;
import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.SelectLibrary;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Models.Domain.Loans.Loan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class SelectLoan {
    
    public Loan searchLoan(Loan loan) {

        final String SQL_SELECT_BY_CODE = "SELECT * FROM loans WHERE code=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, loan.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set library
                int lib = resultSet.getInt("library");
                Library library = new SelectLibrary().SearchByCode(lib);
                loan.setLibrary(library);
                //search n set book
                int isbn = resultSet.getInt("loan_book");
                Book book = new SelectBook().SearchByCode(new Book(isbn));
                loan.setBook(book);
                //search n set state
                int state = resultSet.getInt("loan_state");
                loan.setState(state);
                //search n set date
                Date loaned_date = resultSet.getDate("loaned_date");
                loan.setLoanDate(loaned_date);
            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return loan;
    }
    
}
