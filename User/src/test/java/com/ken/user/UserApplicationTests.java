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

}
