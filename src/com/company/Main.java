package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        List list = new List();
        int kol = 30;
        boolean t = false;
        int p = 0;
        double sum = 0.0;
        int num = 0;
        ArrayList<Double> Cost = new ArrayList<>();
        ArrayList<Double> Time = new ArrayList<>();
        ArrayList<Double> Sum = new ArrayList<>();

        Cost.add(5.60);
        Cost.add(8.40);
        Cost.add(11.2);

        while(Time.size() != kol) {

            list.getTrucks_all().clear();
            list.getTrucks1().clear();
            list.getTrucks2().clear();
            list.getExcavators1().clear();
            list.getExcavators2().clear();
            list.getTrains().clear();

            Transportation transportation1 = new Transportation();
            Transportation transportation2 = new Transportation();
            Transportation transportation3 = new Transportation();

            list.getTrucks1().sort(new TruckComparator());
            list.getTrucks2().sort(new TruckComparator());
            list.getTrains().clear();

            t = false;
            p = 0;
            for(int i = 0; i < list.getTrucks1().size(); i++){
                list.GetTruckDump1(i).setTime_pribitiya_dump(0.0);
            }
            for(int i = 0; i < list.getTrucks2().size(); i++){
                list.GetTruckDump2(i).setTime_pribitiya_dump(0.0);
            }

            while (!t) {
                transportation1.PerevozkaFromOtvalFirst(1, 5, p, list);

                transportation2.PerevozkaFromOtvalSecond(1, 7, p, list);

                list.getTrucks_all().sort(new TruckComparator());

                System.out.println("\t\tГрузовики на станции:\n\n");
                for (int i = 0; i < list.getTrucks_all().size(); i++) {
                    System.out.println(list.GetTruckAll(i) + "\n");
                }

                t = transportation3.CreateSostav(list, p, num);
                list.getTrucks_all().clear();
                //for (int i = 0; i < list.getTrucks_all().size(); i++) {
                //System.out.println(list.GetTruckAll(i) + "\n");
                //list.getTrucks_all().remove(i);
                // }
                p++;
            }
            Time.add((transportation3.getLast_time() - transportation3.getFirst_time()));
        }

        for(int i = 0; i < Time.size(); i++) {
            System.out.println("SUMMA = " + Time.get(i));
            sum += Time.get(i);
        }

        Sum.add(sum / kol);
        System.out.println(Sum.get(0));

        Time.clear();
        sum=0;

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        while(Time.size() != kol) {

            list.getTrucks_all().clear();
            list.getTrucks1().clear();
            list.getTrucks2().clear();
            list.getExcavators1().clear();
            list.getExcavators2().clear();
            list.getTrains().clear();

            Transportation transportation4 = new Transportation();
            Transportation transportation5 = new Transportation();
            Transportation transportation6 = new Transportation();

            t = false;
            p = 0;
            for (int i = 0; i < list.getTrucks1().size(); i++) {
                list.GetTruckDump1(i).setTime_pribitiya_dump(0.0);
            }
            for (int i = 0; i < list.getTrucks2().size(); i++) {
                list.GetTruckDump2(i).setTime_pribitiya_dump(0.0);
            }

            while (!t) {
                transportation4.PerevozkaFromOtvalFirst(2, 5, p, list);

                transportation5.PerevozkaFromOtvalSecond(1, 7, p, list);

                list.getTrucks_all().sort(new TruckComparator());

                for (int i = 0; i < list.getTrucks_all().size(); i++) {
                    System.out.println(list.GetTruckAll(i) + "\n");
                }
                t = transportation6.CreateSostav(list, p, num);

                list.getTrucks_all().clear();
                p++;
            }

            Time.add((transportation6.getLast_time() - transportation6.getFirst_time()));
        }

        for(int i = 0; i < Time.size(); i++) {
            System.out.println("SUMMA = " + Time.get(i));
            sum += Time.get(i);
        }

        Sum.add(sum / kol);
        System.out.println(Sum.get(1));
        Time.clear();

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        sum=0;

        while(Time.size() != kol) {

            list.getTrucks_all().clear();
            list.getTrucks1().clear();
            list.getTrucks2().clear();
            list.getExcavators1().clear();
            list.getExcavators2().clear();
            list.getTrains().clear();

            Transportation transportation7 = new Transportation();
            Transportation transportation8 = new Transportation();
            Transportation transportation9 = new Transportation();

            list.getTrucks1().sort(new TruckComparator());
            list.getTrucks2().sort(new TruckComparator());
            list.getTrains().clear();

            t = false;
            p = 0;
            for (int i = 0; i < list.getTrucks1().size(); i++) {
                list.GetTruckDump1(i).setTime_pribitiya_dump(0.0);
            }
            for (int i = 0; i < list.getTrucks2().size(); i++) {
                list.GetTruckDump2(i).setTime_pribitiya_dump(0.0);
            }

            while (!t) {
                transportation7.PerevozkaFromOtvalFirst(3, 5, p, list);

                transportation8.PerevozkaFromOtvalSecond(1, 7, p, list);

                list.getTrucks_all().sort(new TruckComparator());

                for (int i = 0; i < list.getTrucks_all().size(); i++) {
                    System.out.println(list.GetTruckAll(i) + "\n");
                }
                t = transportation9.CreateSostav(list, p, num);

                list.getTrucks_all().clear();
                p++;
            }

            Time.add((transportation9.getLast_time() - transportation9.getFirst_time()));
        }

        for(int i = 0; i < Time.size(); i++) {
            System.out.println("SUMMA = " + Time.get(i));
            sum += Time.get(i);
        }

        Sum.add(sum / kol);
        System.out.println(Sum.get(2));

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("|                                           | Стоимость (млн. руб) | " + "Время загрузки состава (часов)|");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for(int i = 0; i < 3; i++){
            if(i < 2) {
                System.out.println("| " + (i + 1) + " груз. на 1 отвале и 1 груз. на 2 отвале " + "| " + Cost.get(i) + 0 + "                 | " + df.format(Sum.get(i)) + "                         |");
            }

            else{
                System.out.println("| " + (i + 1) + " груз. на 1 отвале и 1 груз. на 2 отвале " + "| " + Cost.get(i) + "                 | " + df.format(Sum.get(i)) + "                         |");
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}