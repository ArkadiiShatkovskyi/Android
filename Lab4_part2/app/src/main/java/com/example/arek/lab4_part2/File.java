package com.example.arek.lab4_part2;

public class File {

    //Pet
    private String name;
    private String drink;

    private String livingPlace;

    //Wild
    private String animal;
    private String type;
    private String agresive;

    private boolean isWild;

    public File(){
        name="";
        drink="";
        livingPlace="";
        animal="";
        type="";
        agresive="";
        isWild=false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgresive() {
        return agresive;
    }

    public void setAgresive(String agresive) {
        this.agresive = agresive;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
        isWild = wild;
    }
}
