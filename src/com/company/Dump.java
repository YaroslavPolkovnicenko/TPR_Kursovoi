package com.company;

import java.util.ArrayList;

public class Dump {
    private String Name;
    private ArrayList<Truck> trucks;
    private ArrayList<Excavator> excavators;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    Dump(String Name, ArrayList<Truck> trucks, ArrayList<Excavator> excavators){
        this.Name = Name;
        this.trucks = trucks;
        this.excavators = excavators;
    }

    @Override
    public String toString() {
        return "Dump{" +
                "Name=" + Name +
                '}';
    }
}