package com.dmc.DesignPatterns.builder.scene1;

/**
 * 第一种应用场景，常用于复杂对象的构建，和抽象工厂区别，抽象工厂，一个函数生成一个对象，一个类生成一个对象
 */
public class mainTest {
    public static void main(String[] args) {
        BigHouseBuilder builder = new BigHouseBuilder();
        Workers workers = new Workers(builder);
        House house = workers.build();
        System.out.println(house.toString());
    }
}
