/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LoanDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class UpdateLoanDetails {
    
    public void setUserFinisherr(int finisherCode, int requestCode) throws InvalidActionException {

        String UPDATE_LOAN_FINISHER = "UPDATE loan_details SET loan_finisher = ? WHERE loan_code = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LOAN_FINISHER);

            preparedStatement.setInt(1, finisherCode);
            preparedStatement.setInt(2, requestCode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            throw new InvalidActionException("ocurrio un error en la DB" + e.getMessage());

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }
    }
    
}
