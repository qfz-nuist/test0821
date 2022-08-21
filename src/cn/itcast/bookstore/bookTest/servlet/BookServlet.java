package cn.itcast.bookstore.bookTest.servlet;

import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.bookstore.bookTest.service.BookService;
import cn.itcast.bookstore.user.service.UserException;
import sun.security.timestamp.HttpTimestamper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="BookServlet")
public class BookServlet {

    BookService bookService = new BookService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, UserException {
        List<Book> books = null;
        try{
            books = bookService.findALl();

        }catch (UserException e){
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/body.jsp";
        }

        request.setAttribute("books",books);
        return "f:/jsps/book/list.jsp";
    }


    public String bookDetail(HttpServletRequest request,HttpServletResponse response) throws SQLException, UserException {
        Book book = bookService.load(request.getParameter("bid"));

        request.setAttribute("book",book);

        return "f:/jsps/book/desc/jsp";
    }

    public String findByCategory(HttpServletRequest request,HttpServletResponse response) throws SQLException, UserException {
        List<Book> books = null;

        try {
            books = bookService.findByCategory(request.getParameter("cid"));
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/jsps/body.jsp";
        }

        request.setAttribute("books",books);
        return "f:/jsps/book/list.jsp";
    }
}
