package com.company.DistributionLaw;

import java.util.Random;

public class RandomKsi {

    private double ksi;

    public double getKsi() {
        return ksi;
    }

    public void setKsi(double ksi) {
        this.ksi = ksi;
    }

    RandomKsi(){

    }

    public double GetKsi() {
        Random r = new Random();

        ksi = r.nextDouble();

        return ksi;
    }
}