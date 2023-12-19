package com.example.doing.proxy.dymatic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ToolHandler implements InvocationHandler {

    public Object targetCEO;

    // public ToolHandler(Object tar) { this.targetCEO = tar; }
    public ToolHandler() { }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("工具类开始");
        Object res = null;
        if (method != null) {

            // 使用 反射，
            // 调用 目标类需要代理的 方法
            if (targetCEO != null) {
                res = method.invoke(
                        targetCEO,
                        args
                );
            }
            else {
                System.out.println("你没有提交要反射的类");
            }
        }
        System.out.println("工具类结束");
        return res;
    }
}
