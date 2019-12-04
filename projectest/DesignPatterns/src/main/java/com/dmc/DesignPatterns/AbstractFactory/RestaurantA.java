package com.dmc.DesignPatterns.AbstractFactory;

public class RestaurantA implements AbstractRestaurant {
    @Override
    public Food createRice() {
        Food food = new FriedRice(20, 5, "FriedRiceA");
        return food;
    }

    @Override
    public Food createNoodles() {
        Food food = new Noodles(30, 15, "NoodlesA");
        return food;
    }
}
