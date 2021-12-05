package com.rateNUS.backend.canteen;

import java.util.List;

public class DummyCanteenData {

    public static Canteen c1 = new Canteen(1, "Canteen 1", "Location A", "First canteen");
    public static Canteen c2 = new Canteen(2, "Canteen 2", "Location B", "Second canteen");
    public static Canteen c3 = new Canteen(3, "Canteen 3", "Location C", "Third canteen");

    public static List<Canteen> canteenList = List.of(c1, c2, c3);

}
