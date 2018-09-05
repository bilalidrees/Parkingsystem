package com.example.bilalidrees.parkingsystem;

public class Slots {

    private String id;
    private String uid;
    private String name;
    private String email;
    private String date;
    private String start_time;
    private String end_time;
    private String avaliblity;


    public Slots() {

    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getAvaliblity() {
        return avaliblity;
    }

    public Slots(String id, String uid, String name, String email, String date, String start_time, String end_time, String avaliblity) {

        this.id = id;
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.avaliblity = avaliblity;
    }
}