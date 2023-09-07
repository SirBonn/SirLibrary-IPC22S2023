/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.BookDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertBook {

    public int insertBook(Book book) throws InvalidActionException {

        String SQL_INSERT_BOOK = "INSERT INTO books(isbn, tittle, cost, author, category) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setInt(1, book.getIsbn());
            preparedStatement.setString(2, book.getTittle());
            preparedStatement.setDouble(3, book.getCost());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setInt(5, book.getCategory().getCode());

            rowsAfected = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar un liibro por " +ex);
            throw new InvalidActionException("error en DB" +ex);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

        return rowsAfected;
    }

}
