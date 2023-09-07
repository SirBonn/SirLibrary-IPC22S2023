/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.BookDAOs;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO.SelectCategory;
import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.SelectLibrary;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Books.BookList;
import ggnc.sirlibrary.Models.Domain.Books.Category;
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
public class SelectBookList {

    public List<BookList> getBookLists() {

        String SQL_SELECT = "SELECT * FROM book_lists";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book;
        Library library;
        List<BookList> booksList = new ArrayList<>();
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int isbn = resultSet.getInt("code_book");
                int lib = resultSet.getInt("library_code");
                int units = resultSet.getInt("units");

                library = new SelectLibrary().SearchByCode(lib);
                book = new SelectBook().SearchByCode(new Book(isbn));

                booksList.add(new BookList(book, library, units));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return booksList;
    }

    public List<BookList> getBookSpecificList(int libCode) {

        String SQL_SELECT = "SELECT * FROM book_lists WHERE library_code = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book;
        Library library;
        List<BookList> booksList = new ArrayList<>();
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setInt(1, libCode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int isbn = resultSet.getInt("code_book");
                int lib = resultSet.getInt("library_code");
                int units = resultSet.getInt("units");

                library = new SelectLibrary().SearchByCode(lib);
                book = new SelectBook().SearchByCode(new Book(isbn));

                booksList.add(new BookList(book, library, units));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return booksList;
    }

}
