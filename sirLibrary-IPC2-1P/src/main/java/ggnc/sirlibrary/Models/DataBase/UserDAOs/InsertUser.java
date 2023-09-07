/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.UserDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Users.User;
import ggnc.sirlibrary.Models.Domain.Users.Admin;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
import ggnc.sirlibrary.Models.Domain.Users.Dealer;
import ggnc.sirlibrary.Models.Domain.Users.Receptionist;
import ggnc.sirlibrary.Utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author sirbon
 */
public class InsertUser {

    private PreparedStatement getStatement(User user, Connection connection) throws SQLException {

        PreparedStatement preparedStatement = null;

        switch (user.getLevelUser()) {
            case 0:
                String SQL_INSERT_ADMIN = "INSERT INTO " + SelectUser.userTables[user.getLevelUser()]
                        + " (code, email, forename, username, userpassword, lvl_acces) VALUES (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(SQL_INSERT_ADMIN);

                preparedStatement.setInt(1, user.getCode());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getForename());
                preparedStatement.setString(4, user.getUsername());
                preparedStatement.setString(5, user.getUserpswrd());
                preparedStatement.setInt(6, user.getLevelUser());

                return preparedStatement;
            case 1:

                Receptionist userR = (Receptionist) user;
                String SQL_INSERT_RECEPTIONIST = "INSERT INTO " + SelectUser.userTables[user.getLevelUser()]
                        + " (code, email, forename, username, userpassword, lvl_acces, isActive, library) VALUES (?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(SQL_INSERT_RECEPTIONIST);

                preparedStatement.setInt(1, userR.getCode());
                preparedStatement.setString(2, userR.getEmail());
                preparedStatement.setString(3, userR.getForename());
                preparedStatement.setString(4, userR.getUsername());
                preparedStatement.setString(5, userR.getUserpswrd());
                preparedStatement.setInt(6, userR.getLevelUser());
                preparedStatement.setInt(7, userR.isActive());
                preparedStatement.setInt(8, userR.getLibrary().getCode());

                return preparedStatement;

            case 2:

                Dealer userD = (Dealer) user;
                System.out.println(userD);
                String SQL_INSERT_DEALER = "INSERT INTO " + SelectUser.userTables[user.getLevelUser()]
                        + " (code, email, forename, username, userpassword, lvl_acces, isActive, deliveries) VALUES (?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(SQL_INSERT_DEALER);

                preparedStatement.setInt(1, userD.getCode());
                preparedStatement.setString(2, userD.getEmail());
                preparedStatement.setString(3, userD.getForename());
                preparedStatement.setString(4, userD.getUsername());
                preparedStatement.setString(5, userD.getUserpswrd());
                preparedStatement.setInt(6, userD.getLevelUser());
                preparedStatement.setInt(7, userD.isActive());
                preparedStatement.setInt(8, userD.getDeliveries());

                return preparedStatement;
            case 3:
                Customer userC = (Customer) user;
                String SQL_INSERT_CUSTOMER = "INSERT INTO " + SelectUser.userTables[user.getLevelUser()]
                        + " (code, email, forename, username, userpassword, lvl_acces, isActive, amount) VALUES (?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(SQL_INSERT_CUSTOMER);

                preparedStatement.setInt(1, userC.getCode());
                preparedStatement.setString(2, userC.getEmail());
                preparedStatement.setString(3, userC.getForename());
                preparedStatement.setString(4, userC.getUsername());
                preparedStatement.setString(5, userC.getUserpswrd());
                preparedStatement.setInt(6, userC.getLevelUser());
                if (userC.isActive()) {
                    preparedStatement.setInt(7, 1);
                } else {
                    preparedStatement.setInt(7, 0);
                }

                preparedStatement.setDouble(8, userC.getAmount());

                return preparedStatement;
            default:
                return null;
        }
    }

    public int insertUser(User user) throws InvalidActionException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        fieldsValidation(user);
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = getStatement(user, connection);

            rowsAfected = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar un usuario");
            ex.printStackTrace(System.out);
            throw new InvalidActionException("error en DB" + ex);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

        return rowsAfected;
    }

    private void fieldsValidation(User user) throws InvalidActionException {
        if (user.getUserpswrd().length() < 8 || "".equals(user.getUserpswrd())) {
            throw new InvalidActionException("Verifique su contraseña y vuelva a intentar");
        } else if ("".equals(user.getEmail())) {
            throw new InvalidActionException("Verifique el email y vuelva a intentar");
        } else if ("".equals(user.getForename())) {
            throw new InvalidActionException("Verifique su nombre y vuelva a intentar");
        } else if ("".equals(user.getUserpswrd())) {
            throw new InvalidActionException("Verifique su contraseña y vuelva a intentar");
        } else if ("".equals(user.getUsername())) {
            throw new InvalidActionException("Verifique su usuario y vuelva a intentar");
        }
    }

}
