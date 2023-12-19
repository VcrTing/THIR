package com.example.doing.proxy;

import com.example.doing.proxy.dymatic.DyCEO;
import com.example.doing.proxy.dymatic.ToolHandler;
import com.example.doing.proxy.dymatic.DyISEO;
import com.example.doing.proxy.statics.CEO;
import com.example.doing.proxy.statics.CEOProxy;
import com.example.doing.proxy.statics.ISEO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    /**
    * 静态代理
    * @params
    * @return
    */
    @Test
    void staticProxy() {

        // 先要 有 老板
        ISEO boss = new CEO();
        // 才能 有 秘书
        ISEO mishu = new CEOProxy(boss);

        // 然后 秘书 先 会面
        // 秘书 是 老板的 静态代理
        mishu.meet("QIONG");

        // 如果 有 第二个老板，是股东之一，
        // 那么 只能 再 new 一个秘书，给这个 股东 做 代理
        // 这样造成了 秘书 的 重复创造
    }

    /**
    * 动态代理
    * @params
    * @return
    */
    DyISEO doingDynamicProxy(DyISEO anyBoss) {
        // 新建 秘书工具类，服务 A 老板
        InvocationHandler mishuA = new ToolHandler(
                // anyBoss
        );
        // 新建 A 秘书
        return (DyISEO) Proxy.newProxyInstance(
                anyBoss.getClass().getClassLoader(),
                anyBoss.getClass().getInterfaces(),
                mishuA
        );
    }

    @Test
    void dynamicProxy() {

        // 新建 A 老板
        DyISEO bossA = new DyCEO("A");

        // 新建 B 老板
        DyISEO bossB = new DyCEO("B");


        // 全职秘书，安排 A boss 见面
        doingDynamicProxy(bossA).meet();

        // 全职秘书，安排 B boss 见面
        doingDynamicProxy(bossB).meet();
    }
}
