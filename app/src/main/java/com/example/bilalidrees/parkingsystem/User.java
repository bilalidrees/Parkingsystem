package com.example.bilalidrees.parkingsystem;

public class User {

    private String Name;
    private String email;
    private String Feedback;

    public User() {
    }

    public User(String name, String email) {
        Name = name;
        this.email = email;
    }


    public User(String name, String email, String Feedback) {
        Name = name;
        this.email = email;
        this.Feedback = Feedback;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedback(String Feedback) {
        this.Feedback = Feedback;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getFeedback() {
        return Feedback;
    }
}
