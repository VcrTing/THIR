package com.example.doing.myFeign;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

class QFeignHandler implements InvocationHandler {

    // Object tar;
    // public QFeignHandler(Object tar) { this.tar = tar; }
    public QFeignHandler() { }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法被调用");
        if (method != null) {
            // return method.invoke(tar, args);
        }
        return null;
    }
}

public class QFeign {

    String serviceNameName = "name";

    Class<?> requestMappingClazz = RequestMapping.class;
    // 执行 代理 方法


    // 生成代理类
    public <T> T genProxy(T clazz) throws InstantiationException, IllegalAccessException {
        InvocationHandler handler = new QFeignHandler();
        return (T) Proxy.newProxyInstance(
                clazz.getClass().getClassLoader(),
                clazz.getClass().getInterfaces(),
                handler
        );
    }

    public <T> String uriBuyFunc(T clazz, Class<T> ifc, String methodName) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        // 生成代理类
        T src = genProxy(clazz);

        // 获取 服务器 名称
        Field f = ifc.getDeclaredField(serviceNameName);

        // 获取 服务器 值
        String serverName = f.get(src).toString();
        System.out.println(serverName);

        // 获取 方法
        List<Method> ms = Arrays.asList(QFeignProduct.class.getMethods());
        for (Method m: ms) {
            if (m.getName().equals(methodName)) {

                Annotation[] as = m.getAnnotations();
                Arrays.stream(as).forEach(System.out::println);
            }
        }

        // 获取 RequestMapping 注解
        // RequestMapping rm = method.getAnnotation(requestMappingClazz);


        // 获取 方法 里的 参数

        // 拼接 url
        String url = "https://" + serverName + "";


        return "";
    }

}