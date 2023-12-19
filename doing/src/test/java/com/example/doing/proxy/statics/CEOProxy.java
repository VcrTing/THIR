package com.example.doing.proxy.statics;

public class CEOProxy implements ISEO {

    private ISEO ceo;

    public CEOProxy(ISEO ceo) { this.ceo = ceo; }


    @Override
    public void meet(String name) {

        System.out.println("秘书先见客户 name = " + name);

        if (name == "QIONG") {
            ceo.meet(name);
        }
        else {
            System.out.println("不是老板想见的客户");
        }
    }
}
