package com.dmc.DesignPatterns.simpleFactory;

public class Main {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        String foodName = "com.dmc.DesignPatterns.simpleFactory.FriedRice";
        Object food = chooseFood(foodName, foodFactory);
        System.out.println(food.toString());
    }

    public static Object chooseFood(String foodName, FoodFactory foodFactory) {
        Object food = null;
        try {
            food = foodFactory.getClz(foodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return food;
    }
}
