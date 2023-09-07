/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.WebServlets;

import ggnc.sirlibrary.Services.CustomerServices;
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
@WebServlet(name = "customerServices", urlPatterns = {"/customerServices"})
public class customerServices extends HttpServlet {

    private CustomerServices customerServices = new CustomerServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            switch (action) {
                case "sendReqLoan":
                    customerServices.sendRequestLoan(request, response);
                    break;
            }
        } else {
            //User user = (User) request.getSession().getAttribute("userLogged");
            //System.out.println("POST " + request.getAttribute("loanRequest"));
            //request.getSession().setAttribute("userLogged", user);
            request.getRequestDispatcher("customer.jsp").forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        customerServices.dataGetter(request, response);

        if (action != null) {
            switch (action) {
                case "reqLoan":
                    customerServices.createRequestLoan(request, response);
                    // System.out.println("off srvcs " + request.getAttribute("loanRequest"));
                    break;

            }

        } else {
            //customerServices.dataGetter(request, response);
//            System.out.println("GET " + request.getAttribute("loanRequest"));
            request.getRequestDispatcher("/pages/customer-page/customer-main.jsp").forward(request, response);

        }

    }

}
