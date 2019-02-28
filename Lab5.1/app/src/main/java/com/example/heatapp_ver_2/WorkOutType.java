package com.example.heatapp_ver_2;

        import java.io.Serializable;

public abstract class WorkOutType implements Serializable {

    abstract public String getEvent();

    abstract public int getSeconds();
}
