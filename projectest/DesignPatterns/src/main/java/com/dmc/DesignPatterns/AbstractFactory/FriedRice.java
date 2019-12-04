package com.dmc.DesignPatterns.AbstractFactory;


public class FriedRice extends Food {

    public FriedRice() {
        this(100, 20, "FriedRice");
    }

    public FriedRice(int price, int cost, String name) {
        super(price, cost, name);
    }

    @Override
    public String toString() {
        return "FriedRice{" +
                "price=" + super.getPrice() +
                ", cost=" + super.getCost() +
                ", name='" + super.getName() + '\'' +
                '}';
    }
}
