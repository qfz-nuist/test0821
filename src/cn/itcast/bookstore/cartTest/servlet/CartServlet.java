package cn.itcast.bookstore.cartTest.servlet;

import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.bookstore.bookTest.service.BookService;
import cn.itcast.bookstore.cartTest.daomain.CartItem;
import cn.itcast.bookstore.cartTest.daomain.Cart;
import cn.itcast.bookstore.userTest.service.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="CartServlet")
public class CartServlet {

    public String findAllCartItem(HttpServletRequest request, HttpServletResponse response){

        Cart cart =(Cart)request.getSession().getAttribute("car");
        cart.getAllCartItem();
        return "f:/jsps/cart/list.jsp";
    }

    public String delegate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("car");

        cart.delegate(request.getParameter("bid"));
        return "f:/jsps/cart/list.jsp";
    }


    public String clean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("car");
        cart.clean();
        return "f:/jsps/cart/list.jsp";
    }
    /*
      添加购物条目
       */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取

        Cart cart = (Cart) request.getSession().getAttribute("car");
        String bid = request.getParameter("bid");
        Book book = null;
        try {
            book = new BookService().load(bid);
        } catch (SQLException | cn.itcast.bookstore.user.service.UserException e) {
            throw new RuntimeException(e.getMessage());
        }
        CartItem item = new CartItem();
        int count = Integer.parseInt(request.getParameter("count"));
        item.setCount(count);
        item.setBook(book);
        cart.addCartItem(item);
        return "f:/jsps/cart/list.jsp";
    }


}
