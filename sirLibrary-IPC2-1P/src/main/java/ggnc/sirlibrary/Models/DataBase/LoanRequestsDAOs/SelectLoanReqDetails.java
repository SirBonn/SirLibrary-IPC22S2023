/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.DataBase.UserDAOs.SelectUser;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.*;
import ggnc.sirlibrary.Models.Domain.Users.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sirbon
 */
public class SelectLoanReqDetails {

    public List<LoanReqDetail> searchLoanRequest(int userCode) {

        final String SQL_SELECT_BY_USER = "SELECT * FROM loan_requests lr JOIN loan_requests_detail lrd "
                + "ON lr.code = lrd.code_request WHERE lrd.user = ? ORDER BY lr.date_request DESC";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LoanReqDetail> lrds = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER);
            preparedStatement.setInt(1, userCode);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set loanRequest
                int code_request = resultSet.getInt("code_request");
                LoanRequest loanRequest = new SelectLoanReq().searchLoanRequest(new LoanRequest(code_request));
                //search n set user
                int user = resultSet.getInt("user");
                Customer customer = new SelectUser().getCustomer(new User(user));
                //search n set state
                int request_receiver = resultSet.getInt("request_receiver");
                User userReceiver = null;
                if (request_receiver != 0) {
                    userReceiver = new SelectUser().getCustomer(new User(request_receiver));
                }
                int retrievedDays = resultSet.getInt("retrieved_date");

                //search n set type
                int type_request = resultSet.getInt("type_request");
                //search n set date
                LoanReqDetail lrd = new LoanReqDetail(loanRequest, customer, userReceiver, retrievedDays, type_request);

                lrds.add(lrd);
            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return lrds;
    }

    public List<LoanReqDetail> getAllReqs(int libCode) {

        final String SQL_SELECT_BY_LIBRARY = "select * from loan_requests lr JOIN loan_requests_detail lrd "
                + "ON lr.code = lrd.code_request WHERE lr.library = ? ORDER BY lr.date_request DESC";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LoanReqDetail> lrds = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LIBRARY);
            preparedStatement.setInt(1, libCode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set loanRequest
                int code_request = resultSet.getInt("code_request");
                LoanRequest loanRequest = new SelectLoanReq().searchLoanRequest(new LoanRequest(code_request));
                //search n set user
                int user = resultSet.getInt("user");
                Customer customer = new SelectUser().getCustomer(new User(user));
                //search n set state
                int request_receiver = resultSet.getInt("request_receiver");
                User userReceiver = null;
                if (request_receiver != 0) {
                    userReceiver = new SelectUser().getCustomer(new User(request_receiver));
                }
                //search n set date
                int retrievedDays = resultSet.getInt("retrieved_date");

                //search n set type
                int type_request = resultSet.getInt("type_request");
                LoanReqDetail lrd = new LoanReqDetail(loanRequest, customer, userReceiver, retrievedDays, type_request);

                lrds.add(lrd);
            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return lrds;
    }

    public List<LoanReqDetail> getSpecificReqs(int libCode, int reqState) {
        List<LoanReqDetail> allReqDetails = getAllReqs(libCode);
        List<LoanReqDetail> specificList = new ArrayList<>();

        for (LoanReqDetail req : allReqDetails) {
            if (req.getLoanReq().getReqState() == reqState) {
                specificList.add(req);
            }
        }

        return specificList;

    }

    public LoanReqDetail getLoanReqDetail(int code) {

        final String SQL_SELECT_BY_CODE = "SELECT * FROM loan_requests_detail "
                + "WHERE code_request = ?";

        LoanReqDetail lrd = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, code);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set loanRequest
                int code_request = resultSet.getInt("code_request");
                LoanRequest loanRequest = new SelectLoanReq().searchLoanRequest(new LoanRequest(code_request));
                //search n set user
                int user = resultSet.getInt("user");
                Customer customer = new SelectUser().getCustomer(new User(user));
                //search n set state
                int request_receiver = resultSet.getInt("request_receiver");
                User userReceiver = null;
                if (request_receiver != 0) {
                    userReceiver = new SelectUser().getReceptionist(new User(request_receiver));
                }
                int retrievedDays = resultSet.getInt("retrieved_date");

                int type_request = resultSet.getInt("type_request");
                lrd = new LoanReqDetail(loanRequest, customer, userReceiver, retrievedDays, type_request);

            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return lrd;
    }

}
