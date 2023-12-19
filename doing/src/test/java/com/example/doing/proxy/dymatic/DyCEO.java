package com.example.doing.proxy.dymatic;

public class DyCEO implements DyISEO {

    String name;

    public DyCEO(String name) { this.name = name; }

    public void meet() {
        System.out.println("ECO " + name + " 遇见会议。" );
    }
}
