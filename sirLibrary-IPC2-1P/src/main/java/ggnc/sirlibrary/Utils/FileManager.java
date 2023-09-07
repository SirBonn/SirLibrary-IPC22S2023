/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO.InsertCategory;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO.SelectCategory;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.InsertBook;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.InsertToList;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.SelectBook;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.InsertLibrary;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.SelectLibrary;
import ggnc.sirlibrary.Models.DataBase.LoanDAOs.InsertLoan;
import ggnc.sirlibrary.Models.DataBase.LoanDAOs.InsertLoanDetail;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.InsertLoanReq;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.InsertLoanReqDet;
import ggnc.sirlibrary.Models.DataBase.UserDAOs.InsertUser;
import ggnc.sirlibrary.Models.DataBase.UserDAOs.SelectUser;
import ggnc.sirlibrary.Models.Domain.Books.*;
import ggnc.sirlibrary.Models.Domain.Books.BookList;
import ggnc.sirlibrary.Models.Domain.Books.Category;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Models.Domain.Loans.*;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.*;

import ggnc.sirlibrary.Models.Domain.Users.Admin;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
import ggnc.sirlibrary.Models.Domain.Users.Dealer;
import ggnc.sirlibrary.Models.Domain.Users.Receptionist;
import ggnc.sirlibrary.Models.Domain.Users.User;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FileManager {

    private ObjectMapper objectMapper = new ObjectMapper();
    private InputStream fileContent;
    private BufferedReader bufferedReader;
    private JsonNode rootNode;
    private String json;

    public FileManager(String fileContent, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.json = fileContent;
        initJasonRootNode();

    }

    public void dataLoader() throws InvalidActionException {

        try {
            loadCategories();
            loadBooks();
            loadLibraries();
            loadUsers();
            loadLoanRequests();
            loadLoans();
            
        } catch (InvalidActionException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new InvalidActionException("Hubo un error cargando archivos: " + ex.getMessage());
        }

    }

    private void initJasonRootNode() {

        try {

            this.rootNode = objectMapper.readTree(json);
            System.out.println("exito ocn rootNode");
        } catch (JsonProcessingException ex) {

            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void loadUsers() throws InvalidActionException {

        InsertUser insertUser = new InsertUser();
        ArrayList<Admin> admins = new ArrayList<>();
        JsonNode usuariosJsonNode = rootNode.get("admin");

        for (JsonNode usuarioJsonNode : usuariosJsonNode) {

            int codigo = usuarioJsonNode.get("codigo").asInt();
            String nombre = usuarioJsonNode.get("nombre").asText();
            String username = usuarioJsonNode.get("username").asText();
            String password = usuarioJsonNode.get("password").asText();
            String email = usuarioJsonNode.get("email").asText();
            Admin admin = new Admin(codigo, email, nombre, username, password);
            admins.add(admin);

        }
        for (Admin admin : admins) {

            insertUser.insertUser(admin);
        }

        ArrayList<Receptionist> receptionists = new ArrayList<>();
        usuariosJsonNode = rootNode.get("usuariosRecepcion");

        for (JsonNode usuarioJsonNode : usuariosJsonNode) {

            int codigo = usuarioJsonNode.get("codigo").asInt();
            String nombre = usuarioJsonNode.get("nombre").asText();
            String username = usuarioJsonNode.get("username").asText();
            String password = usuarioJsonNode.get("password").asText();
            String email = usuarioJsonNode.get("email").asText();
            int lib = usuarioJsonNode.get("biblioteca").asInt();
            Library library = new SelectLibrary().SearchByCode(lib);
            Receptionist rec = new Receptionist(true, library, codigo, email, nombre, username, password);
            receptionists.add(rec);

        }
        for (Receptionist receptionist : receptionists) {

            insertUser.insertUser(receptionist);
        }

        ArrayList<Customer> customers = new ArrayList<>();
        usuariosJsonNode = rootNode.get("usuarios");

        for (JsonNode usuarioJsonNode : usuariosJsonNode) {

            int codigo = usuarioJsonNode.get("codigo").asInt();
            String nombre = usuarioJsonNode.get("nombre").asText();
            String username = usuarioJsonNode.get("username").asText();
            String password = usuarioJsonNode.get("password").asText();
            String email = usuarioJsonNode.get("email").asText();
            double amount = usuarioJsonNode.get("saldoInicial").asInt();
            Customer customer = new Customer(true, amount, codigo, email, nombre, username, password);
            customers.add(customer);

        }
        for (Customer cs : customers) {

            insertUser.insertUser(cs);
        }

        ArrayList<Dealer> dealers = new ArrayList<>();
        usuariosJsonNode = rootNode.get("usuarios");

        for (JsonNode usuarioJsonNode : usuariosJsonNode) {

            int codigo = usuarioJsonNode.get("codigo").asInt();
            String nombre = usuarioJsonNode.get("nombre").asText();
            String username = usuarioJsonNode.get("username").asText();
            String password = usuarioJsonNode.get("password").asText();
            String email = usuarioJsonNode.get("email").asText();
            Dealer dealer = new Dealer(codigo, email, nombre, username, password);
            dealers.add(dealer);
        }
        for (Dealer d : dealers) {

            insertUser.insertUser(d);
        }

    }

    private void loadCategories() throws InvalidActionException {
        InsertCategory insertCategory = new InsertCategory();

        ArrayList<Category> cats = new ArrayList<>();
        JsonNode catJsonNode = rootNode.get("categorias");

        for (JsonNode jsonNode : catJsonNode) {

            int codigo = jsonNode.get("codigo").asInt();
            String nombre = jsonNode.get("nombre").asText();
            String desc = jsonNode.get("descripcion").asText();
            Category cat = new Category(codigo, nombre, desc);
            cats.add(cat);
        }
        for (Category c : cats) {

            insertCategory.insertCategory(c);
        }

    }

    private void loadBooks() throws InvalidActionException {
        InsertBook insertBook = new InsertBook();

        ArrayList<Book> books = new ArrayList<>();
        JsonNode booksJsonNode = rootNode.get("libros");

        for (JsonNode jsonNode : booksJsonNode) {

            int isbn = jsonNode.get("isbn").asInt();
            String tittle = jsonNode.get("nombre").asText();
            String cost = jsonNode.get("costo").asText();
            int cat = jsonNode.get("categoria").asInt();
            Category category = new SelectCategory().SearchByCode(cat);
            String auth = jsonNode.get("autor").asText();
            Book book = new Book(isbn, tittle, cost, auth, category);
            books.add(book);
        }
        for (Book b : books) {

            insertBook.insertBook(b);

        }

    }

    private void loadLibraries() throws InvalidActionException {
        InsertLibrary insertLibraries = new InsertLibrary();
        InsertToList insertToList = new InsertToList();
        ArrayList<Library> libs = new ArrayList<>();
        ArrayList<BookList> bookLists = new ArrayList<>();

        JsonNode libsJsonNode = rootNode.get("bibliotecas");

        for (JsonNode jsonNode : libsJsonNode) {

            int code = jsonNode.get("codigo").asInt();
            String alias = jsonNode.get("nombre").asText();
            String direction = jsonNode.get("direccion").asText();
            Library library = new Library(code, alias, direction);
            libs.add(library);
            JsonNode bookListJsonNode = jsonNode.get("libros");
            for (JsonNode blNode : bookListJsonNode) {
                int isbn = blNode.get("isbn").asInt();
                int units = blNode.get("existencias").asInt();

                BookList bl = new BookList(new Book(isbn), library, units);
                bookLists.add(bl);
            }

        }

        for (Library l : libs) {

            insertLibraries.insertLibrary(l);
        }
        for (BookList bookList : bookLists) {
            insertToList.addToList(bookList);
        }

    }

    private void loadLoanRequests() throws InvalidActionException {

        InsertLoanReq insertLoanReq = new InsertLoanReq();
        InsertLoanReqDet insertLoanReqDet = new InsertLoanReqDet();
        SelectUser userSelectUser = new SelectUser();
        ArrayList<LoanRequest> loanRequests = new ArrayList<>();
        ArrayList<LoanReqDetail> loanReqDetails = new ArrayList<>();

        JsonNode loansReqsJsonNode = rootNode.get("solicitudesPrestamo");

        for (JsonNode jsonNode : loansReqsJsonNode) {

            int code = jsonNode.get("codigo").asInt();
            int library = jsonNode.get("biblioteca").asInt();
            int recept = jsonNode.get("recepcionista").asInt();
            int user = jsonNode.get("usuario").asInt();
            int isbn = jsonNode.get("isbn").asInt();
            String fecha = jsonNode.get("fecha").asText();
            int estado = 0;
            if (jsonNode.get("estado").asText().equals("PENDIENTE")) {
                estado = 0;
            } else if (jsonNode.get("estado").asText().equals("FINALIZADA")) {
                estado = 1;
            }
            int type = 1;
            if (jsonNode.get("tipoEntrega").asText().equals("RECEPCION")) {
                type = 1;
            } else if (jsonNode.get("tipoEntrega").asText().equals("DOMICILIO")) {
                type = 2;
            }
            int dealerCode = jsonNode.get("transportista").asInt();

            Library lib = new SelectLibrary().SearchByCode(library);
            Customer customer = userSelectUser.getCustomer(new User(user));
            Receptionist receptionist = null;
            if (recept != 0) {
                receptionist = userSelectUser.getReceptionist(new User(recept));
            }
            Book book = new SelectBook().SearchByCode(new Book(isbn));
            Dealer dealer = null;
            if (dealerCode != 0) {
                dealer = userSelectUser.getDealerr(new User(dealerCode));
            }

            LoanRequest lr = new LoanRequest(code, lib, book, estado, fecha);
            LoanReqDetail lrd = new LoanReqDetail(lr, customer, receptionist, dealer, type);

            loanRequests.add(lr);
            loanReqDetails.add(lrd);
        }

        for (LoanRequest loanRequest : loanRequests) {
            insertLoanReq.insertLoanDetail(loanRequest);
        }
        for (LoanReqDetail loanReqDetail : loanReqDetails) {
            insertLoanReqDet.insertLoanReqDet(loanReqDetail);
        }

    }

    private void loadLoans() throws InvalidActionException {

        InsertLoan insertLoan = new InsertLoan();
        InsertLoanDetail insertLoanDetail = new InsertLoanDetail();
        SelectUser userSelectUser = new SelectUser();
        ArrayList<Loan> loans = new ArrayList<>();
        ArrayList<LoanDetail> loanDetails = new ArrayList<>();

        JsonNode loansJsonNode = rootNode.get("prestamos");

        for (JsonNode jsonNode : loansJsonNode) {

            int code = jsonNode.get("codigo").asInt();
            int library = jsonNode.get("biblioteca").asInt();
            int recept = jsonNode.get("recepcionista").asInt();
            int user = jsonNode.get("usuario").asInt();
            int isbn = jsonNode.get("isbn").asInt();
            String fecha = jsonNode.get("fecha").asText();
            int estado = 0;
            if (jsonNode.get("estado").asText().equals("ACTIVO")) {
                estado = 0;
            } else if (jsonNode.get("estado").asText().equals("FINALIZAD0")) {
                estado = 1;
            }
            double fee = jsonNode.get("multa").asDouble();

            Library lib = new SelectLibrary().SearchByCode(library);
            Customer customer = userSelectUser.getCustomer(new User(user));
            Receptionist receptionist = null;
            if (recept != 0) {
                receptionist = userSelectUser.getReceptionist(new User(recept));
            }
            Book book = new SelectBook().SearchByCode(new Book(isbn));

            Loan l = new Loan(code, lib, book, estado, fecha);
            LoanDetail ld = new LoanDetail(l, customer, receptionist, fee);

            loans.add(l);
            loanDetails.add(ld);
        }

        for (Loan loan : loans) {
            insertLoan.inserLoan(loan);
        }
        for (LoanDetail loanDetail : loanDetails) {
            insertLoanDetail.insertLoanDetail(loanDetail);
        }

    }

}
