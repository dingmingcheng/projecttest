package com.dmc.DesignPatterns.builder.scene1;

public class House {
    private Integer height;

    private Integer width;

    private Integer length;

    private Integer price;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" +
                "height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", price=" + price +
                '}';
    }
}
