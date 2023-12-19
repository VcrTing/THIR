package com.example.doing.basic;

public class ProproChildA extends Propro {

    ProproChildA() {
        super(30);
    }

    public void howOld() {
        System.out.println(super.age);
    }
}
