/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.DataBase.UserDAOs.SelectUser;
import ggnc.sirlibrary.Models.Domain.Loans.Loan;
import ggnc.sirlibrary.Models.Domain.Loans.LoanDetail;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
import ggnc.sirlibrary.Models.Domain.Users.User;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.InvalidApplicationException;

/**
 *
 * @author sirbon
 */
public class SelectLoanDetails {

    public List<LoanDetail> searchLoanRequest(int userCode) throws InvalidActionException {

        final String SQL_SELECT_BY_USER = "Select * from loan_details ld JOIN loans l ON ld.loan_code = l.code "
                + "WHERE ld.user_code=? ORDER BY l.loaned_date DESC";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LoanDetail> loanDetails = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER);
            preparedStatement.setInt(1, userCode);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set loanRequest
                int loan_code = resultSet.getInt("loan_code");
                Loan loan = new SelectLoan().searchLoan(new Loan(loan_code));
                //search n set user
                int user = resultSet.getInt("user_code");
                Customer customer = new SelectUser().getCustomer(new User(user));
                //search n set state
                int request_receiver = resultSet.getInt("loan_finisher");
                User loanFinisher = null;
                if (request_receiver != 0) {
                    loanFinisher = new SelectUser().getCustomer(new User(request_receiver));
                }
                int retrievedDays = resultSet.getInt("retrieved_date");
                //search n set type
                double type_request = resultSet.getDouble("late_fee");
                //search n set date
                LoanDetail ld = new LoanDetail(loan, customer, loanFinisher, retrievedDays, type_request);

                loanDetails.add(ld);
            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);
            throw new InvalidActionException("Ocurrio un error: " + ex.getMessage());

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return loanDetails;
    }

    public List<LoanDetail> getAllLoans(int libCode) {

        final String SQL_SELECT_BY_LIBRARY = "Select * from loan_details ld JOIN loans l ON ld.loan_code = l.code "
                + "WHERE l.library=? ORDER BY l.loaned_date DESC";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LoanDetail> loanDetails = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LIBRARY);
            preparedStatement.setInt(1, libCode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //search n set loanRequest
                int loan_code = resultSet.getInt("loan_code");
                Loan loan = new SelectLoan().searchLoan(new Loan(loan_code));
                //search n set user
                int user = resultSet.getInt("user_code");
                Customer customer = new SelectUser().getCustomer(new User(user));
                //search n set state
                int request_receiver = resultSet.getInt("loan_finisher");
                User loanFinisher = null;
                if (request_receiver != 0) {
                    loanFinisher = new SelectUser().getCustomer(new User(request_receiver));
                }
                Date retrievedDate = resultSet.getDate("retrieved_date");
                //search n set type
                double late_fee = resultSet.getDouble("late_fee");
                //search n set date
                LoanDetail ld = new LoanDetail(loan, customer, loanFinisher, retrievedDate, late_fee);

                loanDetails.add(ld);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error en DB" + ex);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return loanDetails;
    }

    public List<LoanDetail> getSpecificReqs(int libCode, int loanState){
        List<LoanDetail> allReqDetails = getAllLoans(libCode);
        List<LoanDetail> specificList = new ArrayList<>();

        for (LoanDetail req : allReqDetails) {
            if (req.getLoan().getState() == loanState) {
                specificList.add(req);
            }
        }

        return specificList;

    }

    public LoanDetail getLoanReqDetail(int code) throws InvalidActionException {

        final String SQL_SELECT_BY_CODE = "SELECT * FROM loan_details "
                + "WHERE loan_code = ?";

        LoanDetail loanDetail = null;
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
                int loan_code = resultSet.getInt("loan_code");
                Loan loan = new SelectLoan().searchLoan(new Loan(loan_code));
                //search n set user
                int user = resultSet.getInt("user_code");
                Customer customer = new SelectUser().getCustomer(new User(user));
                //search n set state
                int request_receiver = resultSet.getInt("loan_finisher");
                User loanFinisher = null;
                if (request_receiver != 0) {
                    loanFinisher = new SelectUser().getCustomer(new User(request_receiver));
                }
                int retrievedDays = resultSet.getInt("retrieved_date");
                //search n set type
                double type_request = resultSet.getDouble("late_fee");
                //search n set date
                loanDetail = new LoanDetail(loan, customer, loanFinisher, retrievedDays, type_request);

            }

        } catch (SQLException ex) {
            System.out.println("error en DB" + ex);
            throw new InvalidActionException("Ocurrio un error: " + ex.getMessage());

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return loanDetail;
    }

}
