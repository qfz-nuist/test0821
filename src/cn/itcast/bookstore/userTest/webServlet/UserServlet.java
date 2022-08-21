package cn.itcast.bookstore.userTest.webServlet;

import cn.itcast.bookstore.cart.daomain.Cart;
import cn.itcast.bookstore.user.damain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="userServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserService();
    public String regist(HttpServletRequest req, HttpServletResponse res) throws UserException {
        //toBean的主要作用是把req.getParameterMap()中的属性和User中的属性对应
        //值赋给User中对应的属性
        User user = CommonUtils.toBean(req.getParameterMap(),User.class);

        Map<String,String> errors = new HashMap<>();

        errors = checkOut(user,errors);

        if(errors.size()>1){
            req.setAttribute("errors",errors);
            req.setAttribute("user",user);
            return "f:/jsps/user/regist.jsp";
        }
        //效验
        try {
            //添加uid
            user.setUid(CommonUtils.uuid());
            user.setCode(CommonUtils.uuid() + CommonUtils.uuid());
            userService.regist(user);
        } catch (UserException e) {
            //保存异常信息
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("user", user);
            return "f:/jsps/user/regist.jsp";
        }


        //注册成功
        req.setAttribute("msg", "注册成功");
        //添加链接

//        List<String> link = new ArrayList<>();

        return "/jsps/msg.jsp";
    }

    //登录
    public String login(HttpServletRequest request,HttpServletResponse response){
        //封装表单数据
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);

//        创建一个map保存错误信息
        Map<String, String> errors = new HashMap<String, String>();


        errors = checkOut(user, errors);
        if (errors.size() > 1) {
            //setAttribute的参数意思：为”errors"属性赋值errors
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            return "f:/jsps/user/login.jsp";
        }
        //登录
        try {
            user = userService.login(user);
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("session_user", user);
            //给用户添加一辆购物车
            session.setAttribute("car",new Cart());
        } catch (UserException e) {
            //用户名密码错误
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("user", user);
            return "/jsps/user/login.jsp";
        }

        return "/jsps/main.jsp";
    }

    //校验用户名和密码
    public Map<String,String> checkOut(User user,Map<String,String> errors){
        String username = user.getUsername();

        if(username==null||username.trim().isEmpty()){
            errors.put("username","用户名不能为空！");
        }else if(username.length()<3||username.length()>10){
            errors.put("username","用户名长度必须在3-10之间！");
        }


        String password = user.getPassword();

        if(password==null||password.trim().isEmpty()){
            errors.put("password","密码不能为空！");
        }else if(password.length()<3||password.length()>10){
            errors.put("password","密码长度必须在3-10之间!");
        }

        String email = user.getEmail();

        if(email==null||email.trim().isEmpty()){
            errors.put("email","Email不能为空！");
        }else if(!email.matches("\\w+@\\w+\\.\\w+")){
            errors.put("email","Email格式错误！");
        }
        return errors;
    }

    //退出登录

    public String quit(HttpServletRequest request,HttpServletResponse response){

        request.getSession().invalidate();
        return "r:/index.jsp";
    }

}
