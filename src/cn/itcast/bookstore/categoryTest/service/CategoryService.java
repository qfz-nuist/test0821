package cn.itcast.bookstore.categoryTest.service;

import cn.itcast.bookstore.bookTest.dao.BookDao;
import cn.itcast.bookstore.categoryTest.dao.CategoryDao;
import cn.itcast.bookstore.categoryTest.daomain.Category;
import cn.itcast.bookstore.user.service.UserException;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    CategoryDao categoryDao = new CategoryDao();

    BookDao bookDao = new BookDao();

    public List<Category> findAll(){

        return categoryDao.findAll();

    }

    public void delegateCategoryByCid(String cid) throws SQLException, UserException {
        int count = bookDao.findBookCountById(cid);

        if(count < 0 ) throw new UserException("该分类下还没有图书不能删除");

        categoryDao.deleCategoryById(cid);
    }


    public Category editCategory(String cid){
        return categoryDao.load(cid);
    }

    public void addCategory(Category category){
        categoryDao.addCategory(category);
    }
}
