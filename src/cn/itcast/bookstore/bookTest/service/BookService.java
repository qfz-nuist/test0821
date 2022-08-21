package cn.itcast.bookstore.bookTest.service;

import cn.itcast.bookstore.bookTest.dao.BookDao;
import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.bookstore.user.service.UserException;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    BookDao bookDao = new BookDao();

    public List<Book> findALl() throws SQLException, UserException {
        List<Book> books = bookDao.findALl();
        if(books==null) throw new UserException("没有图书");
        return books;
    }


    public Book load(String bid) throws UserException, SQLException {
        Book book = bookDao.load(bid);
        if(book==null) throw new UserException("书籍已经下架");
        return book;
    }

    public List<Book> findByCategory(String cid) throws SQLException, UserException {
        List<Book> books = bookDao.findByCategory(cid);
        if(books==null) throw new UserException("没有这个类型的图书");
        return books;
    }

    public void delegateBook(String bid) throws SQLException {
        bookDao.delegateBook(bid);
    }

    public void edit(Book book) throws SQLException {
        bookDao.edit(book);
    }

    public void addBook(Book book){
        bookDao.addBook(book);
    }
}
