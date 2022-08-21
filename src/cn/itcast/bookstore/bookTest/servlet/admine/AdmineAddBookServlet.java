package cn.itcast.bookstore.bookTest.servlet.admine;

import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.bookstore.bookTest.service.BookService;
import cn.itcast.bookstore.categoryTest.daomain.Category;
import cn.itcast.commons.CommonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet(name="AdmineAddBookServlet")
public class AdmineAddBookServlet {

    BookService bookService  = new BookService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html:charset=utf-8");

        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);

        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);

        book.setCid(category.getCid());

        bookService.addBook(book);

    }
}
