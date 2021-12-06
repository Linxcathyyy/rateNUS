package com.rateNUS.backend.util;

import com.rateNUS.backend.hostel.Hostel;

import java.util.List;

public class DummyData {
    public static Hostel h1 = new Hostel(1, "Hostel 1", 5.0, "Location A", "First hostel");
    public static Hostel h2 = new Hostel(2, "Hostel 2", 4.3, "Location B", "Second hostel");
    public static Hostel h3 = new Hostel(3, "Hostel 3", 3.6, "Location C", "Third hostel");

    public static List<Hostel> hostelList = List.of(h1, h2, h3);
}
