/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.BookDAOs;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Books.BookList;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertToList {
    
     public int addToList(BookList list) throws InvalidActionException {

        String SQL_INSERT_BOOK = "INSERT INTO book_lists(code_book, library_code, units) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setInt(1, list.getBook().getIsbn());
            preparedStatement.setInt(2, list.getLibrary().getCode());
            preparedStatement.setInt(3, list.getUnits());

            rowsAfected = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar un liibro a la lista " +ex);
            throw new InvalidActionException("error en DB" +ex);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

        return rowsAfected;
    }
}
