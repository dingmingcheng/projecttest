package com.dmc.DesignPatterns.all;

public abstract class Food {
    protected int price;

    protected int cost;


    public Food(int price, int cost) {
        this.price = price;
        this.cost = cost;
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

}
