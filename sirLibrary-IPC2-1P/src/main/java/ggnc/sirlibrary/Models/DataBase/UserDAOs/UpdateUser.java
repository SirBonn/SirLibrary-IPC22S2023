/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.UserDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Users.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class UpdateUser {

    public void updateUser(User user) {
        final String SQL_UPDATE = "UPDATE "+ SelectUser.userTables[user.getLevelUser()]+" SET nombre_tienda=?, direccion_tienda=?, "
                + "tipo_tienda=? WHERE codigo_tienda=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

           

        } catch (SQLException e) {
            e.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

    }

    public void disableUser(User user) {
        final String SQL_UPDATE = "UPDATE " + SelectUser.userTables[user.getLevelUser()] + " SET isActive=0 WHERE code=?";

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DBConectionManager.getConnection();
                preparedStatement = connection.prepareStatement(SQL_UPDATE);

                preparedStatement.setInt(1, user.getCode());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(System.out);

            } finally {

                DBConectionManager.close(connection);
                DBConectionManager.close(preparedStatement);

            }
    }

    public void enableUser(User user) {
        final String SQL_UPDATE = "UPDATE " + SelectUser.userTables[user.getLevelUser()] + " SET isActive=1 WHERE code=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, user.getCode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }
    }
}
