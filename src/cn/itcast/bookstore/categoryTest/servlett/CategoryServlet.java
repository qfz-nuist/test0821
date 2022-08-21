package cn.itcast.bookstore.categoryTest.servlett;

import cn.itcast.bookstore.categoryTest.daomain.Category;
import cn.itcast.bookstore.categoryTest.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="CategoryServlet")
public class CategoryServlet extends BaseServlet {

    CategoryService categoryService = new CategoryService();

    public String findALlCategory(HttpServletRequest request, HttpServletResponse response){
        List<Category> categories = categoryService.findAll();

        request.setAttribute("categories",categories);

        return "f:/jsps/left.jsp";


    }

}
