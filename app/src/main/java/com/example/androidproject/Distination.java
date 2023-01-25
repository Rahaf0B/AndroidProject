package com.example.androidproject;


public class Distination {
    private String City;
    private String Country;
    private String Continent;
    private int longitude;
    private int latitude;
    private int Cost;
    private String Img;
    private String Description;

    public Distination() {
    }


    public Distination(String city, String country, String continent, int longitude, int latitude, int cost, String img, String description) {
        City = city;
        Country = country;
        Continent = continent;
        this.longitude = longitude;
        this.latitude = latitude;
        Cost = cost;
        Img = img;
        Description = description;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }


    @Override
    public String toString() {
        return "Distination{" +
                "City='" + City + '\'' +
                ", Country='" + Country + '\'' +
                ", Continent='" + Continent + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", Cost=" + Cost +
                ", Img='" + Img + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
