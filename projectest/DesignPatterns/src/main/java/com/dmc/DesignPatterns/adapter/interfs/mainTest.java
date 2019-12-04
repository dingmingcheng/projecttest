package com.dmc.DesignPatterns.adapter.interfs;

public class mainTest {
    public static void main(String[] args) {
        RulesAdapter adapter = new RulesAdapter();
        adapter.dontEat();
        adapter.dontTalk();
    }
}
