package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.hostel.Hostel;

import java.util.List;

public class DummyData {
    public static Hostel h1 = new Hostel(
            1,
            "UTown Residence",
            5.0,
            "NUS University Town",
            "The UTown Residence can house up to 1,700 residents, with units for single and married " +
                    "graduate students. The design of the complex emphasizes open common areas, and the architecture " +
                    "fosters a sense of community and cross-disciplinary discussion. Resident ADvisors (RADs) will " +
                    "also be living alongside the graduate residents. The UTown Residence is equipped with seminar " +
                    "rooms and student lounges."
    );
    public static Hostel h2 = new Hostel(
            2,
            "Prince George's Park Residences",
            5.0,
            "Kent Ridge",
            "PGPR is a self-contained student housing estate with an apartment-style living arrangement. " +
                    "Catering to undergraduates as well as graduates, PGPR is designed to maximise the potential for " +
                    "interaction amongst its residents. The single rooms are grouped into clusters of 15, and every " +
                    "resident in the cluster is entrusted with collective responsibility for shared facilities such " +
                    "as a kitchen, dining area, and bathroom facilities."
    );
    public static Hostel h3 = new Hostel(
            3,
            "Cinnamon College",
            5.0,
            "NUS University Town",
            "USP is located in NUS University Town, where it has its very own residential college – " +
                    "Cinnamon College (USP). Within this unique space, USP students develop a sense of belonging " +
                    "through living and learning together, making full use of the facilities and resources. They " +
                    "take classes at the learn lobes adjacent to the college block; they share meals with peers and " +
                    "professors, and sometimes, alumni too; they play sports and organise events in the halls; and " +
                    "they hold numerous thoughtful discussions in the Master’s Commons, floors lounges, suites and " +
                    "their common lounge – fondly known as Chatterbox.");

    public static Comment c1 = new Comment(1, 3, 4.5,
            "nice hostel :)",
            Comment.Type.hostel);

    public static Comment c2 = new Comment(2, 2, 5.0,
            "close to bus station, buildings are a bit old.",
            Comment.Type.hostel);

    public static List<Hostel> hostelList = List.of(h1, h2, h3);
    public static List<Comment> commentList = List.of(c1, c2);
}
