package com.dmc.DesignPatterns.all;

import java.util.List;

public abstract class Restaurant {

    protected List<Food> foodMenu;

    protected String name;

    public abstract void addFood();

    public abstract void delFood();

    public abstract void cook(Customer customer, Food food);

    public List<Food> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(List<Food> foodMenu) {
        this.foodMenu = foodMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
