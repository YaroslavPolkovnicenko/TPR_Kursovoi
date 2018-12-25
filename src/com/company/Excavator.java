package com.company;

public class Excavator {
    private String Name;
    private double loading_time;
    private int num_dump;
    private double time_osv;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLoading_time() {
        return loading_time;
    }

    public void setLoading_time(double loading_time) {
        this.loading_time = loading_time;
    }

    public int getNum_dump() {
        return num_dump;
    }

    public void setNum_dump(int num_dump) {
        this.num_dump = num_dump;
    }

    public double getTime_osv() {
        return time_osv;
    }

    public void setTime_osv(double time_osv) {
        this.time_osv = time_osv;
    }

    Excavator(String Name, double loading_time, int num_dump, double time_osv){
        this.Name = Name;
        this.loading_time = loading_time;
        this.num_dump = num_dump;
        this.time_osv = time_osv;
    }

    @Override
    public String toString() {
        return "Excavators{" +
                "Name=" + Name +
                "; Loading_time=" + loading_time +
                "; Num_dump=" + num_dump +
                "; Time_osv=" + time_osv +
                '}';
    }
}