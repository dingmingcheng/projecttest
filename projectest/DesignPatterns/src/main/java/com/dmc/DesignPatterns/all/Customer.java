package com.dmc.DesignPatterns.all;

/**
 * 假设有这么一个场景，学校新开了一家A食堂，其中食堂的3楼有着两家餐馆，到了饭点，我们学生要去点餐，如何将这一过程抽象成一个对象？如何实现这一行为？如何又能保证
 * 代码的可维护性和扩展性呢？
 */
public interface Customer {

    void eating();

    void chooseRes(Restaurant restaurant);

    void chooseFood(Food food);

    void pay();
}
