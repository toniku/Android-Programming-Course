package com.example.heatapp;

import java.io.Serializable;

public abstract class WorkOutType implements Serializable {

    abstract public String getEvent();

    abstract public int getSeconds();
}
