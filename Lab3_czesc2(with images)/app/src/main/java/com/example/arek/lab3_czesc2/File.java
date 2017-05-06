package com.example.arek.lab3_czesc2;

public class File {

    private String name;
    private String animal;
    private String country;
    private int memory;
    private String color;
    private String filename;
    private String path;
    private int image;

    public File(){
        name="";
        animal="";
        country="";
        memory=-1;
        filename="";
        path="";
        color="";
        image=-1;
    }

    public File(String name,String animal,String country,String color,String filename) {
        this.name=name;
        this.animal=animal;
        this.country=country;
        this.memory=-1;
        this.color=color;
        this.filename=filename;
        this.image=-1;
    }

    public File(String name,String animal,String country,int memory,String color,String path) {
        this.name=name;
        this.animal=animal;
        this.country=country;
        this.memory=memory;
        this.color=color;
        this.path=path;
        this.image=-1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
