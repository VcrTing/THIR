package com.example.stepone.factory;


public abstract class SimpleFactoryProduct {

    // 创建 Product 工厂
    public static FacProduct gen(Integer typed) {
        if (typed == 1)
            return new FacProductA();
        return new FacProductB();
    }

    // 你还可以 拓展 创建 动漫 工厂
    // public static DONMAN gen(Integer typed) { }
}
