package com.example.skodaclickapp.model;

public class Car {
    private int id;
    private String type;
    private String color;
    private String spz;
    private float fuel;
    private String description;
    private int fuelCard;


    public Car(int id, String type, String color, String spz, float fuel, String description, int fuelCard) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.spz = spz;
        this.fuel = fuel;
        this.description = description;
        this.fuelCard = fuelCard;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFuelCard() {
        return fuelCard;
    }

    public void setFuelCard(int fuelCard) {
        this.fuelCard = fuelCard;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", spz='" + spz + '\'' +
                ", fuel=" + fuel +
                ", description='" + description + '\'' +
                ", fuelCard=" + fuelCard +
                '\n' + "\n";
    }

    public String[] toArray(){
        return new String[]{getId() + "", getType(), getColor(), getSpz() + "", getFuel() + "", getDescription(), getFuelCard() + ""};
    }
}
