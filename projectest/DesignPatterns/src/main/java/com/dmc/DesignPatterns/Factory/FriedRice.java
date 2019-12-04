package com.dmc.DesignPatterns.Factory;

public class FriedRice extends Food {

    public FriedRice() {
        this(100, 20, "FriedRice");
    }

    public FriedRice(int price, int cost, String name) {
        super(price, cost, name);
    }

    @Override
    public String toString() {
        return "FriedRice{" +
                "price=" + super.getPrice() +
                ", cost=" + super.getCost() +
                ", name='" + super.getName() + '\'' +
                '}';
    }

    public static void main(String[] args) {
//        outLoop:
//        while (true) {
//            inLoop:
//            while (true) {
//                for (int i = 0; i < 10; i ++) {
//                    if (i == )
//                }
//            }
//        }
        Loop:
        while (true) {
            for (int i = 0; i < 10; i ++) {
                System.out.println(i);
                if (i == 6) {
                    break Loop;
                }
            }
        }
    }
}
