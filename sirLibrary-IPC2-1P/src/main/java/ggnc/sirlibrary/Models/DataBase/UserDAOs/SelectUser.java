/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.UserDAOs;

import ggnc.sirlibrary.Models.Domain.Users.Admin;
import ggnc.sirlibrary.Models.Domain.Users.Receptionist;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
import ggnc.sirlibrary.Models.Domain.Users.Dealer;
import ggnc.sirlibrary.Models.Domain.Users.User;
import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.SelectLibrary;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Utils.*;
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
public class SelectUser {

    /**
     *
     */
    public static String[] userTables = {"administrators", "receptionists", "dealers", "users"};

    public Admin getAdmin(User user) {

        return (Admin) SearchByCode(user.getCode(), 0);

    }

    public Receptionist getReceptionist(User user) {

        return (Receptionist) SearchByCode(user.getCode(), 1);

    }

    public Dealer getDealerr(User user) {

        return (Dealer) SearchByCode(user.getCode(), 2);

    }

    public Customer getCustomer(User user) {

        return (Customer) SearchByCode(user.getCode(), 3);

    }

    public List<Admin> getAllAdmins() {
        List<User> users = getAllUsers(0);
        List<Admin> Admins = new ArrayList<>();
        for (User user : users) {
            Admin admin = (Admin) user;
            Admins.add(admin);
        }

        return Admins;
    }

    public List<Receptionist> getAllReceptionists() {
        List<User> users = getAllUsers(1);
        List<Receptionist> receptionists = new ArrayList<>();
        for (User user : users) {
            Receptionist receptionist = (Receptionist) user;
            receptionists.add(receptionist);
        }

        return receptionists;
    }

    public List<Dealer> getAllDealers() {
        List<User> users = getAllUsers(2);
        List<Dealer> dealers = new ArrayList<>();
        for (User user : users) {
            Dealer dealer = (Dealer) user;
            dealers.add(dealer);
        }

        return dealers;
    }

    public List<Customer> getAllCustomers() {
        List<User> users = getAllUsers(3);
        List<Customer> customers = new ArrayList<>();
        for (User user : users) {
            Customer customer = (Customer) user;
            customers.add(customer);
        }

        return customers;
    }

    private User setUser(int type, User user) {
        switch (type) {
            case 0:
                return (Admin) user;
            case 1:
                return (Receptionist) user;
            case 2:
                return (Dealer) user;
            case 3:
                return (Customer) user;
            default:
                return null;
        }
    }

    private User userCreator(int lvlAccesUser, ResultSet resultSet) throws SQLException {

        int code = resultSet.getInt("code");
        String email = resultSet.getString("email");
        String forename = resultSet.getString("forename");
        String username = resultSet.getString("username");
        String password = resultSet.getString("userpassword");
        boolean isActive;

        switch (lvlAccesUser) {
            case 0:
                return new Admin(code, email, forename, username, password);
            case 1:
                isActive = resultSet.getInt("isActive") == 1;
                int libraryCode = resultSet.getInt("library");
                Library lib = new SelectLibrary().SearchByCode(libraryCode);
                return new Receptionist(isActive, lib, code, email, forename, username, username);
            case 2:
                isActive = resultSet.getInt("isActive") == 1;
                int deliveries = resultSet.getInt("deliveries");
                return new Dealer(deliveries, isActive, code, email, forename, username, username);
            case 3:
                isActive = resultSet.getInt("isActive") == 1;
                double amount = resultSet.getDouble("amount");
                int maxBooks = resultSet.getInt("max_books");
                return new Customer(isActive, amount, code, email, forename, username, username, maxBooks);
            default:
                return null;
        }
    }

    private User SearchByCode(int code, int lvlAccesUser) {

        String SQL_SELECT_BY_CODE = "SELECT * FROM " + userTables[lvlAccesUser] + " WHERE code = ?";
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, code);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = userCreator(lvlAccesUser, resultSet);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return user;
    }

    public User SearchByUser(User user) {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();

            for (String list : userTables) {
                String SQL_SELECT_BY_USER = "SELECT * FROM " + list + " WHERE username = ? AND userpassword =?";
                preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getUserpswrd());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    user = userCreator(count, resultSet);
                    user = setUser(count, user);
                }

                count++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            
            

        }

        return user;
    }

    public User SearchUserOnTabler(User user, int table) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();

            String SQL_SELECT_BY_USER = "SELECT * FROM " + userTables[table] + " WHERE code = ?";

            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER);
            preparedStatement.setInt(1, user.getCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = userCreator(table, resultSet);
                user = setUser(table, user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return user;
    }

    private List<User> getAllUsers(int lvlAccesUser) {

        final String SQL_SELECT = "SELECT * FROM " + userTables[lvlAccesUser];

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<User> usuarios = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                usuarios.add(userCreator(lvlAccesUser, resultSet));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return usuarios;
    }

}
