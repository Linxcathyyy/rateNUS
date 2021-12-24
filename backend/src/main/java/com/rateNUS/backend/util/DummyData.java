package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.hostel.Hostel;
import com.rateNUS.backend.image.Image;
import com.rateNUS.backend.image.ImageUtil;
import com.rateNUS.backend.stall.Stall;
import com.rateNUS.backend.studyarea.StudyArea;
import com.rateNUS.backend.user.User;

import java.util.List;

public class DummyData {

    public static Image i1 = new Image(
            "utown1",
            "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/12/Screen-Shot-2018-12-03-at-9.51.14-AM.png",
            ImageUtil.ImageUrlToByteArray("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/12/Screen-Shot-2018-12-03-at-9.51.14-AM.png"));

    public static Image i2 = new Image(
            "utown2",
            "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/08/fr0g1ut2about.jpg",
            ImageUtil.ImageUrlToByteArray("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/08/fr0g1ut2about.jpg"));

    public static Image i3 = new Image(
            "utown3",
            "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2020/05/UTown-Single-Room.jpg",
            ImageUtil.ImageUrlToByteArray("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2020/05/UTown-Single-Room.jpg"));

    public static Image i4 = new Image(
            "pgp1",
            "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr01.jpg",
            ImageUtil.ImageUrlToByteArray("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr01.jpg"));

    public static Image i5 = new Image(
            "pgp2",
            "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr04.jpg",
            ImageUtil.ImageUrlToByteArray("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr04.jpg"));

    public static Image i6 = new Image(
            "pgp3",
            "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr11.jpg",
            ImageUtil.ImageUrlToByteArray("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr11.jpg"));

    public static Image i7 = new Image(
            "cinna1",
            "https://www.usp.nus.edu.sg/wp-content/uploads/2019/07/Cinnamon-College.jpg",
            ImageUtil.ImageUrlToByteArray("https://www.usp.nus.edu.sg/wp-content/uploads/2019/07/Cinnamon-College.jpg"));

    public static Hostel h1 = new Hostel(
            "UTown Residence",
            "NUS University Town",
            "The UTown Residence can house up to 1,700 residents, with units for single and married " +
                    "graduate students. The design of the complex emphasizes open common areas, and the architecture " +
                    "fosters a sense of community and cross-disciplinary discussion. Resident ADvisors (RADs) will " +
                    "also be living alongside the graduate residents. The UTown Residence is equipped with seminar " +
                    "rooms and student lounges.",
            List.of(i1, i2, i3),
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
            List.of(i4, i5, i6),
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
            List.of(i7),
            List.of(Facility.laundry, Facility.lounge)
    );

    public static Stall s1 = new Stall(
            "Chop Chop by PUTIEN",
            "Town Plaza",
            "Seating Capacity: 40\n" +
                    "Term Time & Vacation Operating Hours:\n" +
                    "Mon-Sun, 11.00am-9.00pm (Last order 8.30pm)\n" +
                    "Contact: 63340201\n" +
                    "Nearest Carpark: CREATE Tower",
            List.of("https://middleclass.sg/wp-content/uploads/2021/08/CHOP-CHOP-by-PUTIEN-NUS-UTown-Exterior.jpg",
                    "https://middleclass.sg/wp-content/uploads/2021/08/chopchop_online_menu_Page_1.jpg",
                    "https://middleclass.sg/wp-content/uploads/2021/08/chopchop_online_menu_Page_2.jpg"),
            20,
            300
    );

    public static Stall s2 = new Stall(
            "Lejomo Cafe",
            "Prince George’s Park Residences",
            "Term Time & Vacation Operating Hours:\n" +
                    "Mon-Sun, 11am to 9pm\n" +
                    "Contact: 98202273",
            List.of("https://uci.nus.edu.sg/oca/wp-content/uploads/sites/9/2021/08/Lejomo.jpg",
                    "https://lejomo.com/wp-content/uploads/bb-plugin/cache/WhatsApp-Image-2021-05-22-at-14.28.27-panorama.jpeg"),
            5.99,
            29.99
    );

    public static Stall s3 = new Stall(
            "Nine Fresh",
            "Faculty of Science",
            "Seating Capacity: 9\n" +
                    "Term Time Operating Hours:\n" +
                    "Mon-Sat, 11.00am – 8.00pm\n" +
                    "Sun/PH closed\n" +
                    "Vacation Operating Hours:\n" +
                    "Mon-Sat, 11.00am – 3.00pm\n" +
                    "Sun/PH closed",
            List.of("https://uci.nus.edu.sg/oca/wp-content/uploads/sites/9/2021/02/Nine-Fresh-1-1024x684.jpg"),
            3.00,
            6.50
    );

    public static StudyArea sa1 = new StudyArea(
            "Central Library",
            "Central Library",
            List.of("https://blog.nus.edu.sg/linus/files/2018/05/renovation_masterplan_page_library_artist_impression-1zm153z.jpg",
                    "https://blog.nus.edu.sg/linus/files/2021/02/photomania-630f01939d1f02bbefa829f2847238c1-1.jpg",
                    "https://blog.nus.edu.sg/linus/files/2021/02/Chinese-Library-Study-Area-1.jpg")
    );

    public static StudyArea sa2 = new StudyArea(
            "PGP study rooms",
            "Prince George's Park",
            List.of("https://nus.edu.sg/osa/images/default-source/pgp-house/facilities/reading-room.jpeg?sfvrsn=81d01ab3_2")
    );

    public static StudyArea sa3 = new StudyArea(
            "Study Area outside Starbucks",
            "Utown Starbucks",
            List.of("https://thesmartlocal.com/reviews/wp-content/uploads/2013/11/u-town-national-university-of-singapore-78-1383555230.jpg",
                    "https://miro.medium.com/proxy/0*PoVsXEStAHvJmoIp.jpg")
    );

    public static Comment c1 = new Comment(3, 1, 4,
            "Cinnamon College: nice hostel :)",
            Type.hostel);

    public static Comment c2 = new Comment(2, 1, 5,
            "Prince George's Park Residences: close to bus station, buildings are a bit old.",
            Type.hostel);

    public static Comment c3 = new Comment(1, 1, 5,
            "UTown: Many food here.",
            Type.hostel);

    public static Comment c4 = new Comment(2, 1, 5,
            "Prince George's Park Residences: Very chill residence, not much hustle",
            Type.hostel);

    public static Comment c5 = new Comment(3, 1, 4,
            "Cinnamon College: Near StarBucks, where I can go study.",
            Type.hostel);

    public static Comment c6 = new Comment(1, 1, 3,
            "Dummy canteen comment",
            Type.stall);

    public static Comment c7 = new Comment(1, 1, 0,
            "I dun wanna study alr...",
            Type.studyArea);

    public static Comment c8 = new Comment(2, 1, 0,
            "Needs pgp room card to access.",
            Type.studyArea);

    public static User u1 = new User(
            "user1", "user@example.email", "Password123!", true);

    public static List<Hostel> hostelList = List.of(h1, h2, h3);
    public static List<Stall> stallList = List.of(s1, s2, s3);
    public static List<StudyArea> studyAreaList = List.of(sa1, sa2, sa3);
    public static List<Comment> commentList = List.of(c1, c2, c3, c4, c5, c6, c7, c8);
    public static List<User> userList = List.of(u1);
    public static List<Image> imageList = List.of(i1, i2, i3, i4, i5, i6, i7);
}
