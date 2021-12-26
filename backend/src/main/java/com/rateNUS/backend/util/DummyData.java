package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.hostel.Hostel;
import com.rateNUS.backend.stall.Stall;
import com.rateNUS.backend.studyarea.StudyArea;
import com.rateNUS.backend.user.User;

import java.util.List;

public class DummyData {
    // source: https://nus.edu.sg/osa/eusoffhall/
    public static Hostel h1 = new Hostel(
            "Eusoff Hall",
            "10 Kent Ridge Drive",
            "Eusoff Hall, founded in 1988, has its beginning in Eusoff College, the first female residential hall of the University of Malaya in Singapore, at Bukit Timah campus. In November 1958, Eusoff College was officially declared open by Mrs Maude Scott, one of the earliest woman graduates of Raffles College. The very first principal was Dr Ruth Wong while the first President of the JCRC was Ms Ng Kim Lan.\n" +
                    "\n" +
                    "During the decade of the 80s, a number of changes swept through Eusoff College. A 26-year-old tradition was broken when Eusoff went co-educational in 1984. In June 1988, Eusoff Hall at Kent Ridge campus began its operations and was officially opened by the then President Wee Kim Wee on 5 August 1989. It inherited the legacy of the life at Eusoff College, as many of the Eusoff College residents moved to the new Eusoff Hall. Despite the change of name, residents who moved from Eusoff College to Eusoff Hall were determined to carry on with the best traditions of the former.\n" +
                    "\n" +
                    "Eusoff College continued to operate as a postgraduate students' hostel at Evans Road until 2001. After Dr Lim Kit Boey stood down as principal, Professor Lee Kok Onn took over as principal until its closing on the evening of 24 March 2001.",
            List.of("https://nus.edu.sg/osa/images/default-source/eusoff-hall/home-01.jpg",
                    "https://nus.edu.sg/osa/images/default-source/eusoff-hall/about-eusoff/facilities/blue-oyster-2.jpg",
                    "https://nus.edu.sg/osa/images/default-source/eusoff-hall/about-eusoff/facilities/dining-hall.jpg"),
            List.of(Facility.basketballCourt, Facility.danceStudio, Facility.fitnessCentre, Facility.laundry,
                    Facility.lounge, Facility.multipurposeHall, Facility.pantry, Facility.poolRoom,
                    Facility.seminarRoom, Facility.squashCourt)
    );

    // source: https://nus.edu.sg/osa/kentridgehall/
    public static Hostel h2 = new Hostel(
            "Kent Ridge Hall",
            "10 Heng Mui Keng Terrace",
            "King Edward Hall at Kent Ridge (KE Hall at KR) was first opened unofficially in January 1979 to accept its first batch of residents. The name King Edward Hall was chosen to restore the historical significance related to the university. To avoid confusion, ‘at KR’ was included until King Edward Hall at Sepoy Lines closed. \n" +
                    "\n" +
                    "King Edward hall at Kent Ridge was officially declared open on 5th December 1981 by then- President of Singapore and Chancellor of NUS, Mr. Devan Nair. Four years later, in December 1985, the hall embraced a new name, departing from KEKR to Kent Ridge Hall in order to create a new identity and tradition free from the shadow of KEVII. Pro-chancellor at NUS, Dr. Justice A. P. Rajah, officiated the Hall Renaming Ceremony. \n" +
                    "\n" +
                    "The following year marked the unveiling of the new Hall Crest and Hall Motto by President Wee Kim Wee. The new Hall Motto was first proposed by residents of the hall and then discussed and modified to take the form it has today- ‘Seek, Strive and Excel’. The motto expressed the essence of the hall and its residents, the continual seeking for knowledge and truth, the striving for high ideals in spite of difficulties, and the excellence which marks all that is undertaken by us to do. The source of our hall spirit and a driving force of our achievements is our strong family culture. It is believed that the camaraderie forged during our stay here will pave the way for our renewed goal, that of being “the Family Hall ever striding for excellence”, reinforced by the Family Song introduced in 2002. \n" +
                    "\n" +
                    "In November 2002, KR was relocated from its original site in Lower Kent Ridge Road to its current location at Heng Mui Keng Terrace. The move was symbolically represented by having the then-residents walk over to the new building, which was officially opened on 3rd January 2004 by former President S. R. Nathan.\n" +
                    "\n" +
                    "Despite the move, the culture of Kent Ridge Hall has not only survived, but thrived in its new home. From outstanding results in FLAG and RAG, yearly productions and most recently, becoming champions of the Inter-Hall Games (IHG) in 2014, it become clear that the spirit of excellence KR embodies remains ever-present in the hall.",
            List.of("https://nus.edu.sg/osa/images/default-source/kent-ridge-hall/facilities/student/21211754b289243e484cb3be5aba022a0.jpg",
                    "https://nus.edu.sg/osa/images/default-source/kent-ridge-hall/facilities/amenities/amenities-lounge.jpg",
                    "https://nus.edu.sg/osa/images/default-source/kent-ridge-hall/facilities/amenities/amenities-multi-purpose-hall.jpg"),
            List.of(Facility.fitnessCentre, Facility.laundry, Facility.lounge, Facility.meetingRoom,
                    Facility.multipurposeHall, Facility.pantry)
    );

    // source: https://nus.edu.sg/osa/keviihall
    public static Hostel h3 = new Hostel(
            "King Edward VII Hall",
            "1A Kent Ridge Road",
            "25th November 1988 marked the official grand opening ceremony of KEVII Hall at the NUS Kent Ridge campus, graced by their President Wee Kim Wee amidst much celebrations. The new King Edward VII Hall took in the most number of non-medical students in its history in order to broaden the student profile of its residents, a practice evident in the diversity of current KEVII hall residents. The sprawling new hostel overlooks the National University Hospital.",
            List.of("https://nus.edu.sg/osa/images/default-source/kevii-hall/banners/40773051_735925123423298_3456762337506099200_o.jpg",
                    "https://nus.edu.sg/osa/images/default-source/kevii-hall/facilities/block-lounge.png",
                    "https://nus.edu.sg/osa/images/default-source/kevii-hall/facilities/dining-hall-updated.png"),
            List.of(Facility.basketballCourt, Facility.danceStudio, Facility.fitnessCentre, Facility.laundry,
                    Facility.lounge, Facility.meetingRoom, Facility.multipurposeHall, Facility.musicRoom,
                    Facility.pantry, Facility.squashCourt, Facility.studyRoom, Facility.tennisCourt)
    );

    // source: https://www.nus.edu.sg/osa/pgphouse
    // image copy right: © James Hii 2018
    public static Hostel h4 = new Hostel(
            "PGP House",
            "35 Prince George's Park",
            "PH provides a balanced one-year living and learning programme for freshmen, and aims to help freshmen settle into varsity life by providing a familial and well-supported residential environment.\n" +
                    "\n" +
                    "The signature Peer Mentorship Programme offers freshmen an opportunity to receive academic guidance and student life-related care from a selected group of dedicated seniors (i.e. \"Peer Mentors\"). Resident Fellows and Resident Assistants are also readily available to provide pastoral care and support.\n" +
                    "\n" +
                    "PH believes in building an inclusive and well-integrated community, whereby residents are encouraged to partake in house-wide events and programmes to build friendships and exchange cultural ideas.",
            List.of("https://nus.edu.sg/osa/images/default-source/pgp-house/about-us/pgp-house-blocks.jpg",
                    "https://www.nus.edu.sg/osa/images/default-source/pgp-house/facilities/pgp-house-58.jpg",
                    "https://www.nus.edu.sg/osa/images/default-source/pgp-house/facilities/badminton-courts.tmb-medium.jpg"),
            List.of(Facility.badmintonCourt, Facility.basketballCourt, Facility.carPark, Facility.campusSecurity,
                    Facility.convenienceStore, Facility.fitnessCentre, Facility.laundry, Facility.lounge,
                    Facility.musicRoom, Facility.pantry, Facility.seminarRoom, Facility.studyRoom,
                    Facility.tennisCourt, Facility.wifi)
    );

    // source: https://nus.edu.sg/osa/raffleshall
    public static Hostel h5 = new Hostel(
            "Raffles Hall",
            "19 Kent Ridge Crescent",
            "Founded in 1958, Raffles Hall was one of the first halls to be established in the National University of Singapore (NUS).\n" +
                    "\n" +
                    "Raffles Hall was originally located in Nassim Road, Bukit Timah, alongside Eusoff College. At that time, Raffles Hall was an all-boys hall. Due to an increase in the number of female students, Raffles Hall officially became co-ed in 1975.\n" +
                    "\n" +
                    "In 1981, NUS shifted from the Bukit Timah campus to the current Kent Ridge campus. The establishment of two new halls there took a toll on Raffles Hall’s residential numbers. Consequently, Raffles Hall bade farewell to Nassim Road and moved to the Kent Ridge Campus in 1984. The new Raffles Hall site was almost twice the size of the old Nassim Road site.\n" +
                    "\n" +
                    "Raffles Hall has its roots in Raffles College, which was one of the institutes of higher learning that merged to eventually form the National University of Singapore (the other being King Edward VII college). Raffles Hall was also modelled after the colleges at Oxford and Cambridge, and hence many of their traditions trickled down to us. The positions of the Master, Resident Fellows, Junior Common Room Committees (JCRCs) and Senior Common Room Committees (SCRCs) all stemmed from those origins. And although “Raffles” is a common name for many institutions and organisations in Singapore, Raffles Hall is in no way affiliated to them.",
            List.of("https://nus.edu.sg/osa/images/default-source/raffles-hall/blocks/header.jpg",
                    "https://nus.edu.sg/osa/images/default-source/raffles-hall/amenities/upper-lounge.jpg",
                    "https://nus.edu.sg/osa/images/default-source/raffles-hall/amenities/comm-hall-e1432982818222.jpg"),
            List.of(Facility.basketballCourt, Facility.danceStudio, Facility.fitnessCentre, Facility.lounge,
                    Facility.multipurposeHall, Facility.musicRoom, Facility.pantry, Facility.poolRoom)
    );

    // source: https://nus.edu.sg/osa/sheareshall
    public static Hostel h6 = new Hostel(
            "Sheares Hall",
            "20 Heng Mui Keng Terrace",
            "The merger of the University of Singapore and Nanyang University to become the National University of Singapore (NUS) in 1980 and its relocation to Kent Ridge led to the closure of DRH on 22 November 1981. The houses later became rental houses to the public from 1981 until 2007 when NUS leased some of them from the Singapore Land Authority and converted them into hostel for students. Today, these houses are known as College Green.\n" +
                    "After 29 years of existence, the oldest hostel was renamed Sheares Hall in 1982 when it was relocated to the National University of Singapore campus at Kent Ridge after the late Benjamin Henry Sheares, then NUS Chancellor. The only DRH remnant lies in the motto for Sheares Hall: Dare to Reach the Highest which bears the initials of DRH. Two other visible reminders at Sheares Hall include a mural painted by Rais bin Yatim in 1971, hung in the dining hall at DRH until its closure. The other is the publication of The Psyche yearbook which was continued by Sheares Hall.\n" +
                    "In 2002, Sheares Hall moved from its original site at Lower Kent Ridge Road to its current location at 20 Heng Mui Keng Terrace. To this date, the mural from DRH by Rais bin Yatim may be found on the wall of the Hall Administration Office and The Psyche yearbook managed by Sheares Media, continues to be published annually.",
            List.of("https://nus.edu.sg/osa/images/default-source/sheares/values/1.png",
                    "https://nus.edu.sg/osa/images/default-source/sheares/facilities-and-rooms/3.png",
                    "https://nus.edu.sg/osa/images/default-source/sheares/facilities-and-rooms/5.png"),
            List.of(Facility.danceStudio, Facility.lounge, Facility.musicRoom, Facility.pantry,
                    Facility.seminarRoom, Facility.studyRoom)
    );

    // source: https://nus.edu.sg/osa/temasekhall
    public static Hostel h7 = new Hostel(
            "Temasek Hall",
            "12 Kent Ridge Drive",
            "Temasek Hall (TH) welcomed its pioneer batch of residents in July 1988. As a completely new residential Hall on campus then, TH had to establish its brand of quality, strength and culture. This came about quickly and naturally through the motivation and resolve of its residents. The Hall crest, motto and Hall anthem were all contributed by residents. TH proceeded to secure its place among the longer established Halls and within its first few years secured awards in the ‘Rag & Flag’ competition and the inter-Hall sports competition.\n" +
                    "\n" +
                    "Emphasising a fun loving and innovative culture, TH is not only well known for its legacy of sporting triumphs, but also for its achievements in staging annual productions and charity\n" +
                    "activities.\n" +
                    "\n" +
                    "TH envisions itself as a Hall of choice where residents are inspired to develop their talents, explore their potential and strive for success in a conducive learning and living environment. It offers a wholesome and vibrant Hall life which builds character and inculcates values such as respect for individuals, integrity, creativity and excellence.\n" +
                    "\n" +
                    "Residents have a saying that epitomizes the lifestyle of Temasek Hall: “Some call it a Hostel, we call it Home”.",
            List.of("https://nus.edu.sg/osa/images/default-source/temasek-hall/common-banner.jpg",
                    "https://nus.edu.sg/osa/images/default-source/temasek-hall/facilities/amenities/lounge.jpg",
                    "https://nus.edu.sg/osa/images/default-source/temasek-hall/facilities/amenities/807b7cfe-2005-4b21-9c95-9ca963fef966_1_105_c.jpg"),
            List.of(Facility.basketballCourt, Facility.danceStudio, Facility.fitnessCentre, Facility.laundry,
                    Facility.lounge, Facility.multipurposeHall, Facility.pantry, Facility.seminarRoom,
                    Facility.squashCourt)
    );

    private static List<Facility> utownCollegesFacilities = List.of(
            Facility.badmintonCourt, Facility.carPark, Facility.campusSecurity, Facility.convenienceStore,
            Facility.fitnessCentre, Facility.laundry, Facility.lounge, Facility.multipurposeHall,
            Facility.pantry, Facility.seminarRoom, Facility.wifi
    );

    // source: https://www.usp.nus.edu.sg/life-at-usp/usp-housing-and-support/cinnamon-college-usp/
    public static Hostel h8 = new Hostel(
            "Cinnamon College (USP)",
            "18 College Ave E, Cinnamon West Learn Lobe",
            "USP is located in NUS University Town, where it has its very own residential college – Cinnamon College (USP). Within this unique space, USP students develop a sense of belonging through living and learning together, making full use of the facilities and resources. They take classes at the learn lobes adjacent to the college block; they share meals with peers and professors, and sometimes, alumni too; they play sports and organise events in the halls; and they hold numerous thoughtful discussions in the Master’s Commons, floors lounges, suites and their common lounge – fondly known as Chatterbox.\n" +
                    "\n" +
                    "All USP students are expected to fulfil a two-year commitment of their living in the USP residential college (Cinnamon College). This will be in year one and a second year of choice. As USP students choose to do their second year of residential living, they have the freedom and flexibility to build their own unique experiences.",
            List.of("https://www.usp.nus.edu.sg/wp-content/uploads/2019/07/Cinnamon-College.jpg",
                    "http://www.usp.nus.edu.sg/wp-content/uploads/2019/07/resources-chatterbox-04.jpg"),
            utownCollegesFacilities
    );

    // source: https://tembusu.nus.edu.sg
    public static Hostel h9 = new Hostel(
            "Tembusu College",
            "28 College Avenue East",
            "Tembusu College is one of four residential colleges at University Town (UTown), an extension of the main NUS campus at Kent Ridge. The colleges, the first of their kind in Singapore and Southeast Asia, provide an exciting alternative to undergraduate campus living.\n" +
                    "\n" +
                    "In addition to co-curricular activities, *most incoming freshmen will take five of their classes at the College over a two year period, helping them fulfill their General Education requirements. Some students from non-modular faculties (whose course requirements may be reduced or waived), and students on semestral exchange programmes add to the residential mix. Students from any NUS faculty are eligible to apply.\n" +
                    "\n" +
                    "Freshmen normally reside in Tembusu College for two years *during which they complete our five-module schedule. Additionally, the College provides many opportunities for learning on a daily basis.\n" +
                    "\n" +
                    "Faculty members and visiting scholars from prestigious overseas institutions also live at the College and take meals in the same dining hall as students. There are regular ‘Master’s Teas’ and other events at which small groups of students can interact with distinguished visitors in a relaxed, non-classroom setting.\n" +
                    "\n" +
                    "Tembusu College students also enjoy easy access to a wide array of UTown facilities, and are invited to participate in a myriad of exciting sporting and social activities. The three buildings of the College house approximately 600 undergraduates, a small number of graduate student advisors, as well as Residential Fellows, their families, and distinguished guests. UTown is connected to the Kent Ridge campus by frequent shuttle buses and a pedestrian bridge. Many regular NUS classes, especially those for freshmen, also take place at UTown’s Education Resource Centre and the Stephen Riady Centre, both just opposite Tembusu College.",
            List.of("https://tembusu.nus.edu.sg/images/headers/Admission.jpg",
                    "https://tembusu.nus.edu.sg/images/content/Colleg2.jpg"),
            utownCollegesFacilities
    );

    // source: https://capt.nus.edu.sg
    // image 1 source: https://www.dpa.com.sg/projects/residentialcollege4andcollegeofaliceandpetertan/
    public static Hostel h10 = new Hostel(
            "College of Alice & Peter Tan",
            "8 College Avenue East",
            "Founded in 2012, the College of Alice & Peter Tan (CAPT) is a Residential College made up of undergraduate students, faculty and administrative staff at the National University of Singapore. Together with three other residential colleges, CAPT is located within University Town and within close proximity to the wide range of learning, sports and performing arts facilities.\n" +
                    "\n" +
                    "The College of Alice & Peter Tan is distinguished by its vision of helping students engage with the community within and outside of NUS, and encouraging them to apply their knowledge to address issues that are important to society. The College consciously weaves this theme of active citizenship and community engagement through its curriculum and other aspects of the student experience.\n" +
                    "\n" +
                    "The University Town College Programme (UTCP) curriculum at CAPT is designed to help students become critical thinkers, articulate communicators, and individuals who can deal with complexity and uncertainty. In addition, through various informal learning activities, students have the opportunity to acquire a greater awareness of the diverse communities around us, develop empathy, leadership and organisational skills, and cultivate an informed view of issues relevant to society.\n" +
                    "\n" +
                    "We believe that active citizenship in the context of an educational institution is fundamentally about being equipped for participation in society. Our hope is that the outcome of this living-learning programme will be the shaping of knowledge, skills and values needed to make a difference, and that ultimately our students will apply them when they graduate to work across a wide range of careers and professions.",
            List.of("https://www.dpa.com.sg/sites/dpa/img/DPA%20Singapore%20Projects/NUS%20Residential%20College%204%20And%20College%20Of%20Alice%20and%20Peter%20Tan/DPA_ResidentialCollege4AndCollegeOfAlice&PeterTan_Carousel1_new_20180515055238.jpg",
                    "https://capt.nus.edu.sg/wp-content/uploads/2017/03/capt_masthead_experience_living_room_2.jpg"),
            utownCollegesFacilities
    );

    // source: https://rc4.sg
    public static Hostel h11 = new Hostel(
            "Residential College 4",
            "6 College Avenue East",
            "Residential College 4 is located in NUS University Town, and offers undergraduate students the opportunity to combine the vibrancy of campus living with an exciting multidisciplinary academic programme.\n" +
                    "\n" +
                    "Residential College 4 aims to integrate academic and personal life in a residential setting to develop systems thinking using interdisciplinary issues, foster all round student development through in and out-of-class learning experiences and nurture a spirit of concern and care in students for all living beings and the environment.",
            List.of("https://rc4.sg/img/building.jpg"),
            utownCollegesFacilities
    );

    // source: https://rvrc.nus.edu.sg
    public static Hostel h12 = new Hostel(
            "Ridge View Residential College",
            "25 Lower Kent Ridge Road",
            "RVRC is housed in the former Ridge View Residences at NUS. The visually distinct brick-clad interconnected buildings are nestled against the lush backdrop of the Kent Ridge Forest, creating the 'kampung' atmosphere the college prides itself for. The college has a distinctive laid back atmosphere with its landscaped open courtyards, heritage trees, and views out to the forest behind. Quite unlike the typically dense Singaporean urban setting, the walk-up buildings are interspersed with generous and verdant green spaces for residents to interact in, and enjoy nature. These spaces come alive with student activities, as well as a variety of resident and visiting fauna from the forest.\n" +
                    "\n" +
                    "The college was formally established in April 2014, under the leadership and vision of the inaugural College Master Professor Adekunle Adeyeye. Along with the Director of Studies, Associate Professor Lee Kooi Cheng, he established the RVRC Programme and welcomed the pioneer RVRC student cohort to the college in Aug 2014.",
            List.of("https://rvrc.nus.edu.sg/wp-content/uploads/2018/01/about-us_history.jpg",
                    "https://rvrc.nus.edu.sg/wp-content/uploads/bb-plugin/cache/Facilities_Student-Lounge-1-square.jpg",
                    "https://rvrc.nus.edu.sg/wp-content/uploads/bb-plugin/cache/Facilities_Dining-Hall-1-square.jpg"),
            List.of(Facility.lounge, Facility.meetingRoom, Facility.multipurposeHall, Facility.musicRoom,
                    Facility.seminarRoom)
    );

    // source: https://uci.nus.edu.sg/ohs/future-residents/graduates/prince-georges-park-residences/about-pgpr/
    public static Hostel h13 = new Hostel(
            "Prince George's Park Residences",
            "27 Prince George's Park",
            "The Prince George’s Park Residences (PGPR) is located on the eastern end of the Kent Ridge Campus and is within close proximity to Science Park and Kent Ridge MRT Station.\n" +
                    "\n" +
                    "With 3000 local and international students living here, PGPR embraces global community living and racial harmony.\n" +
                    "\n" +
                    "PGPR is a self-contained student housing estate with an apartment-style living arrangement. Catering to undergraduates as well as graduates, PGPR is designed to maximise the potential for interaction amongst its residents. The single rooms are grouped into clusters of 15, and every resident in the cluster is entrusted with collective responsibility for shared facilities such as a kitchen, dining area, and bathroom facilities.",
            List.of("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr01.jpg",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr04.jpg",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/07/pgpr11.jpg"),
            List.of(Facility.basketballCourt, Facility.carPark, Facility.campusSecurity, Facility.convenienceStore,
                    Facility.fitnessCentre, Facility.laundry, Facility.lounge, Facility.multipurposeHall,
                    Facility.musicRoom, Facility.pantry, Facility.seminarRoom, Facility.tennisCourt, Facility.wifi)
    );

    // source: https://uci.nus.edu.sg/ohs/future-residents/graduates/utown/about-utown/
    public static Hostel h14 = new Hostel(
            "UTown Residence",
            "36 College Ave E, North Tower",
            "UTown supports the university’s growing graduate community with dedicated living facilities.\n" +
                    "\n" +
                    "The UTown Residence can house up to 1,700 residents, with units for single and married graduate students. The design of the complex emphasizes open common areas, and the architecture fosters a sense of community and cross-disciplinary discussion. Resident ADvisors (RADs) will also be living alongside the graduate residents. The UTown Residence is equipped with seminar rooms and student lounges.",
            List.of("https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/12/Screen-Shot-2018-12-03-at-9.51.14-AM.png",
                    "https://uci.nus.edu.sg/ohs/wp-content/uploads/sites/3/2018/08/fr0g1ut2about.jpg"),
            List.of(Facility.carPark, Facility.campusSecurity, Facility.convenienceStore, Facility.fitnessCentre,
                    Facility.laundry, Facility.lounge, Facility.pantry, Facility.seminarRoom, Facility.wifi)
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
}
