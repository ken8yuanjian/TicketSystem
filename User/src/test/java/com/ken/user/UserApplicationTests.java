package com.ken.user;

import com.ken.user.property.CustomProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    CustomProperty customProperty;
    @Test
    void testConfiguration()
    {
        System.out.println(customProperty.toString());
    }

    @Test
    void test_try_finally()
    {
        int x = 0;
        try {
            if (0 == x)
                return ;
        } finally { //上一句的return，这里也会执行，保证一定会执行
            x++;
        }
        x =0;
    }

}
