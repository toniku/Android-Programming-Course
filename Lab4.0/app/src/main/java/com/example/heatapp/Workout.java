package com.example.heatapp;

public class Workout extends WorkOutType {

    int seconds = 0;
    String event = null;

    public Workout(int seconds, String event) {
        this.seconds = seconds;
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public int getSeconds() {
        return seconds;
    }
}
