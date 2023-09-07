/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class UpdateLoanReq {

    public void updateLoanReqState(int newState, int codeReq) throws InvalidActionException{
        String SQL_UPDATE_STATE = "UPDATE loan_requests SET request_state=? WHERE code=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_STATE);

            preparedStatement.setInt(1, newState);
            preparedStatement.setInt(2, codeReq);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            throw new InvalidActionException("ocurrio un error en la DB" +e.getMessage());

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }
    }

    
    
    
}
