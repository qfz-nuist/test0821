package cn.itcast.bookstore.categoryTest.servlett.admine;

import cn.itcast.bookstore.categoryTest.daomain.Category;
import cn.itcast.bookstore.categoryTest.service.CategoryService;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="AdmineCategoryServlet")
public class AdmineCategory extends BaseServlet {

    CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response){

        List<Category> categories = categoryService.findAll();

        request.setAttribute("categories",categories);

        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String delegateCategory(HttpServletRequest request,HttpServletResponse response){
        try{
            categoryService.delegateCategoryByCid(request.getParameter("cid"));
            return findAll(request,response);
        } catch (UserException | SQLException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
    }
}
