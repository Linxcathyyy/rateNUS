import Vue from "vue";
import VueRouter from "vue-router";
import Hostels from "../views/Hostels.vue";
import Canteens from "../views/Canteens.vue";
import StudyAreas from "../views/StudyAreas.vue";
import ItemViewMore from "../components/commons/ItemViewMore.vue";
import RegistrationConfirm from "../components/authentication/RegistrationConfirm.vue";
import MyComments from "../views/MyComments.vue";
import ResetPassword from "../components/authentication/ResetPassword.vue";
import FAQ from "../views/FAQ.vue";
import Team from "../views/Team.vue";
import About from "../views/About.vue";
import Contribute from "../views/Contribute.vue";
import ManageItems from "../views/ManageItems.vue";

Vue.use(VueRouter);

const routes = [

  {
    path: "/hostels",
    name: "Hostels",
    component: Hostels,
    meta: {
      title: "Hostels",
    },
  },
  {
    path: "/stalls",
    name: "Stalls",
    component: Canteens,
    meta: {
      title: "Stalls",
    },
  },
  {
    path: "/studyAreas",
    name: "StudyAreas",
    component: StudyAreas,
    meta: {
      title: "Study Areas",
    },
  },
  {
    path: "/myComments",
    name: "MyComments",
    component: MyComments,
    meta: {
      title: "My Comments",
    },
  },
  {
    path: "/hostels/:id",
    name: "HostelViewMore",
    component: ItemViewMore,
    props: {
      type: "hostel",
    },
    meta: {
      title: "Hostel",
    },
  },
  {
    path: "/stalls/:id",
    name: "StallViewMore",
    component: ItemViewMore,
    props: {
      type: "stall",
    },
    meta: {
      title: "stall",
    },
  },
  {
    path: "/studyAreas/:id",
    name: "StudyAreaViewMore",
    component: ItemViewMore,
    props: {
      type: "studyArea",
    },
    meta: {
      title: "studyArea",
    },
  },
  {
    path: "/registrationConfirm",
    name: "RegistrationConfirm",
    component: RegistrationConfirm,
    meta: {
      title: "RegistrationConfirm",
    },
  },
  {
    path: "/resetPassword",
    name: "ResetPassword",
    component: ResetPassword,
    meta: {
      title: "ResetPassword",
    },
  },
  {
    path: "/manageItems",
    name: "ManageItems",
    component: ManageItems,
    meta: {
      title: "Manage Items",
    },
  },
  {
    path: "/faq",
    name: "FAQ",
    component: FAQ,
    meta: {
      title: "FAQ",
    },
  },
  {
    path: "/team",
    name: "Team",
    component: Team,
    meta: {
      title: "Team",
    },
  },
  {
    path: "/about",
    name: "About",
    component: About,
    meta: {
      title: "About",
    },
  },
  {
    path: "/contribute",
    name: "Contribute",
    component: Contribute,
    meta: {
      title: "Contribute",
    },
  },
];

const router = new VueRouter({
  routes: routes,
});

export default router;
