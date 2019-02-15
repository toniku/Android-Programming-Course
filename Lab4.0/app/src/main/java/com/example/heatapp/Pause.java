package com.example.heatapp;

public class Pause extends WorkOutType {

    int seconds = 0;
    String event = null;

    public Pause(int seconds, String event) {
        this.seconds = seconds;
        this.event = event;
    }

    public String getEvent() {return event; }
    public int getSeconds() { return seconds; }
}
