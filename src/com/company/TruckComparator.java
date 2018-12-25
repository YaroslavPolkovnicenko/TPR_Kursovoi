package com.company;

import java.util.Comparator;

public class TruckComparator implements Comparator<Truck> {

    @Override
    public int compare(Truck t1, Truck t2) {
        if(t1.getTime_pribitiya_zd() >= t2.getTime_pribitiya_zd()){
            return 1;
        }
        else return -1;
    }

}
