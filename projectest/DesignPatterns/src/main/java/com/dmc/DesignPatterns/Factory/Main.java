package com.dmc.DesignPatterns.Factory;

public class Main {
    public static void main(String[] args) {
        FriedRiceFactory friedRiceFactory = new FriedRiceFactory();
        Food friedRice = friedRiceFactory.createFood();
        System.out.println(friedRice.toString());

        NoodlesFactory noodlesFactory = new NoodlesFactory();
        Food noodles = noodlesFactory.createFood();
        System.out.println(noodles.toString());
    }
}
