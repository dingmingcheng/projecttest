package com.dmc.DesignPatterns.Factory;

public class NoodlesFactory implements FoodFactory {
    @Override
    public Food createFood() {
        Food food = new Noodles();
        return food;
    }
}
