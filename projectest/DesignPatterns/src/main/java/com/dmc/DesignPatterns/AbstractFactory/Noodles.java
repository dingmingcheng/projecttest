package com.dmc.DesignPatterns.AbstractFactory;


public class Noodles extends Food {


    public Noodles() {
        this(50, 10, "Noodles");
    }

    public Noodles(int price, int cost, String name) {
        super(price, cost, name);
    }
    @Override
    public String toString() {
        return "Noodles{" +
                "price=" + super.getPrice() +
                ", cost=" + super.getCost() +
                ", name='" + super.getName() + '\'' +
                '}';
    }
}
