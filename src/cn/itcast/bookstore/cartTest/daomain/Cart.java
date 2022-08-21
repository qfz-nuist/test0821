package cn.itcast.bookstore.cartTest.daomain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<String,CartItem> map = new LinkedHashMap<>();

    public void addCartItem(CartItem cartItem){
        String bid = cartItem.getBook().getBid();

        if(map.containsKey(bid)){
            CartItem _cartItem = map.get(bid);

            System.out.println(_cartItem.getCount());

            System.out.println(cartItem.getCount());

            _cartItem.setCount(_cartItem.getCount()+cartItem.getCount());

            map.put(bid,_cartItem);
        }else{
            map.put(bid,cartItem);
        }
    }

    public void clean(){
        map.clear();
    }

    public void delegate(String bid){
        map.remove(bid);
    }

    public double getTotal(){
        BigDecimal bigDecimaltotal = new BigDecimal(0);

        for(CartItem cartItem:map.values()){
            BigDecimal subTotal = new BigDecimal(cartItem.getCartItemTotal());
            bigDecimaltotal = bigDecimaltotal.add(subTotal);
        }

        return bigDecimaltotal.doubleValue();
    }
    //获取所有条目
    public Collection<CartItem> getAllCartItem(){
        return map.values();
    }
}
