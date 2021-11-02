package Entities;

import Entities.Enums.Size;

public class Clothes extends Product {
    private Size size;
    private String color;

    public Clothes(String name, String brand, double price, Size size, String color) {
        super(name, brand, price);
        this.size = size;
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", super.toString(), this.size, this.color);
    }
}
