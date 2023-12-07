package com.example.stepone;

import com.example.stepone.decorator.DanceSingRobot;
import com.example.stepone.decorator.SingRobot;
import com.example.stepone.factory.FacProduct;
import com.example.stepone.factory.SimpleFactoryProduct;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class FactoryAllTest {
    @Test
    public void factoryTest() {
        /**
         *  优点：用户不需要 创建，你只需要 传 typed 就行，工厂帮你创建，只管使用
         * 缺点：不够灵活，这里只提供了 ProductA, ProductB，如果要 CDEF 就炸了，要写很多类型的创建
         * @params
         * @return
         */
        FacProduct fp = SimpleFactoryProduct.gen(1);
        // FacProduct 定义了 工厂里应该有 哪些方法
        // SimpleFactoryProduct 定义了如何 生成 FacProduct 的 子类
        // 用户看不见 FacProduct 的 子类，只管使用 FacProduct 类 里面的 方法，
        // 看不见 就是 闭，开闭原则的 闭
        fp.say();


        // Collection 类 使用了

        String dt = new SimpleDateFormat("yyyy").format(new Date());
    }

    @Test
    public void decoratorTest() {
        SingRobot singRobot = new SingRobot();
        DanceSingRobot danceSingRobot = new DanceSingRobot(singRobot);

        danceSingRobot.sing();
        danceSingRobot.dancing();

        // java io 流 使用了
    }
}
