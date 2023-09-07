/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO;

import ggnc.sirlibrary.Models.DataBase.DBConectionManager;
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
public class SelectCategory {

    public Category SearchByCode(int categoryInt) {

        String SQL_SELECT_BY_CODE = "SELECT * FROM categories WHERE code = ?";
        Category category = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE);
            preparedStatement.setInt(1, categoryInt);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int code = resultSet.getInt("code");
                String name = resultSet.getString("category");
                String resume = resultSet.getString("category_resume");

                category = new Category(code, name, resume);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return category;
    }

    public List<Category> getAllCategories() {
        final String SQL_SELECT = "SELECT * FROM categories";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Category category;
        List<Category> categories = new ArrayList<>();

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int code = resultSet.getInt("code");
                String cat = resultSet.getString("category");
                String res = resultSet.getString("category_resume");

                category = new Category(code, cat, res);

                categories.add(category);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {

            DBConectionManager.close(connection);
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);

        }

        return categories;
    }

}
