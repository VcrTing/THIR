package com.example.doing.basic;

public class ProproChildB extends Propro {

    ProproChildB() {
        super(5_000);
    }

    public void howOld() {
        System.out.println(super.age);
    }
}
