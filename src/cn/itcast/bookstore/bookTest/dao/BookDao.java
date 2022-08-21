package cn.itcast.bookstore.bookTest.dao;

import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {

    TxQueryRunner txQueryRunner = new TxQueryRunner();
    //查询所有书籍
    public List<Book> findALl() throws SQLException {
        String sql = "select * from book order by cid";
        return txQueryRunner.query(sql,new BeanListHandler<Book>(Book.class));
    }

    //根据bookid查找书籍
    public Book load(String bid) throws SQLException {
        String sql = "select * from book where bid=?";
        return txQueryRunner.query(sql,new BeanHandler<Book>(Book.class));
    }

    public List<Book> findByCategory(String cid) throws SQLException {
        String sql = "select * from book where cid=?";
        return txQueryRunner.query(sql,new BeanListHandler<Book>(Book.class));
    }


    public int findBookCountById(String cid) throws SQLException {
        String sql = "select count(*) from book where cid=?";

        Number number = (Number) txQueryRunner.query(sql,new ScalarHandler(),cid);
        return number.intValue();
    }

    public void delegateBook(String bid) throws SQLException {
        String sql = "delete from book wehere bid=?";
        txQueryRunner.update(sql,bid);
    }

    public void edit(Book book) throws SQLException {
        String sql = "update book set bname=?,price=?,author=?,image=?,cid=? where bid=?";
        Object[] params = {
                book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCid(),book.getBname()};
        txQueryRunner.update(sql,params);

    }

    public void addBook(Book book){
        String sql = "insert into book values (?,?,?,?,?,?)";//bname=?, price=?,author=?, image=?, cid=? , bid=? ";
        Object[] params = {book.getBid(),book.getBname(), book.getPrice(),
                book.getAuthor(), book.getImage(),
                book.getCid() };
    }

}
