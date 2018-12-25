package com.company.DistributionLaw;

public class ExsponentialLaw {

    private double MO;
    private RandomKsi r = new RandomKsi();

    public double getMO() {
        return MO;
    }

    public void setMO(double MO) {
        this.MO = MO;
    }

    public double GetExcponentialY(double MO){
        return -MO * Math.log10(r.GetKsi());
    }
}