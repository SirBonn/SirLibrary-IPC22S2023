/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.WebServlets;

import ggnc.sirlibrary.Models.DataBase.UserDAOs.InsertUser;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
import ggnc.sirlibrary.Utils.InvalidActionException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sirbon
 */
@WebServlet(name = "signInServlet", urlPatterns = {"/signInServlet"})
public class signInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InsertUser insertUser = new InsertUser();

        String forename = request.getParameter("forename");
        String username = request.getParameter("username");
        String emailAddress = request.getParameter("emailAddress");
        String userpassword = request.getParameter("userpassword");

        try {
            Customer customer = new Customer(emailAddress, forename, username, userpassword);
            insertUser.insertUser(customer);
            request.getSession().setAttribute("userLogged", customer);
            request.setAttribute("isOk", true);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/customer.jsp");
            dispatcher.forward(request, response);

        } catch (InvalidActionException ex) {
            request.setAttribute("isOk", false);
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/signin.jsp");
            dispatcher.forward(request, response);
        }

    }

}
