package com.company.DistributionLaw;

public class NormalLaw {

    private RandomKsi r = new RandomKsi();

    public double getUniformByRange(double a, double b) {

        return a + (b - a) * r.GetKsi();

    }

    public double getNormalY(double mo, double sko) {

        int n = 12;
        double y = 0;

        for (int i = 0; i < n; i++) {

            double a = mo / n - (Math.sqrt(3) * sko) / n;
            double b = mo / n + (Math.sqrt(3) * sko) / n;
            double uniformY = getUniformByRange(a, b);

            y += uniformY;
        }
        return y;
    }
}