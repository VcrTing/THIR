package com.example.doing;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TYpedTest {

    @Test
    void cis() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long i = Long.valueOf("0");
        System.out.println(i);
        // Method method = Long.class.getMethod("valueOf", Object.class);
        Method method = Integer.class.getMethod("valueOf", String.class);
        Integer res = (Integer) method.invoke(null, "123");
        System.out.println(res);
    }

    @Test
    void math() {
        System.out.println("---------------");
        System.out.println(0%2);
        System.out.println(1%2);
        System.out.println(2%2);
        System.out.println(3%2);
        System.out.println(4%2);
        System.out.println("---------------");
        System.out.println(0%3);
        System.out.println(1%3);
        System.out.println(2%3);
        System.out.println(3%3);
        System.out.println(4%3);
        System.out.println("---------------");
        System.out.println(0%4);
        System.out.println(1%4);
        System.out.println(2%4);
        System.out.println(3%4);
        System.out.println(4%4);
        System.out.println(5%4);
    }
}
