/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Books.Category;
import ggnc.sirlibrary.Utils.InvalidActionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirbon
 */
public class InsertCategory {

    public int insertCategory(Category category) throws InvalidActionException {

        String SQL_INSERT_BOOK = "INSERT INTO categories(code, category, category_resume) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setInt(1, category.getCode());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getResume());

            rowsAfected = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error al agregar una category");
            ex.printStackTrace(System.out);

        } finally {
            DBConectionManager.close(connection);
            DBConectionManager.close(preparedStatement);

        }

        return rowsAfected;
    }
}
