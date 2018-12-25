package com.company;

import com.company.DistributionLaw.ExsponentialLaw;
import com.company.DistributionLaw.NormalLaw;

import java.util.ArrayList;

public class Transportation {

    private NormalLaw normalY = new NormalLaw();
    private ExsponentialLaw exsponentialY = new ExsponentialLaw();
    private ArrayList<Integer> ochered;
    ArrayList<Integer> ochered1 = new ArrayList<>();
    ArrayList<Integer> ochered2 = new ArrayList<>();
    private double last_time;
    double carrying;
    double first_time = 0.0;

    Transportation(){
        this.ochered = new ArrayList<>();
        this.carrying = 0.0;
        this.last_time = 0.0;
        //this.first_time = 0.0;
    }

    public double getLast_time() { return last_time; }

    public double getFirst_time(){return first_time;}

    public void CreateTrucks1(int kol, List list){
        for(int i = 0; i < kol; i++){
            list.AddTruckDump1(new Truck("Грузовик № " + (i + 1), 0, 0, 1, 0, 0, 0, 0));
        }
    }

    public void CreateTrucks2(int kol, List list){
        for(int i = 0; i < kol; i++){
            list.AddTruckDump2(new Truck("Грузовик № " + (i + 1), 0, 0, 2, 0, 0, 0, 0));
        }
    }

    public void CreateExcavators1(int kol, List list){
        for(int i = 0; i < kol; i++){
            list.AddExcavatorDump1(new Excavator("Экскаватор № " + (i + 1), 0,1, 0));
        }
    }

    public void CreateExcavators2(int kol, List list){
        for(int i = 0; i < kol; i++){
            list.AddExcavatorDump2(new Excavator("Экскаватор № " + (i + 1), 0, 2, 0));
        }
    }

    public void CreateTrains(List list, double cur_time){

        //for(int i = 0; i < 2; i++) {
        double pribitie = normalY.getUniformByRange(30, 90);
            list.AddTrain(new Train("Поезд " + (list.getTrains().size() + 1), 0, 600, 0, 600,  pribitie + cur_time, 0, 0,  pribitie + cur_time));
        //}

    }

    public void PerevozkaFromOtvalFirst(int kol_excavator, int kol_trucks, int p, List list){

        if(p == 0) {
            CreateExcavators1(kol_excavator, list);
            CreateTrucks1(kol_trucks, list);
        }
            int k = 0;
            double time_pribitiya = 0.0;
            double time_pribitiya_zd = 0.0;
            double time_osv = 0.0;
            double loading_time = 0.0;
            double current_time = 0.0;
            double gruz = 0.0;
            double time_dviz = 0.0;



        for(int i = 0; i < kol_trucks; i++){

            for(int j = 0; j < kol_excavator; j++){
                list.getExcavators1().sort(new ExcavatorComparator());
                if(list.GetExcavatorDump1(j).getTime_osv() <= list.GetTruckDump1(i).getTime_pribitiya_dump()){

                    if(ochered.size() == 0){
                        loading_time = normalY.getNormalY(20, 5);
                        gruz = normalY.getUniformByRange(10, 15);
                        time_dviz = exsponentialY.GetExcponentialY(30);

                        list.GetExcavatorDump1(j).setLoading_time(loading_time); // время закрузки
                        time_osv = list.GetTruckDump1(i).getTime_pribitiya_dump() + loading_time;
                        list.GetExcavatorDump1(j).setTime_osv(time_osv); // время освобождения экскаватора

                        list.GetTruckDump1(i).setCarrying(gruz); //груз
                        list.GetTruckDump1(i).setTime_viezda(list.GetExcavatorDump1(j).getTime_osv()); // время выезда из отвала
                        list.GetTruckDump1(i).setDriving_time(time_dviz);
                        list.GetTruckDump1(i).setTime_pribitiya_zd(list.GetTruckDump1(i).getTime_viezda() + time_dviz);

                        list.AddTrucksAll(list.GetTruckDump1(i));

                        //System.out.println(list.GetTruckDump1(i));
                        //System.out.println(list.GetExcavatorDump1(j));
                        break;
                    }

                    else if(ochered.size() > 0){
                        ochered.add(i);

                        if(list.GetTruckDump1(ochered.get(0)).getTime_pribitiya_dump() > list.GetExcavatorDump1(j).getTime_osv()){
                            time_osv = list.GetTruckDump1(ochered.get(0)).getTime_pribitiya_dump();
                        }
                        else{
                            time_osv = list.GetExcavatorDump1(j).getTime_osv();
                        }

                        loading_time = normalY.getNormalY(20, 5);
                        gruz = normalY.getUniformByRange(10, 15);
                        time_dviz = exsponentialY.GetExcponentialY(30);

                        list.GetExcavatorDump1(j).setLoading_time(loading_time); // время закрузки
                        list.GetExcavatorDump1(j).setTime_osv(time_osv + loading_time); // время освобождения экскаватора

                        list.GetTruckDump1(ochered.get(0)).setCarrying(gruz); //груз
                        list.GetTruckDump1(ochered.get(0)).setTime_viezda(list.GetExcavatorDump1(j).getTime_osv()); // время выезда из отвала
                        list.GetTruckDump1(ochered.get(0)).setDriving_time(time_dviz);
                        list.GetTruckDump1(ochered.get(0)).setTime_pribitiya_zd(list.GetTruckDump1(ochered.get(0)).getTime_viezda() + time_dviz);

                        list.AddTrucksAll(list.GetTruckDump1(ochered.get(0)));

                        //System.out.println(list.GetTruckDump1(ochered.get(0)));
                        //System.out.println(list.GetExcavatorDump1(j));

                        ochered.remove(0);
                        break;
                    }
                }
                k++;
            }
            if(k > 0) {
                ochered.add(i);
                //System.out.println("Добавлен в очередь: время прибытия = " + time_pribitiya + " время освобождения =" + list.GetExcavatorDump1(0).getTime_osv());
            }
        }

        while (ochered.size() != 0){
            list.getExcavators1().sort(new ExcavatorComparator());
            if(list.GetTruckDump1(ochered.get(0)).getTime_pribitiya_dump() > list.GetExcavatorDump1(0).getTime_osv()){
                time_osv = list.GetTruckDump1(ochered.get(0)).getTime_pribitiya_dump();
            }
            else{
                time_osv = list.GetExcavatorDump1(0).getTime_osv();
            }
            loading_time = normalY.getNormalY(20, 5);
            gruz = normalY.getUniformByRange(10, 15);
            time_dviz = exsponentialY.GetExcponentialY(30);

            list.GetExcavatorDump1(0).setLoading_time(loading_time); // время закрузки
            list.GetExcavatorDump1(0).setTime_osv(list.GetExcavatorDump1(0).getTime_osv() + loading_time); // время освобождения экскаватора

            list.GetTruckDump1(ochered.get(0)).setCarrying(gruz); //груз
            list.GetTruckDump1(ochered.get(0)).setTime_viezda(list.GetExcavatorDump1(0).getTime_osv()); // время выезда из отвала
            list.GetTruckDump1(ochered.get(0)).setDriving_time(time_dviz);
            list.GetTruckDump1(ochered.get(0)).setTime_pribitiya_zd(list.GetTruckDump1(ochered.get(0)).getTime_viezda() + time_dviz);

            list.AddTrucksAll(list.GetTruckDump1(ochered.get(0)));

            //System.out.println(list.GetTruckDump1(ochered.get(0)));
            //System.out.println(list.GetExcavatorDump1(0));

            ochered.remove(0);
        }
    }

    public void PerevozkaFromOtvalSecond(int kol_excavator, int kol_trucks, int p, List list){

        if(p == 0) {
            CreateExcavators2(kol_excavator, list);
            CreateTrucks2(kol_trucks, list);
        }

        int k = 0;
        double time_pribitiya = 0.0;
        double time_osv = 0.0;
        double loading_time = 0.0;
        double gruz = 0.0;
        double time_dviz = 0.0;
        ArrayList<Integer> ochered = new ArrayList<>();

        for(int i = 0; i < kol_trucks; i++){

            for(int j = 0; j < kol_excavator; j++){
                list.getExcavators2().sort(new ExcavatorComparator());
                if(list.GetExcavatorDump2(j).getTime_osv() <= list.GetTruckDump2(i).getTime_pribitiya_dump()){

                    if(ochered.size() == 0){
                        loading_time = normalY.getNormalY(15, 3);
                        gruz = normalY.getUniformByRange(10, 15);
                        time_dviz = exsponentialY.GetExcponentialY(20);

                        list.GetExcavatorDump2(j).setLoading_time(loading_time); // время закрузки
                        time_osv = list.GetTruckDump2(i).getTime_pribitiya_dump() + loading_time;
                        list.GetExcavatorDump2(j).setTime_osv(time_osv); // время освобождения экскаватора

                        list.GetTruckDump2(i).setCarrying(gruz); //груз
                        list.GetTruckDump2(i).setTime_viezda(list.GetExcavatorDump2(j).getTime_osv()); // время выезда из отвала
                        list.GetTruckDump2(i).setDriving_time(time_dviz);
                        list.GetTruckDump2(i).setTime_pribitiya_zd(list.GetTruckDump2(i).getTime_viezda() + time_dviz);

                        list.AddTrucksAll(list.GetTruckDump2(i));

                        //System.out.println(list.GetTruckDump2(i));
                        //System.out.println(list.GetExcavatorDump2(j));
                        break;
                    }

                    else if(ochered.size() > 0){
                        ochered.add(i);

                        if(list.GetTruckDump2(ochered.get(0)).getTime_pribitiya_dump() > list.GetExcavatorDump2(j).getTime_osv()){
                            time_osv = list.GetTruckDump2(ochered.get(0)).getTime_pribitiya_dump();
                        }
                        else{
                            time_osv = list.GetExcavatorDump2(j).getTime_osv();
                        }

                        loading_time = normalY.getNormalY(15, 3);
                        gruz = normalY.getUniformByRange(10, 15);
                        time_dviz = exsponentialY.GetExcponentialY(20);

                        list.GetExcavatorDump2(j).setLoading_time(loading_time); // время закрузки
                        list.GetExcavatorDump2(j).setTime_osv(time_osv + loading_time); // время освобождения экскаватора

                        list.GetTruckDump2(ochered.get(0)).setCarrying(gruz); //груз
                        list.GetTruckDump2(ochered.get(0)).setTime_viezda(list.GetExcavatorDump1(j).getTime_osv()); // время выезда из отвала
                        list.GetTruckDump2(ochered.get(0)).setDriving_time(time_dviz);
                        list.GetTruckDump2(ochered.get(0)).setTime_pribitiya_zd(list.GetTruckDump1(ochered.get(0)).getTime_viezda() + time_dviz);

                        list.AddTrucksAll(list.GetTruckDump2(ochered.get(0)));

                        //System.out.println(list.GetTruckDump1(ochered.get(0)));
                        //System.out.println(list.GetExcavatorDump2(j));

                        ochered.remove(0);
                        break;
                    }
                }
                k++;
            }
            if(k > 0) {
                ochered.add(i);
                //System.out.println("Добавлен в очередь: время прибытия = " + time_pribitiya + " время освобождения =" + list.GetExcavatorDump2(0).getTime_osv());
            }
        }

        while (ochered.size() != 0){

            list.getExcavators2().sort(new ExcavatorComparator());
            if(list.GetTruckDump2(ochered.get(0)).getTime_pribitiya_dump() > list.GetExcavatorDump2(0).getTime_osv()){
                time_osv = list.GetTruckDump2(ochered.get(0)).getTime_pribitiya_dump();
            }
            else{
                time_osv = list.GetExcavatorDump2(0).getTime_osv();
            }
            loading_time = normalY.getNormalY(15, 3);
            gruz = normalY.getUniformByRange(10, 15);
            time_dviz = exsponentialY.GetExcponentialY(20);

            list.GetExcavatorDump2(0).setLoading_time(loading_time); // время закрузки
            list.GetExcavatorDump2(0).setTime_osv(list.GetExcavatorDump2(0).getTime_osv() + loading_time); // время освобождения экскаватора

            list.GetTruckDump2(ochered.get(0)).setCarrying(gruz); //груз
            list.GetTruckDump2(ochered.get(0)).setTime_viezda(list.GetExcavatorDump2(0).getTime_osv()); // время выезда из отвала
            list.GetTruckDump2(ochered.get(0)).setDriving_time(time_dviz);
            list.GetTruckDump2(ochered.get(0)).setTime_pribitiya_zd(list.GetTruckDump2(ochered.get(0)).getTime_viezda() + time_dviz);

            list.AddTrucksAll(list.GetTruckDump2(ochered.get(0)));

            //System.out.println(list.GetTruckDump2(ochered.get(0)));
            //System.out.println(list.GetExcavatorDump2(0));

            ochered.remove(0);
        }
    }

    public boolean CreateSostav(List list, int k, int num){
        double time_zagruzki = 0.0;
        double time_dviz = 0.0;
        double current_time = 0.0;
if(k == 0) {
    CreateTrains(list, 0);
    double first_time = list.getTrains().get(0).getTime_pribitiya();
}
//        //CreateTrains(list, list.getTrains().get(0).getTime_pribitiya());
//
        //double current_time = list.getTrains().get(0).getTime_osv();
//
//        for(int i = 0; i < list.getTrucks_all().size(); i++){
//
//            for(int j = 0; j < list.getTrains().size(); j++){
//                    if(list.GetTrain(j).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()){
//
//                        list.GetTrain(j).setTime_pribitiya(list.GetTruckAll(i).getTime_pribitiya_zd());
//
//                        if(ochered.size() == 0){
//                            //current_time = list.GetTruckAll(i).getTime_pribitiya_zd();
//                        time_zagruzki = normalY.getNormalY(3, 1);
//                        list.GetTrain(j).setTime_zagruz(time_zagruzki);
//                        list.GetTrain(j).setTime_osv(list.GetTrain(j).getTime_pribitiya() + list.GetTrain(j).getTime_zagruz());
//
//                            //if(list.GetTrain(j).getTime_osv() < list.GetTruckAll(i).getTime_pribitiya_zd()){
//                                /*if(list.GetTruckAll(i).getNum_dump() == 1){
//                                    time_dviz = exsponentialY.GetExcponentialY(30);
//                                }
//                                else{
//                                    time_dviz = exsponentialY.GetExcponentialY(20);
//                                }*/
//
//                                list.GetTruckAll(i).setTime_pribitiya_dump(list.GetTrain(j).getTime_osv() + time_dviz);
//
//                            current_time += time_zagruzki;
//                            carrying += list.GetTruckAll(i).getCarrying();
//
//
//                            //list.GetTruckAll(i).setTime_zagruzki_sost(time_zagruzki);
//                            list.GetTrain(j).setCompleted_gruz(carrying);
//                            if ((list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz()) < 0) {
//                                list.GetTrain(j).setRemains_gruz(0.0);
//                                list.GetTruckAll(i).setCarrying((list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz()) * (-1));
//                            } else {
//                                list.GetTrain(j).setRemains_gruz(list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz());
//                            }
//                                //list.GetTrain(j).setTime_pribitiya(current_time);
//
//                            } /*else {
//                                list.GetTruckAll(i).setTime_pribitiya_dump(list.GetTruckAll(i).getTime_pribitiya_zd() + time_dviz);
//                                list.GetTrain(j).setTime_pribitiya(list.GetTruckAll(i).getTime_pribitiya_zd());
//                            }*/
//else {
//                                ochered.add(i);
//                                current_time += time_zagruzki;
//                                carrying += list.GetTruckAll(ochered.get(0)).getCarrying();
//
//
//                                list.GetTruckAll(ochered.get(0)).setTime_zagruzki_sost(time_zagruzki);
//                                list.GetTrain(j).setCompleted_gruz(carrying);
//                                if ((list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz()) < 0) {
//                                    list.GetTrain(j).setRemains_gruz(0.0);
//                                    list.GetTruckAll(ochered.get(0)).setCarrying((list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz()) * (-1));
//                                } else {
//                                    list.GetTrain(j).setRemains_gruz(list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz());
//                                }
//                            }
//
//if(list.GetTrain(j).getRemains_gruz() == 0){
//    System.out.println(list.GetTrain(j));
//    return true;
//}
//                            System.out.println(list.GetTrain(j));
//
//                }
//                    else{
//                        ochered.add(i);
//                        System.out.println("Добавлен в очередь " + list.GetTruckAll(i).getName());
//
//                            time_zagruzki = normalY.getNormalY(3, 1);
//                            list.GetTrain(j).setTime_zagruz(time_zagruzki);
//
//                            current_time += time_zagruzki;
//                            carrying += list.GetTruckAll(i).getCarrying();
//
//                            if(current_time < list.GetTruckAll(i).getTime_pribitiya_zd()){
//                                list.GetTruckAll(ochered.get(0)).setTime_pribitiya_dump(list.GetTruckAll(ochered.get(0)).getTime_pribitiya_zd());
//                                list.GetTrain(j).setTime_pribitiya(list.GetTruckAll(ochered.get(0)).getTime_pribitiya_zd());
//                            }
//                            else {
//                                list.GetTruckAll(ochered.get(0)).setTime_pribitiya_dump(current_time);
//                                list.GetTrain(0).setTime_pribitiya(current_time);
//                            }
//
//                            list.GetTruckAll(ochered.get(0)).setTime_zagruzki_sost(time_zagruzki);
//                            list.GetTrain(j).setCompleted_gruz(carrying);
//                            list.GetTrain(j).setRemains_gruz(list.GetTrain(j).getAll_gruz() - list.GetTrain(j).getCompleted_gruz());
//
//                            System.out.println(list.GetTrain(j));
//                            ochered.remove(0);
//                    }
//            }
//            }

        for(int i = 0; i < list.getTrucks_all().size(); i++) {

            if (list.getTrains().size() < 2) {
                if (list.getTrains().size() == 1) {
                    CreateTrains(list, list.GetTrain(0).getTime_pribitiya());

                } else {
                    CreateTrains(list, 0);
                }
            }

            if (ochered1.size() == 0 && ochered2.size() == 0) {

                if (list.GetTrain(0).getCompleted_gruz() >= list.GetTrain(1).getCompleted_gruz()) {

                    if (list.GetTrain(0).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()) {

                        time_zagruzki = normalY.getNormalY(3, 1);

                        carrying += list.GetTruckAll(i).getCarrying();

                        list.GetTrain(0).setTime_nachala(list.GetTruckAll(i).getTime_pribitiya_zd()); //время начала
                        list.GetTrain(0).setTime_zagruz(time_zagruzki); //время загрузки
                        list.GetTrain(0).setTime_osv(list.GetTrain(0).getTime_nachala() + time_zagruzki); //время освобождения
                        list.GetTrain(0).setCompleted_gruz(carrying); //груза на составе
                        list.GetTrain(0).setRemains_gruz(list.GetTrain(0).getAll_gruz() - list.GetTrain(0).getCompleted_gruz()); //осталось загрузить

                        if (list.GetTrain(0).getRemains_gruz() <= 0) {
                            list.GetTruckAll(i).setCarrying(list.GetTrain(0).getRemains_gruz() * (-1));
                            list.GetTrain(0).setRemains_gruz(0.0);
                            list.GetTrain(0).setCompleted_gruz(600.0);
                            System.out.println("Обслужил состав № 1: " + list.GetTruckAll(i) + "\n" + list.GetTrain(0));
                            last_time = list.GetTrain(0).getTime_osv();
                            list.getTrains().remove(0);
                            System.out.println("СОСТАВ № 1 ОБСЛУЖЕН!");
                            ochered1.clear();
                            return true;
                        }

                        if (list.GetTruckAll(i).getNum_dump() == 1) {
                            time_dviz = exsponentialY.GetExcponentialY(30);
                        } else {
                            time_dviz = exsponentialY.GetExcponentialY(20);
                        }

                        list.GetTruckAll(i).setTime_pribitiya_dump(list.GetTrain(0).getTime_osv() + time_dviz); //время прибытия на отвал
                        System.out.println("Обслужил состав № 1: " + list.GetTruckAll(i) + "\n" + list.GetTrain(0));
                        //list.getTrucks_all().remove(i);
                    } else {
                        ochered1.add(i);
                        list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
                        System.out.println("Добавлен в очередь к составу № 1: " + list.GetTruckAll(i));
                    }
                } else if (list.GetTrain(1).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()) {

                    time_zagruzki = normalY.getNormalY(3, 1);

                    carrying += list.GetTruckAll(i).getCarrying();

                    list.GetTrain(1).setTime_nachala(list.GetTruckAll(i).getTime_pribitiya_zd()); //время начала
                    list.GetTrain(1).setTime_zagruz(time_zagruzki); //время загрузки
                    list.GetTrain(1).setTime_osv(list.GetTrain(1).getTime_nachala() + time_zagruzki); //время освобождения
                    list.GetTrain(1).setCompleted_gruz(carrying); //груза на составе
                    list.GetTrain(1).setRemains_gruz(list.GetTrain(1).getAll_gruz() - list.GetTrain(1).getCompleted_gruz()); //осталось загрузить

                    if (list.GetTrain(1).getRemains_gruz() <= 0) {
                        list.GetTruckAll(i).setCarrying(list.GetTrain(1).getRemains_gruz() * (-1));
                        list.GetTrain(1).setRemains_gruz(0.0);
                        list.GetTrain(1).setCompleted_gruz(600.0);
                        System.out.println("Обслужил состав № 2: " + list.GetTruckAll(i) + "\n" + list.GetTrain(1));
                        last_time = list.GetTrain(1).getTime_osv();
                        list.getTrains().remove(1);
                        System.out.println("СОСТАВ № 2 ОБСЛУЖЕН!");
                        ochered2.clear();
                        return true;
                    }

                    if (list.GetTruckAll(i).getNum_dump() == 1) {
                        time_dviz = exsponentialY.GetExcponentialY(30);
                    } else {
                        time_dviz = exsponentialY.GetExcponentialY(20);
                    }

                    list.GetTruckAll(i).setTime_pribitiya_dump(list.GetTrain(1).getTime_osv() + time_dviz); //время прибытия на отвал
                    System.out.println("Обслужил состав № 2: " + list.GetTruckAll(i) + "\n" + list.GetTrain(1));
                    list.getTrucks_all().remove(i);
                } else {
                    ochered2.add(i);
                    list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() + 1);
                    System.out.println("Добавлен в очередь к составу № 2: " + list.GetTruckAll(i));
                }
            }
            else if(list.GetTrain(0).getOchered() <= list.GetTrain(1).getOchered()){

                if(list.GetTrain(0).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()){

                    ochered1.add(i);
                    list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
                    System.out.println("Добавлен в очередь к составу № 1: " + list.GetTruckAll(i));

                    time_zagruzki = normalY.getNormalY(3, 1);

                    carrying += list.GetTruckAll(ochered1.get(0)).getCarrying();

                    list.GetTrain(0).setTime_nachala(list.GetTruckAll(ochered1.get(0)).getTime_pribitiya_zd()); //время начала
                    list.GetTrain(0).setTime_zagruz(time_zagruzki); //время загрузки
                    list.GetTrain(0).setTime_osv(list.GetTrain(0).getTime_nachala() + time_zagruzki); //время освобождения
                    list.GetTrain(0).setCompleted_gruz(carrying); //груза на составе
                    list.GetTrain(0).setRemains_gruz(list.GetTrain(0).getAll_gruz() - list.GetTrain(0).getCompleted_gruz()); //осталось загрузить

                    if (list.GetTrain(0).getRemains_gruz() <= 0) {
                        list.GetTruckAll(ochered1.get(0)).setCarrying(list.GetTrain(0).getRemains_gruz() * (-1));
                        list.GetTrain(0).setRemains_gruz(0.0);
                        list.GetTrain(0).setCompleted_gruz(600.0);
                        System.out.println("Обслужил состав № 1: " + list.GetTruckAll(ochered1.get(0)) + "\n" + list.GetTrain(0));
                        last_time = list.GetTrain(0).getTime_osv();
                        list.getTrains().remove(0);
                        System.out.println("СОСТАВ № 1 ОБСЛУЖЕН!");
                        ochered1.clear();
                        return true;
                    }

                    if (list.GetTruckAll(ochered1.get(0)).getNum_dump() == 1) {
                        time_dviz = exsponentialY.GetExcponentialY(30);
                    } else {
                        time_dviz = exsponentialY.GetExcponentialY(20);
                    }

                    list.GetTruckAll(ochered1.get(0)).setTime_pribitiya_dump(list.GetTrain(0).getTime_osv() + time_dviz); //время прибытия на отвал
                    System.out.println("Обслужил состав № 1: " + list.GetTruckAll(ochered1.get(0)) + "\n" + list.GetTrain(0));
                    //list.getTrucks_all().remove(ochered1.get(0));
                    list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() - 1);
                    ochered1.remove(0);
                }
                else{
                    ochered1.add(i);
                    list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
                    System.out.println("Добавлен в очередь к составу № 1: " + list.GetTruckAll(i));
                }
            }
            else if(list.GetTrain(1).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()){

                ochered2.add(i);
                list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() + 1);
                System.out.println("Добавлен в очередь к составу № 2: " + list.GetTruckAll(i));

                time_zagruzki = normalY.getNormalY(3, 1);

                carrying += list.GetTruckAll(ochered2.get(0)).getCarrying();

                list.GetTrain(1).setTime_nachala(list.GetTruckAll(ochered2.get(0)).getTime_pribitiya_zd()); //время начала
                list.GetTrain(1).setTime_zagruz(time_zagruzki); //время загрузки
                list.GetTrain(1).setTime_osv(list.GetTrain(1).getTime_nachala() + time_zagruzki); //время освобождения
                list.GetTrain(1).setCompleted_gruz(carrying); //груза на составе
                list.GetTrain(1).setRemains_gruz(list.GetTrain(1).getAll_gruz() - list.GetTrain(1).getCompleted_gruz()); //осталось загрузить

                if (list.GetTrain(1).getRemains_gruz() <= 0) {
                    list.GetTruckAll(ochered2.get(0)).setCarrying(list.GetTrain(1).getRemains_gruz() * (-1));
                    list.GetTrain(1).setRemains_gruz(0.0);
                    list.GetTrain(1).setCompleted_gruz(600.0);
                    System.out.println("Обслужил состав № 2: " + list.GetTruckAll(ochered2.get(0)) + "\n" + list.GetTrain(1));
                    last_time = list.GetTrain(1).getTime_osv();
                    list.getTrains().remove(1);
                    System.out.println("СОСТАВ № 2 ОБСЛУЖЕН!");
                    ochered2.clear();
                    return true;
                }

                if (list.GetTruckAll(ochered2.get(0)).getNum_dump() == 1) {
                    time_dviz = exsponentialY.GetExcponentialY(30);
                } else {
                    time_dviz = exsponentialY.GetExcponentialY(20);
                }

                list.GetTruckAll(ochered2.get(0)).setTime_pribitiya_dump(list.GetTrain(1).getTime_osv() + time_dviz); //время прибытия на отвал
                System.out.println("Обслужил состав № 2: " + list.GetTruckAll(ochered2.get(0)) + "\n" + list.GetTrain(1));
                //list.getTrucks_all().remove(ochered1.get(0));
                list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() - 1);
                ochered2.remove(0);
            }
            else{
                ochered2.add(i);
                list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() + 1);
                System.out.println("Добавлен в очередь к составу № 2: " + list.GetTruckAll(i));
            }
        }

        while(ochered1.size() != 0){
            if(list.GetTrain(0).getTime_osv() <= list.GetTruckAll(ochered1.get(0)).getTime_pribitiya_zd()) {
                list.GetTrain(0).setTime_nachala(list.GetTruckAll(ochered1.get(0)).getTime_pribitiya_zd());
            }
            else {
                list.GetTrain(0).setTime_nachala(list.GetTrain(0).getTime_osv());
            }

                //ochered1.add(i);
                //list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
                //System.out.println("Добавлен в очередь к составу № 1: " + list.GetTruckAll(i));

                time_zagruzki = normalY.getNormalY(3, 1);

                carrying += list.GetTruckAll(ochered1.get(0)).getCarrying();

                //list.GetTrain(0).setTime_nachala(list.GetTruckAll(ochered1.get(0)).getTime_pribitiya_zd()); //время начала
                list.GetTrain(0).setTime_zagruz(time_zagruzki); //время загрузки
                list.GetTrain(0).setTime_osv(list.GetTrain(0).getTime_nachala() + time_zagruzki); //время освобождения
                list.GetTrain(0).setCompleted_gruz(carrying); //груза на составе
                list.GetTrain(0).setRemains_gruz(list.GetTrain(0).getAll_gruz() - list.GetTrain(0).getCompleted_gruz()); //осталось загрузить

                if (list.GetTrain(0).getRemains_gruz() <= 0) {
                    list.GetTruckAll(ochered1.get(0)).setCarrying(list.GetTrain(0).getRemains_gruz() * (-1));
                    list.GetTrain(0).setRemains_gruz(0.0);
                    list.GetTrain(0).setCompleted_gruz(600.0);
                    System.out.println("Обслужил состав № 1: " + list.GetTruckAll(ochered1.get(0)) + "\n" + list.GetTrain(0));
                    list.getTrains().remove(0);
                    last_time = list.GetTrain(0).getTime_osv();
                    System.out.println("СОСТАВ № 1 ОБСЛУЖЕН!");
                    ochered1.clear();
                    return true;
                }

                if (list.GetTruckAll(ochered1.get(0)).getNum_dump() == 1) {
                    time_dviz = exsponentialY.GetExcponentialY(30);
                } else {
                    time_dviz = exsponentialY.GetExcponentialY(20);
                }

                list.GetTruckAll(ochered1.get(0)).setTime_pribitiya_dump(list.GetTrain(0).getTime_osv() + time_dviz); //время прибытия на отвал
                System.out.println("Обслужил состав № 1 из очереди: " + list.GetTruckAll(ochered1.get(0)) + "\n" + list.GetTrain(0));
                //list.getTrucks_all().remove(ochered1.get(0));
                list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() - 1);
                ochered1.remove(0);
            }

        while(ochered2.size() != 0){
            if(list.GetTrain(1).getTime_osv() <= list.GetTruckAll(ochered2.get(0)).getTime_pribitiya_zd()) {
                list.GetTrain(1).setTime_nachala(list.GetTruckAll(ochered2.get(0)).getTime_pribitiya_zd());
            }
            else {
                list.GetTrain(1).setTime_nachala(list.GetTrain(1).getTime_osv());
            }

            //ochered1.add(i);
            //list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
            //System.out.println("Добавлен в очередь к составу № 1: " + list.GetTruckAll(i));

            time_zagruzki = normalY.getNormalY(3, 1);

            carrying += list.GetTruckAll(ochered2.get(0)).getCarrying();

            //list.GetTrain(0).setTime_nachala(list.GetTruckAll(ochered1.get(0)).getTime_pribitiya_zd()); //время начала
            list.GetTrain(1).setTime_zagruz(time_zagruzki); //время загрузки
            list.GetTrain(1).setTime_osv(list.GetTrain(1).getTime_nachala() + time_zagruzki); //время освобождения
            list.GetTrain(1).setCompleted_gruz(carrying); //груза на составе
            list.GetTrain(1).setRemains_gruz(list.GetTrain(1).getAll_gruz() - list.GetTrain(1).getCompleted_gruz()); //осталось загрузить

            if (list.GetTrain(1).getRemains_gruz() <= 0) {
                list.GetTruckAll(ochered2.get(0)).setCarrying(list.GetTrain(1).getRemains_gruz() * (-1));
                list.GetTrain(1).setRemains_gruz(0.0);
                list.GetTrain(1).setCompleted_gruz(600.0);
                System.out.println("Обслужил состав № 2: " + list.GetTruckAll(ochered2.get(0)) + "\n" + list.GetTrain(1));
                last_time = list.GetTrain(1).getTime_osv();
                list.getTrains().remove(1);
                System.out.println("СОСТАВ № 2 ОБСЛУЖЕН!");
                ochered2.clear();
                return true;
            }

            if (list.GetTruckAll(ochered2.get(0)).getNum_dump() == 1) {
                time_dviz = exsponentialY.GetExcponentialY(30);
            } else {
                time_dviz = exsponentialY.GetExcponentialY(20);
            }

            list.GetTruckAll(ochered2.get(0)).setTime_pribitiya_dump(list.GetTrain(1).getTime_osv() + time_dviz); //время прибытия на отвал
            System.out.println("Обслужил состав № 2 из очереди: " + list.GetTruckAll(ochered2.get(0)) + "\n" + list.GetTrain(0));
            //list.getTrucks_all().remove(ochered1.get(0));
            list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() - 1);
            ochered2.remove(0);
        }
//                            //if(ochered1.size() == 0 && ochered2.size() == 0) {
//
//                                if (list.getTrains().size() == 1) {
//
//                                    System.out.println(list.GetTrain(0));
//
//                                    if (list.GetTrain(0).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()) {
//
//                                        time_zagruzki = normalY.getNormalY(3, 1);
//
//                                        carrying += list.GetTruckAll(i).getCarrying();
//
//                                        list.GetTrain(0).setTime_nachala(list.GetTruckAll(i).getTime_pribitiya_zd()); //время начала
//                                        list.GetTrain(0).setTime_zagruz(time_zagruzki); //время загрузки
//                                        list.GetTrain(0).setTime_osv(list.GetTrain(0).getTime_nachala() + time_zagruzki); //время освобождения
//                                        list.GetTrain(0).setCompleted_gruz(carrying); //груза на составе
//                                        list.GetTrain(0).setRemains_gruz(list.GetTrain(0).getAll_gruz() - list.GetTrain(0).getCompleted_gruz()); //осталось загрузить
//
//                                        if(list.GetTrain(0).getRemains_gruz() <= 0){
//                                            list.GetTruckAll(i).setCarrying(list.GetTrain(0).getRemains_gruz() *(-1));
//                                            list.GetTrain(0).setRemains_gruz(0.0);
//                                            list.getTrains().remove(0);
//                                            return true;
//                                        }
//
//                                        if (list.GetTruckAll(i).getNum_dump() == 1) {
//                                            time_dviz = exsponentialY.GetExcponentialY(30);
//                                        } else {
//                                            time_dviz = exsponentialY.GetExcponentialY(20);
//                                        }
//
//                                        list.GetTruckAll(i).setTime_pribitiya_dump(list.GetTrain(0).getTime_osv() + time_dviz); //время прибытия на отвал
//                                        System.out.println("Обслужился № 1" + list.GetTruckAll(i) + "\n" + list.GetTrain(0));
//                                        list.getTrucks_all().remove(i);
//                                    } else {
//                                        ochered1.add(i);
//
//                                        list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
//                                        System.out.println("Добавлен в очередь № 1: " + list.GetTruckAll(i));
//                                    }
//                                } else if(ochered1.size() == 0 && ochered2.size() == 0){ //(list.GetTrain(0).getCompleted_gruz() >= list.GetTrain(1).getCompleted_gruz()) {
//                                    if((list.GetTrain(0).getCompleted_gruz() >= list.GetTrain(1).getCompleted_gruz()))
//
//                                    if (list.GetTrain(0).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()) {
//
//                                        //ochered1.add(i);
//
//                                        //list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
//                                        //System.out.println("Добавлен в очередь № 1: " + list.GetTruckAll(i));
//
//                                        time_zagruzki = normalY.getNormalY(3, 1);
//
//                                        carrying += list.GetTruckAll(i).getCarrying();
//
//                                        list.GetTrain(0).setTime_nachala(list.GetTruckAll(i).getTime_pribitiya_zd()); //время начала
//                                        list.GetTrain(0).setTime_zagruz(time_zagruzki); //время загрузки
//                                        list.GetTrain(0).setTime_osv(list.GetTrain(1).getTime_nachala() + time_zagruzki); //время освобождения
//                                        list.GetTrain(0).setCompleted_gruz(carrying); //груза на составе
//                                        list.GetTrain(0).setRemains_gruz(list.GetTrain(0).getAll_gruz() - list.GetTrain(1).getCompleted_gruz()); //осталось загрузить
//
//                                        if (list.GetTruckAll(ochered1.get(0)).getNum_dump() == 1) {
//                                            time_dviz = exsponentialY.GetExcponentialY(30);
//                                        } else {
//                                            time_dviz = exsponentialY.GetExcponentialY(20);
//                                        }
//
//                                        list.GetTruckAll(ochered1.get(0)).setTime_pribitiya_dump(list.GetTrain(1).getTime_osv() + time_dviz); //время прибытия на отвал
//                                        System.out.println("Обслужился " + list.GetTruckAll(ochered1.get(0)) + "\n" + list.GetTrain(0));
//                                        list.getTrucks_all().remove(i);
//                                        ochered1.remove(0);
//                                    } else {
//                                        ochered2.add(i);
//
//                                        list.GetTrain(1).setOchered(list.GetTrain(0).getOchered() + 1);
//                                        System.out.println("Добавлен в очередь: " + list.GetTruckAll(i));
//                                    }
//                                } else {
//                                    ochered2.add(i);
//
//                                    list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() + 1);
//                                    System.out.println("Добавлен в очередь № 2: " + list.GetTruckAll(i));
//                                }
//                            //}
//
//                            else if(ochered2.size() == 0){
//
//                                if (list.GetTrain(1).getTime_osv() <= list.GetTruckAll(i).getTime_pribitiya_zd()) {
//
//                                    time_zagruzki = normalY.getNormalY(3, 1);
//
//                                    carrying += list.GetTruckAll(i).getCarrying();
//
//                                    list.GetTrain(1).setTime_nachala(list.GetTruckAll(i).getTime_pribitiya_zd()); //время начала
//                                    list.GetTrain(1).setTime_zagruz(time_zagruzki); //время загрузки
//                                    list.GetTrain(1).setTime_osv(list.GetTrain(1).getTime_nachala() + time_zagruzki); //время освобождения
//                                    list.GetTrain(1).setCompleted_gruz(carrying); //груза на составе
//                                    list.GetTrain(1).setRemains_gruz(list.GetTrain(0).getAll_gruz() - list.GetTrain(1).getCompleted_gruz()); //осталось загрузить
//
//                                    if (list.GetTruckAll(i).getNum_dump() == 1) {
//                                        time_dviz = exsponentialY.GetExcponentialY(30);
//                                    } else {
//                                        time_dviz = exsponentialY.GetExcponentialY(20);
//                                    }
//
//                                    list.GetTruckAll(i).setTime_pribitiya_dump(list.GetTrain(1).getTime_osv() + time_dviz); //время прибытия на отвал
//                                    System.out.println("Обслужился № 2" + list.GetTruckAll(i) + "\n" + list.GetTrain(0));
//                                    list.getTrucks_all().remove(i);
//                                } else {
//                                    ochered2.add(i);
//
//                                    list.GetTrain(1).setOchered(list.GetTrain(0).getOchered() + 1);
//                                    System.out.println("Добавлен в очередь № 2: " + list.GetTruckAll(i));
//                                }
//                            }
//
//                            else if(ochered1.size() <= ochered2.size()){
//
//                                ochered1.add(i);
//
//                                list.GetTrain(0).setOchered(list.GetTrain(0).getOchered() + 1);
//                                System.out.println("Добавлен в очередь № 1: " + list.GetTruckAll(i));
//                            }
//                            else{
//                                ochered2.add(i);
//
//                                list.GetTrain(1).setOchered(list.GetTrain(1).getOchered() + 1);
//                                System.out.println("Добавлен в очередь № 2: " + list.GetTruckAll(i));
//                            }
        return  false;
    }
}