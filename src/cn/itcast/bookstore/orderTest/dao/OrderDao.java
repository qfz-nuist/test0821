package cn.itcast.bookstore.orderTest.dao;

import cn.itcast.bookstore.bookTest.daomain.Book;
import cn.itcast.bookstore.orderTest.domain.Order;
import cn.itcast.bookstore.orderTest.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    TxQueryRunner txQueryRunner = new TxQueryRunner();

    public void addOrder(Order order){
        String sql = "insert into orders values(?,?,?,?,?)";

        System.out.println(order.toString());

        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
        Object[] param = {order.getOid(),timestamp, order.getTotal(), order.getState(),
        order.getOwner().getUid(),order.getAddress()};

        try{
            txQueryRunner.update(sql,param);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void addOrderItem(List<OrderItem> orderItems){
        for(OrderItem orderItem:orderItems){
            String sql = "insert into orderitem values(?,?,?,?,?)";

            Object[] param = {orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),
            orderItem.getOrder().getOid(),orderItem.getBook().getBid()};

            try{
                txQueryRunner.update(sql,param);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }


    public List<Order> findAllOrder(String uid){
        String sql = "select * from orders where uid=?";
        try{
            List<Order> orders = (List<Order>) txQueryRunner.query(sql,new BeanHandler<Order>(Order.class));
            for(Order order:orders){
              loadOrderitem(order);
            }
            return orders;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private void loadOrderitem(Order order) throws SQLException {
        //
        String sql = "select * from orderitem i,book b where i.bid=b.bid and oid=?";
        List<Map<String,Object>> mapList = txQueryRunner.query(sql,new MapListHandler(),order.getOid());
        List<OrderItem> orderItems = toOrderItems(mapList);

        order.setOrderItems(orderItems);
    }

    private List<OrderItem> toOrderItems(List<Map<String,Object>> mapList){
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for(Map<String,Object> stringObjectMap:mapList){
            OrderItem orderItem = CommonUtils.toBean(stringObjectMap,OrderItem.class);
            Book book = CommonUtils.toBean(stringObjectMap, Book.class);
            orderItem.setBook(book);
            orderItems.add(orderItem);
        }
        return orderItems;
    }


    public Order findOrderByOid(String oid){
        String sql = "select * from orders where id=?";
        try{
            Order order = txQueryRunner.query(sql,new BeanHandler<Order>(Order.class));
            loadOrderitem(order);
            return order;
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }


    public int findOrderState(String oid){
        String sql = "select state from orders where oid=?";
        try{
            return (int)txQueryRunner.query(sql,new ScalarHandler(),oid);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void changeOrderState(String oid,int i){
        String sql = "update orders set state=? where oid=?";

        try{
            txQueryRunner.update(sql,i,oid);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public List<Order> findAllOrder() {
        String sql = "select *from orders ";
        try {
            List<Order> orders =  txQueryRunner.query(sql,new BeanListHandler<Order>(Order.class));
            for (Order order :orders) {
                loadOrderitem(order);
            }
            return orders;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Order> findorderByState(int i) {
        String sql = "select * from orders where state=?";
        try {
            List<Order> orders =  txQueryRunner.query(sql,new BeanListHandler<Order>(Order.class),(Number)i);
            for (Order order :orders) {
                loadOrderitem(order);
            }

            return orders;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
