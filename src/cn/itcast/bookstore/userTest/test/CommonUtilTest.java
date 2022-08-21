package cn.itcast.bookstore.userTest.test;

import cn.itcast.commons.CommonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonUtilTest {

    @Test
    public void testCommonUtil(){

        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("sex","男");
        map.put("age",23);
        CommonUtils.toBean(map,Person.class);
        Person p = new Person();
        System.out.println(p);
    }
}
