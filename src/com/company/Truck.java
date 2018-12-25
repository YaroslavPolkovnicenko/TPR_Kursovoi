package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Truck {
    private String Name;
    private int num_dump;
    private double carrying;
    private double driving_time;
    private double time_pribitiya_dump;
    private double time_viezda;
    private double time_pribitiya_zd;
    private double time_zagruzki_sost;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNum_dump() {
        return num_dump;
    }

    public void setNum_dump(int num_dump) {
        this.num_dump = num_dump;
    }

    public double getCarrying() {
        return carrying;
    }

    public void setCarrying(double carrying) {
        this.carrying = carrying;
    }

    public double getDriving_time() {
        return driving_time;
    }

    public void setDriving_time(double driving_time) {
        this.driving_time = driving_time;
    }

    public double getTime_pribitiya_dump() {
        return time_pribitiya_dump;
    }

    public void setTime_pribitiya_dump(double time_pribitiya_dump) {
        this.time_pribitiya_dump = time_pribitiya_dump;
    }

    public double getTime_viezda() {
        return time_viezda;
    }

    public void setTime_viezda(double time_viezda) {
        this.time_viezda = time_viezda;
    }

    public double getTime_pribitiya_zd() {
        return time_pribitiya_zd;
    }

    public void setTime_pribitiya_zd(double time_pribitiya_zd) {
        this.time_pribitiya_zd = time_pribitiya_zd;
    }

    public double getTime_zagruzki_sost() {
        return time_zagruzki_sost;
    }

    public void setTime_zagruzki_sost(double time_zagruzki_sost) {
        this.time_zagruzki_sost = time_zagruzki_sost;
    }

    Truck(String Name, double carrying, double driving_time, int num_dump, double time_pribitiya_dump, double time_viezda, double time_pribitiya_zd, double time_zagruzki_sost){
        this.Name = Name;
        this.carrying = carrying;
        this.driving_time = driving_time;
        this.num_dump = num_dump;
        this.time_pribitiya_dump = time_pribitiya_dump;
        this.time_viezda = time_viezda;
        this.time_pribitiya_zd = time_pribitiya_zd;
        this.time_zagruzki_sost = time_zagruzki_sost;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        return "Trucks{" +
                "Имя =" + Name +
                ", № отвала ='" + num_dump + '\'' +
                ", время прибытия отвал ='" + df.format(time_pribitiya_dump) + '\'' +
                ", груз ='" + df.format(carrying) + '\'' +
                ", время выезда ='" + df.format(time_viezda) + '\'' +
                ", время движения ='" + df.format(driving_time) + '\'' +
                ", время прибытия жд ='" + df.format(time_pribitiya_zd) + '\'' +
                '}';
    }
}