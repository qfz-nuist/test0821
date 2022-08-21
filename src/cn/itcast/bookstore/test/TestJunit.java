package cn.itcast.bookstore.test;

import cn.itcast.bookstore.orderTest.domain.Order;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestJunit {

    @Test
    public void test(){
        Date date = new Date(System.currentTimeMillis());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println(timestamp);

    }
    @Test
    public void mapTostring(){
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("alice",1);
        map.put("bob",2);
        map.put("charly",3);
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(map.toString());
    }
}
