package com.dmc.DesignPatterns.adapter.clz;

public class mainTest {
    public static void main(String[] args) {
        RuleAdapter adapter = new RuleAdapter();
        adapter.dontEat();
        adapter.dontTalk();
    }
}
