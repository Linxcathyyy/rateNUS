package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.hostel.Hostel;

import java.util.List;

public class DummyData {
    public static Hostel h1 = new Hostel(
            "UTown Residence",
            "NUS University Town",
            "The UTown Residence can house up to 1,700 residents, with units for single and married " +
                    "graduate students. The design of the complex emphasizes open common areas, and the architecture " +
                    "fosters a sense of community and cross-disciplinary discussion. Resident ADvisors (RADs) will " +
                    "also be living alongside the graduate residents. The UTown Residence is equipped with seminar " +
                    "rooms and student lounges.",
            List.of("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/12/Screen-Shot-2018-12-03-at-9.51.14-AM.png",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/08/fr0g1ut2about.jpg",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2020/05/UTown-Single-Room.jpg"),
            List.of(Facility.campusSecurity, Facility.laundry, Facility.lounge, Facility.swimmingPool, Facility.wifi)
    );

    public static Hostel h2 = new Hostel(
            "Prince George's Park Residences",
            "Kent Ridge",
            "PGPR is a self-contained student housing estate with an apartment-style living arrangement. " +
                    "Catering to undergraduates as well as graduates, PGPR is designed to maximise the potential for " +
                    "interaction amongst its residents. The single rooms are grouped into clusters of 15, and every " +
                    "resident in the cluster is entrusted with collective responsibility for shared facilities such " +
                    "as a kitchen, dining area, and bathroom facilities.",
            List.of("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr01.jpg",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr04.jpg",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr11.jpg"),
            List.of(Facility.basketballCourt, Facility.carPark, Facility.campusSecurity, Facility.convenienceStore,
                    Facility.fitnessCentre, Facility.laundry, Facility.lounge, Facility.multipurposeHall,
                    Facility.musicRoom, Facility.wifi)
    );

    public static Hostel h3 = new Hostel(
            "Cinnamon College",
            "NUS University Town",
            "USP is located in NUS University Town, where it has its very own residential college – " +
                    "Cinnamon College (USP). Within this unique space, USP students develop a sense of belonging " +
                    "through living and learning together, making full use of the facilities and resources. They " +
                    "take classes at the learn lobes adjacent to the college block; they share meals with peers and " +
                    "professors, and sometimes, alumni too; they play sports and organise events in the halls; and " +
                    "they hold numerous thoughtful discussions in the Master’s Commons, floors lounges, suites and " +
                    "their common lounge – fondly known as Chatterbox.",
            List.of("https://www.usp.nus.edu.sg/wp-content/uploads/2019/07/Cinnamon-College.jpg"),
            List.of(Facility.laundry, Facility.lounge)
    );

    public static Comment c1 = new Comment(3, 4,
            "Cinnamon College: nice hostel :)",
            Type.hostel);

    public static Comment c2 = new Comment(2, 5,
            "Prince George's Park Residences: close to bus station, buildings are a bit old.",
            Type.hostel);

    public static Comment c3 = new Comment(1, 5,
            "UTown: Many food here.",
            Type.hostel);

    public static Comment c4 = new Comment(2, 5,
            "Prince George's Park Residences: Very chill residence, not much hustle",
            Type.hostel);

    public static Comment c5 = new Comment(3, 4,
            "Cinnamon College: Near StarBucks, where I can go study.",
            Type.hostel);

    public static Comment c6 = new Comment(1, 3,
            "Dummy canteen comment",
            Type.canteen);

    public static Comment c7 = new Comment(1, 0,
            "I dun wanna study alr...",
            Type.studyArea);

    public static List<Hostel> hostelList = List.of(h1, h2, h3);
    public static List<Comment> commentList = List.of(c1, c2, c3, c4, c5, c6, c7);
}
