package com.dmc.DesignPatterns.Factory;

public class FriedRiceFactory implements FoodFactory {
    @Override
    public Food createFood() {
        Food food = new FriedRice();
        return food;
    }
}
