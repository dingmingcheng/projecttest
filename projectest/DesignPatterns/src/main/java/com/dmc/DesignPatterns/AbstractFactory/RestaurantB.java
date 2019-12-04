package com.dmc.DesignPatterns.AbstractFactory;

public class RestaurantB implements AbstractRestaurant {
    @Override
    public Food createRice() {
        Food food = new FriedRice(59, 14, "FriedRiceB");
        return food;
    }

    @Override
    public Food createNoodles() {
        Food food = new Noodles(34, 21, "NoodlesB");
        return food;
    }
}
