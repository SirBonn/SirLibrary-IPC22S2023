/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LibraryDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertLibrary {
    
    public int insertLibrary(Library lib) throws InvalidActionException {

        String SQL_INSERT_BOOK = "INSERT INTO libraries (code, alias, direction) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setInt(1, lib.getCode());
            preparedStatement.setString(2, lib.getAlias());
            preparedStatement.setString(3, lib.getDirection());

            rowsAfected = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            
            System.out.println("error al agregar una libreria");
            ex.printStackTrace(System.out);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

        return rowsAfected;
    }
}
