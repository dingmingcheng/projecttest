package com.dmc.DesignPatterns.all;

public abstract class Student implements Customer{

    protected int studentName;

    protected int studentCardId;

    @Override
    public void eating() {
        System.out.println(this.studentName + " is eating, he thinks the food is too ugly to eat:(");
    }

    @Override
    public void chooseRes(Restaurant restaurant) {
        System.out.println(this.studentName + " is coming, he chooses the restaurant called " + restaurant.getName());
    }

    @Override
    public void chooseFood(Food food) {
        System.out.println(this.studentName + " chooses the food called " + "asd");
    }

    @Override
    public void pay() {
        System.out.println(this.studentName + " is paying :(");
    }
}
