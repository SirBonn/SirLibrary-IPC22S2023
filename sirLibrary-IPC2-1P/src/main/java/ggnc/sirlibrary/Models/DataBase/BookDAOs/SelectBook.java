/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.BookDAOs;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO.SelectCategory;
import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Books.Category;
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
public class SelectBook {

    public Book SearchByCode(Book book) {

        String SQL_SELECT_BY_CODE = "SELECT * FROM books WHERE isbn = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, book.getIsbn());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book.setTittle(resultSet.getString("tittle"));
                book.setCost(resultSet.getDouble("cost"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(new SelectCategory().SearchByCode(resultSet.getInt("category")));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return book;
    }

    public List<Book> getAllBooks() {

        String SQL_SELECT = "SELECT * FROM books";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String tittle = resultSet.getString("tittle");
                String cost = resultSet.getString("cost");
                String author = resultSet.getString("author");
                Category category = new SelectCategory().SearchByCode(resultSet.getInt("category"));
                books.add(new Book(isbn, tittle, cost, author, category));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return books;
    }
}
