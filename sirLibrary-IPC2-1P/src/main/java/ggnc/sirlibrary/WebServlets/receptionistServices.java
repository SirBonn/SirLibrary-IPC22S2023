/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.WebServlets;

import ggnc.sirlibrary.Models.Domain.Users.User;
import ggnc.sirlibrary.Services.ReceptionistServices;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sirbon
 */
@WebServlet(name = "receptionistServices", urlPatterns = {"/receptionistServices"})
public class receptionistServices extends HttpServlet {

    private ReceptionistServices receptionistServices = new ReceptionistServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            switch (action) {
                case "addUser":
                    receptionistServices.insertNewUser(request, response);
                    break;
            }
        } else {
            User user = (User) request.getSession().getAttribute("userLogged");
            request.getRequestDispatcher("receptionist.jsp").forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "acceptReq":
                    receptionistServices.aceptReq(request, response);
                    break;
                case "rejectdReq":
                    receptionistServices.rejectReq(request, response);
                    break;
                case "finishLoan":
                    receptionistServices.finishLoan(request, response);
                    break;
            }

        } else {

            System.out.println("se cargan los datos de la db");
            receptionistServices.dataGetter(request, response);
            request.getRequestDispatcher("/pages/receptionist-page/receptionist-main.jsp").forward(request, response);

        }

    }

}
