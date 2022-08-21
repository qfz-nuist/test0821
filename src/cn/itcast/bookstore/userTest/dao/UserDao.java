package cn.itcast.bookstore.userTest.dao;

import cn.itcast.bookstore.userTest.domain.User;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {

    private TxQueryRunner txQueryRunner = new TxQueryRunner();

    //验证名字是否和数据库中的名字一致
    public User findUsername(String username) throws SQLException {

        String sql = "select * from tb_user where name =?";
        return txQueryRunner.query(sql,new BeanHandler<User>(User.class),username);

    }
    //和验证用户名一样，这里通过验证email
    public User findEmail(String email) throws SQLException {
        String sql = "select * from tb_user where email=?";
        return txQueryRunner.query(sql,new BeanHandler<User>(User.class),email);
    }


    //如果两者都不存在，则把用户添加进数据库

    public void addUser(User user) throws SQLException {
        String sql = "insert into tb_user values(?,?,?,?,?,?)";

        Object[] param = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.isState()};
        txQueryRunner.update(sql,param);
    }

    //登录

    public User login(User user) throws SQLException {
        String sql = "select * from tb_user where username=? and password =?";
        Object[] param = {user.getUsername(),user.getPassword()};

        return txQueryRunner.query(sql,new BeanHandler<User>(User.class),param);
    }
}
