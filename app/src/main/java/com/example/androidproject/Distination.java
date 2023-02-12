package com.example.androidproject;


import java.util.Comparator;

public class Distination {
    private String City;
    private String Country;
    private String Continent;
    private float longitude;
    private float latitude;
    private int Cost;
    private String Img;
    private String Description;

    public Distination() {
    }

    public Distination(String city, String country, String continent, float longitude, float latitude, int cost, String img, String description) {
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

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
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

    public static Comparator<Distination> ascending_order= new Comparator<Distination>() {

        // Method
        public int compare(Distination D1, Distination D2) {

            int cost1 = D1.getCost();
            int cost2 = D2.getCost();

            // For ascending order
            return cost1 - cost2;
        }
    };

    public static Comparator<Distination> descending_order = new Comparator<Distination>() {
        // Method
        public int compare(Distination D1, Distination D2) {

            int cost1 = D1.getCost();
            int cost2 = D2.getCost();

            // For descending order
            return  cost2 - cost1;
        }
    };
}
