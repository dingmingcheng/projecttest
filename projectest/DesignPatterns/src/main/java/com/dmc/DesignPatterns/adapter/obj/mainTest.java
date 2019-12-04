package com.dmc.DesignPatterns.adapter.obj;

public class mainTest {
    public static void main(String[] args) {
        RuleAdapter adapter = new RuleAdapter(new OldRules());
        adapter.dontEat();
        adapter.dontTalk();
    }
}
