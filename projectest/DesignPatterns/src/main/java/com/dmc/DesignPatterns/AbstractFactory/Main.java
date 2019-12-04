package com.dmc.DesignPatterns.AbstractFactory;

public class Main {
    public static void main(String[] args) {
        RestaurantA A = new RestaurantA();
        RestaurantB B = new RestaurantB();

        Food AFriedRice = A.createRice();
        Food ANoodles = A.createNoodles();
        System.out.println(AFriedRice);
        System.out.println(ANoodles);

        Food BFriedRice = B.createRice();
        Food BNoodles = B.createNoodles();
        System.out.println(BFriedRice);
        System.out.println(BNoodles);
    }
}
