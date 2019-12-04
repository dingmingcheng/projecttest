package com.dmc.DesignPatterns.adapter.obj;

public class RuleAdapter implements NewRules {

    private OldRules oldRules;

    public RuleAdapter(OldRules oldRules) {
        this.oldRules = oldRules;
    }

    @Override
    public void dontTalk() {
        oldRules.dontTalk();
    }

    @Override
    public void dontEat() {
        System.out.println("new rule : do not eat...!");
    }
}
