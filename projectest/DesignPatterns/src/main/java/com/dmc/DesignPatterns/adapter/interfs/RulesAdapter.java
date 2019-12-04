package com.dmc.DesignPatterns.adapter.interfs;

public class RulesAdapter extends AbstractRules {
    public void dontTalk() {
        System.out.println("do not talk...!");
    }

    public void dontEat() {
        System.out.println("do not eat...!");
    }
}
