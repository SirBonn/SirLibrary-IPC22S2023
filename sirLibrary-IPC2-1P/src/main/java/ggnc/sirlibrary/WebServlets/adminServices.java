/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.WebServlets;

import ggnc.sirlibrary.Models.Domain.Users.User;
import ggnc.sirlibrary.Services.AdminServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author sirbon
 */
@WebServlet(name = "adminServices", urlPatterns = {"/adminServices"})
@jakarta.servlet.annotation.MultipartConfig

public class adminServices extends HttpServlet {

    private AdminServices adminServices = new AdminServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            switch (action) {
                case "addUser":
                    adminServices.insertNewUser(request, response);
                    break;
                case "addBook":
                    adminServices.insertNewBook(request, response);
                    break;
                case "loadFile":
                    adminServices.loadFiles(request, response);
                    break;
            }
        } else {
            User user = (User) request.getSession().getAttribute("userLogged");
            //request.getSession().setAttribute("userLogged", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "editUser":
                    adminServices.editUser(request, response);
                    break;
                case "disableUser":
                    adminServices.disableUser(request, response);
                    break;
                case "enableUser":
                    adminServices.enableUser(request, response);
                    break;
            }

        } else {

            System.out.println("se cargan los datos de la db");

            adminServices.dataGetter(request, response);
            request.getRequestDispatcher("/pages/admin-page/admin-main.jsp").forward(request, response);

        }

    }

}
