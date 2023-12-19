package com.example.doing.proxy.statics;

public class CEO implements ISEO{

    @Override
    public void meet(String kehu) {
        System.out.println("CEO 见客户，签约百万项目，客人 = " + kehu);
    }
}
