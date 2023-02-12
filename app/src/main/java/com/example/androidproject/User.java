package com.example.androidproject;


public class User {
    private int UserID;
    private String Fname;
    private String Lname;
    private String Email;
    private String Password;
    private String Distination;


    public User() {
    }


    public User(int userID, String fname, String lname, String email, String password, String distination) {
        UserID = userID;
        Fname = fname;
        Lname = lname;
        Email = email;
        Password = password;
        Distination = distination;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getFname() {

        return Fname;
    }

    public void setFname(String fname) {

        Fname = fname;
    }

    public String getLname() {

        return Lname;
    }

    public void setLname(String lname) {

        Lname = lname;
    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDistination() {
        return Distination;
    }

    public void setDistination(String distination) {
        Distination = distination;
    }


    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Fname='" + Fname + '\'' +
                ", Lname='" + Lname + '\'' +
                ", Email='" + Email + '\'' +
                ", Distination='" + Distination + '\'' +
                '}';
    }

}
