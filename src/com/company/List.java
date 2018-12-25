package com.company;

import java.util.ArrayList;

public class List {

    private ArrayList<Truck> trucks1 = new ArrayList<>();
    private ArrayList<Truck> trucks2 = new ArrayList<>();
    private ArrayList<Excavator> excavators1 = new ArrayList<>();
    private ArrayList<Excavator> excavators2 = new ArrayList<>();
    private ArrayList<Dump> dumps = new ArrayList<>();
    private ArrayList<Truck> trucks_all = new ArrayList<>();
    private ArrayList<Train> trains = new ArrayList<>();

    public ArrayList<Truck> getTrucks1() {
        return trucks1;
    }

    public void setTrucks1(ArrayList<Truck> trucks1) {
        this.trucks1 = trucks1;
    }

    public ArrayList<Truck> getTrucks2() {
        return trucks2;
    }

    public void setTrucks2(ArrayList<Truck> trucks2) {
        this.trucks2 = trucks2;
    }

    public ArrayList<Excavator> getExcavators1() {
        return excavators1;
    }

    public void setExcavators1(ArrayList<Excavator> excavators1) {
        this.excavators1 = excavators1;
    }

    public ArrayList<Excavator> getExcavators2() {
        return excavators2;
    }

    public void setExcavators2(ArrayList<Excavator> excavators2) {
        this.excavators2 = excavators2;
    }

    public ArrayList<Dump> getDumps() {
        return dumps;
    }

    public void setDumps(ArrayList<Dump> dumps) {
        this.dumps = dumps;
    }

    public ArrayList<Truck> getTrucks_all() {
        return trucks_all;
    }

    public void setTrucks_all(ArrayList<Truck> trucks_all) {
        this.trucks_all = trucks_all;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    List(){    }

    public void AddTruckDump1(Truck truck){
        trucks1.add(truck);
    }

    public void AddTruckDump2(Truck truck){
        trucks2.add(truck);
    }

    public void AddExcavatorDump1(Excavator excavator){
        excavators1.add(excavator);
    }

    public void AddExcavatorDump2(Excavator excavator){
        excavators2.add(excavator);
    }

    public void AddDump(Dump dump){
        dumps.add(dump);
    }

    public void AddTrain(Train train){
        trains.add(train);
    }

    public Train GetTrain(int i){
        return trains.get(i);
    }

    public Truck GetTruckDump1(int i){
        return trucks1.get(i);
    }

    public Truck GetTruckDump2(int i) { return trucks2.get(i); }

    public Excavator GetExcavatorDump1(int i){
        return excavators1.get(i);
    }

    public Excavator GetExcavatorDump2(int i){
        return excavators2.get(i);
    }

    public Dump GetDump(int i){
        return dumps.get(i);
    }

    public Truck GetTruckAll(int i){
        return trucks_all.get(i);
    }

    public void AddTrucksAll(Truck truck){
        trucks_all.add(truck);
    }
}