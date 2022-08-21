package cn.itcast.bookstore.bookTest.servlet.admine;

import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.bookstore.bookTest.service.BookService;
import cn.itcast.bookstore.categoryTest.daomain.Category;
import cn.itcast.bookstore.categoryTest.service.CategoryService;
import cn.itcast.bookstore.userTest.service.UserException;
import cn.itcast.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="AdmineBookServlet")
public class AdmineBookServlet {

    BookService bookService = new BookService();

    CategoryService categoryService = new CategoryService();

    public String findAllBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, UserException, cn.itcast.bookstore.user.service.UserException {
        List<Book> books = bookService.findALl();

        request.setAttribute("books",books);

        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String loadBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, cn.itcast.bookstore.user.service.UserException {
        Book book = bookService.load(request.getParameter("bid"));

        List<Category> categories = categoryService.findAll();

        request.setAttribute("book",book);

        request.setAttribute("categories",categories);

        return "f:/adminjsps/admin/book/desc.jsp";

    }

    public String delete(HttpServletRequest request,HttpServletResponse response) throws SQLException, cn.itcast.bookstore.user.service.UserException, UserException {
        bookService.delegateBook(request.getParameter("bid"));

        return findAllBook(request,response);
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, cn.itcast.bookstore.user.service.UserException, SQLException, UserException {

        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);

        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);

        book.setCid(category.getCid());

        bookService.edit(book);

        return findAllBook(request,response);
    }


    public String addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, cn.itcast.bookstore.user.service.UserException, SQLException, UserException {

        //封装表单数据
      Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);

        //上传图片,保存图片的地址

        book.setBid(CommonUtils.uuid());

        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);

        book.setCid(category.getCid());
        bookService.addBook(book);


        return findAllBook(request, response);
    }


    public String preAddBook(HttpServletRequest request,HttpServletResponse response){
        List<Category> categories = categoryService.findAll();

        request.setAttribute("categories",categories);

        return "f:/adminjsps/admin/book/add.jsp";
    }

}
