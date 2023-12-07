package com.example.stepone.decorator;

public class DanceSingRobot implements Robot {

    private SingRobot singRobot;

    public DanceSingRobot(SingRobot singRobot) {
        this.singRobot = singRobot;
    }

    @Override
    public void sing() {
        singRobot.sing();
    }

    public void dancing() {
        System.out.println("跳舞 机器人");
    }
}
