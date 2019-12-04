package com.dmc.DesignPatterns.simpleFactory;

public abstract class Food {
    private int price;

    private int cost;

    private String name;

    public Food() {
    }

    public Food(int price, int cost, String name) {
        this.price = price;
        this.cost = cost;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "price=" + price +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
