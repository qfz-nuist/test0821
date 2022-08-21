package cn.itcast.bookstore.userTest.service;


import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.userTest.dao.UserDao;
import cn.itcast.bookstore.userTest.domain.User;

import java.sql.SQLException;

public class UserService {

    UserDao userDao = new UserDao();

    //注册功能
    public void regist(User user) throws SQLException, UserException {
        User u = userDao.findUsername(user.getUsername());

        if(u!=null) throw new UserException("用户名已被注册");
        u = userDao.findEmail(user.getEmail());
        if(u!=null) throw new UserException("邮箱已被注册");

        //如果邮箱和用户名都没有，则插入到数据库

        userDao.addUser(user);
    }

    //登录

    public User login(User user) throws SQLException, UserException {

        User user1 = userDao.findUsername(user.getUsername());
        if(user1==null) throw new UserException("用户名已被注册");

        user1 = userDao.login(user);

        if(user1==null) throw new UserException("用户名或密码错误");
        System.out.println(user1.isState());

        if(!user1.isState()) throw new UserException("用户尚未激活");
        return user1;
    }
}
