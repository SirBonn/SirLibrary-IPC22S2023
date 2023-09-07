/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.SelectBook;
import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.SelectLibrary;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.LoanRequest;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sirbon
 */
public class SelectLoanReq {

    public LoanRequest searchLoanRequest(LoanRequest lr) {

        final String SQL_SELECT_BY_ID = "SELECT * FROM loan_requests WHERE code=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, lr.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set library
                int lib = resultSet.getInt("library");
                Library library = new SelectLibrary().SearchByCode(lib);
                lr.setLibrary(library);
                //search n set book
                int isbn = resultSet.getInt("isbn");
                Book book = new SelectBook().SearchByCode(new Book(isbn));
                lr.setBook(book);
                //search n set state
                int state = resultSet.getInt("request_state");
                lr.setReqState(state);
                //search n set date
                Date reqDate = resultSet.getDate("date_request");
                lr.setReqDate(reqDate);
            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return lr;
    }

    public List<LoanRequest> getRequiredLoans(int state) {

        final String SQL_SELECT_BY_ID = "SELECT * FROM loan_requests WHERE request_state=?";
        List<LoanRequest> requests = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, state);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int code = resultSet.getInt("code_request");
                int lib = resultSet.getInt("library");
                Library library = new SelectLibrary().SearchByCode(lib);
                int isbn = resultSet.getInt("isbn");
                Book book = new SelectBook().SearchByCode(new Book(isbn));
                int stateReq = resultSet.getInt("request_state");
                String reqDate = resultSet.getString("date_request");

                requests.add(new LoanRequest(code, library, book, stateReq, reqDate));
            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return requests;
    }

}
