package com.example.heatapp_ver_2;

public class Workout extends WorkOutType {

    int seconds;
    String event;

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
