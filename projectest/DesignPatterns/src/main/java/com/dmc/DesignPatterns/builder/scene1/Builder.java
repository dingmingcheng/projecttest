package com.dmc.DesignPatterns.builder.scene1;

public interface Builder {
    void buildLength();

    void buildWidth();

    void buildHeight();

    void buildPrice();

    House getHouse();
}
