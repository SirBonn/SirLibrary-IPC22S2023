/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.LibraryDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
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
public class SelectLibrary {

    public Library SearchByCode(int lib) {

        String SQL_SELECT_BY_CODE = "SELECT * FROM libraries WHERE code = ?";
        Library library = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, lib);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int code = resultSet.getInt("code");
                String alias = resultSet.getString("alias");
                String direction = resultSet.getString("direction");

                library = new Library(code, alias, direction);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return library;
    }

    public List<Library> getAllLibraries() {
        final String SQL_SELECT = "SELECT * FROM libraries";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Library library;
        List<Library> libraries = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                int code = resultSet.getInt("code");
                String alias = resultSet.getString("alias");
                String direction = resultSet.getString("direction");

                library = new Library(code, alias, direction);
                
                libraries.add(library);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return libraries;
    }

}
