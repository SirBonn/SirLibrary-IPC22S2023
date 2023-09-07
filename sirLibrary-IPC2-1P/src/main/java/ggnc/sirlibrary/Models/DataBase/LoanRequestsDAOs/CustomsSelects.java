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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class CustomsSelects {

//    public LoanRequest getLoanReqnDet(int userCode, int typeReq) {
//
//        final String SQL_GET_REQUEST_W_DETAILS = "SELECT lr.code AS request_code, lr.request_state, lr.date_request, lrd.* "
//                + "FROM loan_requests lr JOIN loan_requests_detail lrd ON lr.code = lrd.code_request "
//                + "WHERE lrd.user = "+userCode+" AND lrd.type_request = "+typeReq+"";
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DBConectionManager.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_REQUEST_W_DETAILS);
//            preparedStatement.setInt(1, lr.getCode());
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                //search n set library
//                int lib = resultSet.getInt("library");
//                Library library = new SelectLibrary().SearchByCode(lib);
//                lr.setLibrary(library);
//                //search n set book
//                int isbn = resultSet.getInt("isbn");
//                Book book = new SelectBook().SearchByCode(new Book(isbn));
//                lr.setBook(book);
//                //search n set state
//                int state = resultSet.getInt("request_state");
//                lr.setReqState(state);
//                //search n set date
//                Date reqDate = resultSet.getDate("date_request");
//                lr.setReqDate(reqDate);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println("error en DB" + ex);
//
//        } finally {
//
//            DBConectionManager.close(connection);
//            DBConectionManager.close(resultSet);
//            DBConectionManager.close(preparedStatement);
//
//        }
//
//        return lr;
//    }

}
