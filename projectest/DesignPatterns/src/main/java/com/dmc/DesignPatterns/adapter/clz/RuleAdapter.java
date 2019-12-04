package com.dmc.DesignPatterns.adapter.clz;

public class RuleAdapter extends OldRules implements NewRules {

    @Override
    public void dontEat() {
        System.out.println("new rule : do not eat...!");
    }
}
