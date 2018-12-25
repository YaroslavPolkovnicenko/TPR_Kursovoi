package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Train {
    private String Name;
    private double all_gruz;
    private double completed_gruz;
    private double remains_gruz;
    private double time_pribitiya;
    private int ochered;
    private double time_zagruz;
    private double time_osv;
    private double time_nachala;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getAll_gruz() {
        return all_gruz;
    }

    public void setAll_gruz(double all_gruz) {
        this.all_gruz = all_gruz;
    }

    public double getCompleted_gruz() {
        return completed_gruz;
    }

    public void setCompleted_gruz(double completed_gruz) {
        this.completed_gruz = completed_gruz;
    }

    public double getRemains_gruz() {
        return remains_gruz;
    }

    public void setRemains_gruz(double remains_gruz) {
        this.remains_gruz = remains_gruz;
    }

    public double getTime_pribitiya() {
        return time_pribitiya;
    }

    public void setTime_pribitiya(double time_pribitiya) {
        this.time_pribitiya = time_pribitiya;
    }

    public int getOchered() {
        return ochered;
    }

    public void setOchered(int ochered) {
        this.ochered = ochered;
    }

    public double getTime_zagruz() {
        return time_zagruz;
    }

    public void setTime_zagruz(double time_zagruz) {
        this.time_zagruz = time_zagruz;
    }

    public double getTime_osv() {
        return time_osv;
    }

    public void setTime_osv(double time_osv) {
        this.time_osv = time_osv;
    }

    public double getTime_nachala() {
        return time_nachala;
    }

    public void setTime_nachala(double time_nachala) {
        this.time_nachala = time_nachala;
    }

    Train(String Name, double time_nachala, double all_gruz, double completed_gruz, double remains_gruz, double time_pribitiya, int ochered, double time_zagruz, double time_osv){

        this.Name = Name;
        this.time_nachala = time_nachala;
        this.all_gruz = all_gruz;
        this.completed_gruz = completed_gruz;
        this.remains_gruz = remains_gruz;
        this.time_pribitiya = time_pribitiya;
        this.ochered = ochered;
        this.time_zagruz = time_zagruz;
        this.time_osv = time_osv;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        return "Trains{" +
                "Имя=" + Name +
                ", время прибытия ='" + df.format(time_pribitiya) + '\'' +
                ", время начала загрузки ='" + df.format(time_nachala) + '\'' +
                ", время загрузки ='" + df.format(time_zagruz) + '\'' +
                ", время освобождения ='" + df.format(time_osv) + '\'' +
                ", всего груза='" + df.format(all_gruz) + '\'' +
                ", загружено ='" + df.format(completed_gruz) + '\'' +
                ", осталось загрузить ='" + df.format(remains_gruz) + '\'' +
                '}';
    }
}
