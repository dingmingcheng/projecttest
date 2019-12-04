package com.dmc.DesignPatterns.Multition;

public class MainTest {
    public static void main(String[] args) {
        for (int i = 0; i <= 5; i ++) {
            Multition obj = Multition.getInstance(2);
            System.out.println(obj.getIndex());
        }
    }
}
