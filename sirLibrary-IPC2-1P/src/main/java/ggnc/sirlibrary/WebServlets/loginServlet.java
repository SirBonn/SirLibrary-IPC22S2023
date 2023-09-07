/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.WebServlets;

import ggnc.sirlibrary.Models.DataBase.UserDAOs.SelectUser;
import ggnc.sirlibrary.Models.Domain.Users.User;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    private User user;
    private SelectUser selectUser = new SelectUser();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loginSession(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("action");

        if (accion != null) {

            switch (accion) {
                case "signOut":
                    closeSession(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isLogged(String username, String password) { //metodo para verificar si el usuario se encuentra dentro de la base de datos
        User tmpUsuario = selectUser.SearchByUser(new User(username, password));
        if (tmpUsuario.getLevelUser() == -1) {
            return false;
        } else {
            this.user = tmpUsuario;
        }
        return true;
    }

    private void loginSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //metodo que asigna la sesion y redirige a la pagina correspondiente
        String emailAddress = request.getParameter("emailAddress");
        String userpassword = request.getParameter("userpassword");

        if (isLogged(emailAddress, userpassword)) {

            request.getSession().setAttribute("userLogged", this.user);

            if (this.user.getLevelUser() == 3) {

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customerServices");
                dispatcher.forward(request, response);
            }
            if (this.user.getLevelUser() == 2) {

                
//                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dealerServices");
//                dispatcher.forward(request, response);
            }
            if (this.user.getLevelUser() == 1) {

                 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/receptionistServices");
                dispatcher.forward(request, response);
            }
            if (this.user.getLevelUser() == 0) {

                //RequestDispatcher dispatcher = request.getRequestDispatcher("adminServices");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminServices");
                dispatcher.forward(request, response);
            }

        } else {
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void closeSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

}
