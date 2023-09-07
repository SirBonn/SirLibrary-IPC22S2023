/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Services;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.SelectBookList;
import ggnc.sirlibrary.Models.DataBase.LoanDAOs.*;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.*;
import ggnc.sirlibrary.Models.DataBase.UserDAOs.InsertUser;
import ggnc.sirlibrary.Models.Domain.Books.BookList;
import ggnc.sirlibrary.Models.Domain.Loans.*;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.*;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
import ggnc.sirlibrary.Models.Domain.Users.Dealer;
import ggnc.sirlibrary.Models.Domain.Users.Receptionist;
import ggnc.sirlibrary.Models.Domain.Users.User;
import ggnc.sirlibrary.Utils.InvalidActionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author sirbon
 */
public class ReceptionistServices {

    public void dataGetter(HttpServletRequest request, HttpServletResponse response) {
        Receptionist user = (Receptionist) request.getSession().getAttribute("userLogged");

        List<BookList> booksLib = new SelectBookList().getBookSpecificList(user.getLibrary().getCode());
        request.setAttribute("bookList", booksLib);
        List<LoanReqDetail> pendingReqs = new SelectLoanReqDetails().getSpecificReqs(user.getLibrary().getCode(), 0);
        request.setAttribute("pendingReqs", pendingReqs);
        List<LoanReqDetail> aceptedReqs = new SelectLoanReqDetails().getSpecificReqs(user.getLibrary().getCode(), 1);
        request.setAttribute("aceptedReqs", aceptedReqs);
        List<LoanReqDetail> rejectedReqs = new SelectLoanReqDetails().getSpecificReqs(user.getLibrary().getCode(), 2);
        request.setAttribute("rejectedReqs", rejectedReqs);
        List<LoanDetail> loanDetails = new SelectLoanDetails().getAllLoans(user.getLibrary().getCode());
        request.setAttribute("loanDetails", loanDetails);
        List<LoanDetail> activeLoans = new SelectLoanDetails().getSpecificReqs(user.getLibrary().getCode(), 1);
        request.setAttribute("activeLoans", activeLoans);
        List<LoanDetail> retrievedLoans = new SelectLoanDetails().getSpecificReqs(user.getLibrary().getCode(), 2);
        request.setAttribute("retrievedLoans", retrievedLoans);

        // revisar loanded books
        //logica para disminuir cantidad de libros segun libros prestados
    }

    public void aceptReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reqCode = Integer.parseInt(request.getParameter("code"));
        Receptionist user = (Receptionist) request.getSession().getAttribute("userLogged");

        try {
            new UpdateLoanReq().updateLoanReqState(1, reqCode);
            new UpdateLoanReqDetails().setUserReceiver(user.getCode(), reqCode);
            createLoan(reqCode);
            request.getSession().setAttribute("message", "Se ha aceptado la solicitud, el prestamo se ha efectuado");

        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "Ha ocurrido un error con la solicitud: " + ex.getMessage());

        }

        response.sendRedirect("receptionist.jsp");

        //crear loan con datos de loanreqdetail
        //enviar loan n loandetail a bd
        //cargar a usuario
    }

    public void rejectReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reqCode = Integer.parseInt(request.getParameter("code"));
        Receptionist user = (Receptionist) request.getSession().getAttribute("userLogged");

        try {
            new UpdateLoanReq().updateLoanReqState(2, reqCode);
            new UpdateLoanReqDetails().setUserReceiver(user.getCode(), reqCode);
            request.getSession().setAttribute("errorMessage", "Se ha rechazado la solicitud");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "Ha ocurrido un error con la solicitud: " + ex.getMessage());
        }
        response.sendRedirect("receptionist.jsp");
    }

    private void createLoan(int codeRequest) throws InvalidActionException {
        LoanReqDetail lrd = new SelectLoanReqDetails().getLoanReqDetail(codeRequest);

        Loan loan = new Loan(lrd.getLoanReq().getCode(), lrd.getLoanReq().getLibrary(), lrd.getLoanReq().getBook());
        LoanDetail loanDetail = new LoanDetail(loan, lrd.getUser(), lrd.getRetrievedDays());

        new InsertLoan().inserLoan(loan);
        new InsertLoanDetail().insertLoanDetail(loanDetail);

    }

    public void finishLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int loanCode = Integer.parseInt(request.getParameter("code"));
        Receptionist user = (Receptionist) request.getSession().getAttribute("userLogged");

        try {
            new UpdateLoan().updateLoanState(2, loanCode);
            new UpdateLoanDetails().setUserFinisherr(user.getCode(), loanCode);
            request.getSession().setAttribute("errorMessage", "Se ha finalizado el prestamo");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "Ha ocurrido un error con la solicitud: " + ex.getMessage());
        }
        response.sendRedirect("receptionist.jsp");

    }

    public void insertNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String forename = request.getParameter("forename");
        String username = request.getParameter("username");
        String emailAddress = request.getParameter("emailAddress");
        String userpassword = request.getParameter("userpassword");

        User user = new Customer(emailAddress, forename, username, userpassword);

        try {
            new InsertUser().insertUser(user);
            System.out.println("se agrego al usuario " + user.toString());
            request.getSession().setAttribute("message", "Se ha agregado el nuevo usuario: " + username);
            response.sendRedirect("admin.jsp");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "hubo un error al agregar el libro por el error " + ex.getMessage());
            response.sendRedirect("admin.jsp");
        }

    }

}
