/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Services;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO.*;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.*;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.*;
import ggnc.sirlibrary.Models.DataBase.UserDAOs.*;
import ggnc.sirlibrary.Models.Domain.Books.*;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Models.Domain.Users.*;
import ggnc.sirlibrary.Utils.FileManager;
import ggnc.sirlibrary.Utils.InvalidActionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sirbon
 */
public class AdminServices {

    public void insertNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String forename = request.getParameter("forename");
        String username = request.getParameter("username");
        String emailAddress = request.getParameter("emailAddress");
        String userpassword = request.getParameter("userpassword");

        switch (Integer.parseInt(request.getParameter("tipoUsuario"))) {
            case 1:
                user = new Receptionist(emailAddress, forename, username, userpassword);
                break;
            case 2:
                user = new Dealer(emailAddress, forename, username, userpassword);
                break;

            case 3:
                user = new Customer(emailAddress, forename, username, userpassword);
                break;
        }

        try {
            new InsertUser().insertUser(user);
            System.out.println("se agrego al usuario " + user.toString());
            request.getSession().setAttribute("message", "Se ha agregado el nuevo usuario: " + username);
            response.sendRedirect("admin.jsp");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + ex.getMessage());
            response.sendRedirect("admin.jsp");
        }

        //request.getRequestDispatcher("/pages/admin-page/admin-main.jsp").forward(request, response);
    }

    public void insertNewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String tittle = request.getParameter("tittle");
        String author = request.getParameter("author");
        String cost = request.getParameter("cost");
        int cat = Integer.parseInt(request.getParameter("category"));
        Category category = new SelectCategory().SearchByCode(cat);
        int lib = Integer.parseInt(request.getParameter("library"));
        Library library = new SelectLibrary().SearchByCode(lib);
        int units = Integer.parseInt(request.getParameter("units"));

        Book book = new Book(isbn, tittle, cost, author, category);
        BookList bl = new BookList(book, library, units);

        try {
            new InsertBook().insertBook(book);
            new InsertToList().addToList(bl);
            System.out.println("se agrego al usuario " + book.toString());
            request.setAttribute("isOk", true);
            request.getSession().setAttribute("message", "Se ha agregado el nuevo usuario: " + tittle);
            response.sendRedirect("admin.jsp");

        } catch (InvalidActionException ex) {

            request.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + ex.getMessage());
            response.sendRedirect("admin.jsp");

        }

        //request.getRequestDispatcher("/pages/admin-page/admin-main.jsp").forward(request, response);
    }

    public void dataGetter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Library> libraries = new SelectLibrary().getAllLibraries();
        request.setAttribute("libraries", libraries);
        List<Category> categories = new SelectCategory().getAllCategories();
        request.setAttribute("categories", categories);
        List<Customer> customers = new SelectUser().getAllCustomers();
        request.setAttribute("customers", customers);
        List<Receptionist> receptionists = new SelectUser().getAllReceptionists();
        request.setAttribute("receptionists", receptionists);
        List<Dealer> dealers = new SelectUser().getAllDealers();
        request.setAttribute("dealers", dealers);
        List<BookList> bookList = new SelectBookList().getBookLists();
        request.setAttribute("bookList", bookList);

    }

    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int code = Integer.parseInt(request.getParameter("code"));
        int lvlAc = Integer.parseInt(request.getParameter("lvlAcces"));

        switch (lvlAc) {
            case 1:
                Receptionist receptionist = new SelectUser().getReceptionist(new User(code));
                request.setAttribute("editUser", receptionist);
                break;
            case 2:
                Dealer dealer = new SelectUser().getDealerr(new User(code));
                request.setAttribute("editUser", dealer);
                break;
            case 3:
                Customer customer = new SelectUser().getCustomer(new User(code));
                request.setAttribute("editUser", customer);
                break;
        }
        // response.sendRedirect("admin.jsp");
        request.getRequestDispatcher("/pages/admin-page/admin-main.jsp").forward(request, response);
    }

    public void disableUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int code = Integer.parseInt(request.getParameter("code"));
        int lvlAc = Integer.parseInt(request.getParameter("lvlAcces"));

        new UpdateUser().disableUser(new User(code, lvlAc));
        request.getSession().setAttribute("message", "Se deshabilito al usuario");
        response.sendRedirect("admin.jsp");

        //request.getRequestDispatcher("/pages/admin-page/admin-main.jsp").forward(request, response);
    }

    public void enableUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int code = Integer.parseInt(request.getParameter("code"));
        int lvlAc = Integer.parseInt(request.getParameter("lvlAcces"));

        new UpdateUser().enableUser(new User(code, lvlAc));
        request.getSession().setAttribute("message", "Se habilito al usuario");

        response.sendRedirect("admin.jsp");
//        request.getRequestDispatcher("/pages/admin-page/admin-main.jsp").forward(request, response);

    }

    public void loadFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isJson = false;
        try {
            System.out.println("ingresamos al servlet");
            Part filePart = request.getPart("JSONfile"); // obtiene la parte del archivo cargado en el input
            String fileName = filePart.getSubmittedFileName();
            isJson = fileName.toLowerCase().endsWith(".json");
            if (isJson) {
                InputStream fileContent = filePart.getInputStream(); // obtiene el InputStream del archivo
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, "UTF-8")); // crea un lector de BufferedReader
                StringBuilder stringBuilder = new StringBuilder(); // crea un StringBuilder para almacenar el contenido del archivo
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line); // agrega cada línea del archivo al StringBuilder
                }

                FileManager fileManager = new FileManager(stringBuilder.toString(), reader);
                fileManager.dataLoader();
            }

            request.getSession().setAttribute("message", "Se han cargado los datos correctamente");
            response.sendRedirect("admin.jsp");

        } catch (IOException | IllegalArgumentException | ServletException e) {
            System.out.println("error por " + e);
            e.printStackTrace(System.out);
            request.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + e.getMessage());
            response.sendRedirect("admin.jsp");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + ex.getMessage());
            response.sendRedirect("admin.jsp");
        }

    }

}
