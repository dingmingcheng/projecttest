package com.dmc.DesignPatterns.builder.scene1;

public class BigHouseBuilder implements Builder {
    private House house = new House();
    @Override
    public void buildLength() {
        house.setLength(100);
    }

    @Override
    public void buildWidth() {
        house.setWidth(100);
    }

    @Override
    public void buildHeight() {
        house.setHeight(100);
    }

    @Override
    public void buildPrice() {
        house.setPrice(1000000);
    }

    @Override
    public House getHouse() {
        return house;
    }
}
