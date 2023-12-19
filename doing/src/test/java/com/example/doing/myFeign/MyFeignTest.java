package com.example.doing.myFeign;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class MyFeignTest {
    /*
        Class<QFeignProduct> qfpc = QFeignProduct.class;
        System.out.println(qfpc);
        System.out.println(qfpc.getInterfaces());
        System.out.println("--------");
     */

    @Test
    void mf() throws InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        QFeign qf = new QFeign();

        QFeignProduct proxy = qf.genProxy(new QFeignProduct() {
            @Override
            public Object byId(Long id) {
                return id;
            }
        });

        // 调用 方法
        Object res = proxy.byId(1L);

        qf.uriBuyFunc(new QFeignProduct() {
                @Override
                public Object byId(Long id) {
                    return null;
                }
            },
            QFeignProduct.class,
            "byId"
        );
    }
}
