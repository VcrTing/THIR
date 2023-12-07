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
}
