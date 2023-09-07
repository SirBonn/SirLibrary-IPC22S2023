/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggnc.sirlibrary.Services;

import ggnc.sirlibrary.Models.DataBase.BookDAOs.CategoriesDAO.SelectCategory;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.SelectBookList;
import ggnc.sirlibrary.Models.DataBase.LibraryDAOs.SelectLibrary;
import ggnc.sirlibrary.Models.DataBase.BookDAOs.SelectBook;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.InsertLoanReqDet;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.InsertLoanReq;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.SelectLoanReq;
import ggnc.sirlibrary.Models.DataBase.LoanRequestsDAOs.SelectLoanReqDetails;
import ggnc.sirlibrary.Models.Domain.Books.Book;
import ggnc.sirlibrary.Models.Domain.Books.BookList;
import ggnc.sirlibrary.Models.Domain.Books.Category;
import ggnc.sirlibrary.Models.Domain.Libraries.Library;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.LoanReqDetail;
import ggnc.sirlibrary.Models.Domain.Loans.Requests.LoanRequest;
import ggnc.sirlibrary.Models.Domain.Users.Customer;
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
public class CustomerServices {

    public void dataGetter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer user = (Customer) request.getSession().getAttribute("userLogged");

        List<Library> libraries = new SelectLibrary().getAllLibraries();
        request.setAttribute("libraries", libraries);
        List<Category> categories = new SelectCategory().getAllCategories();
        request.setAttribute("categories", categories);
        List<BookList> bookList = new SelectBookList().getBookLists();
        request.setAttribute("bookList", bookList);
        List<LoanReqDetail> pendingReqs = new SelectLoanReqDetails().searchLoanRequest(user.getCode());
        request.setAttribute("pendingReqs", pendingReqs);

    }

    public void createRequestLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int isbn = Integer.parseInt(request.getParameter("isbn"));
        int lib = Integer.parseInt(request.getParameter("library"));
        Book book = new SelectBook().SearchByCode(new Book(isbn));
        Library library = new SelectLibrary().SearchByCode(lib);

        LoanRequest initLoan = new LoanRequest(library, book);
        request.setAttribute("initLoan", initLoan);

        try {
            new InsertLoanReq().insertLoanDetail(initLoan);
            request.getSession().setAttribute("message", "Se ha enviado su solicitud");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "hubo un error al enviar su solicitud por el error " + ex.getMessage());
        }
        request.getRequestDispatcher("/pages/customer-page/customer-main.jsp").forward(request, response);

    }

    public void sendRequestLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = Integer.parseInt(request.getParameter("code"));
        LoanRequest initLoan = new SelectLoanReq().searchLoanRequest(new LoanRequest(code));
        int type = Integer.parseInt(request.getParameter("type"));
        Customer user = (Customer) request.getSession().getAttribute("userLogged");
        int retrievedDays = Integer.parseInt(request.getParameter("retrievedDays"));
        LoanReqDetail loanReqDetail = new LoanReqDetail(initLoan, user, type, retrievedDays);

        try {
            new InsertLoanReqDet().insertLoanReqDet(loanReqDetail);
            request.getSession().setAttribute("message", "Se ha enviado su solicitud");
            response.sendRedirect("customer.jsp");
        } catch (InvalidActionException ex) {
            request.getSession().setAttribute("errorMessage", "hubo un error al enviar su solicitud por el error " + ex.getMessage());
            response.sendRedirect("customer.jsp");
        }
    }

}
