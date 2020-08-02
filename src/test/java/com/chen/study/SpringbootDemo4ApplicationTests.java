package com.chen.study;

import com.chen.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
// @RunWith(SpringRunner.class)
public class SpringbootDemo4ApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Test
    public void testIoc(){
        User user1 = (User) applicationContext.getBean("user1");
        System.out.println(user1);
    }

}
