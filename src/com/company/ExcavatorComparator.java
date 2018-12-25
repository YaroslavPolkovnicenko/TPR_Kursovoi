package com.company;

import java.util.Comparator;

public class ExcavatorComparator implements Comparator<Excavator> {

    @Override
    public int compare(Excavator e1, Excavator e2) {
        if(e1.getTime_osv() >= e2.getTime_osv()){
            return 1;
        }
        else return -1;
    }
}
