package com.krikaliov.earthdistances;

public class Main {
    public static void main(String[] args) {
        EarthData data = new EarthData();
        EarthDataView dataView = new EarthDataView(data);

        System.out.println(dataView);
    }
}
