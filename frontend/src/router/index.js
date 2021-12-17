import Vue from "vue";
import VueRouter from "vue-router";
import Hostels from "../views/Hostels.vue";
import Canteens from "../views/Canteens.vue";
import StudyAreas from "../views/StudyAreas.vue";
import ItemViewMore from "../components/commons/ItemViewMore.vue";
// import store from '@/store/index.js';
Vue.use(VueRouter);

const BACKEND_BASE_URL = "http://localhost:8080";

const routes = [
  {
    path: '/',
    name: 'Hostels',
    component: Hostels
  },
  {
    path: '/hostels',
    name: 'Hostels',
    component: Hostels,
    meta: {
      title: "Hostels",
    }
  },
  {
    path: '/canteens',
    name: 'Canteens',
    component: Canteens,
    meta: {
      title: "Canteens",
    }
  },
  {
    path: '/studyAreas',
    name: 'StudyAreas',
    component: StudyAreas,
    meta: {
      title: "Study Areas",
    }
  },
  {
    path: '/hostels/:hostelId',
    name: 'HostelViewMore',
    component: ItemViewMore,
    props: {
      type: "hostel"
    },
    meta: {
      title: "Hostel",
    }
  },
  {
    path: '/stalls/:stallId',
    name: 'StallViewMore',
    component: ItemViewMore,
    props: {
      type: "stall"
    },
    meta: {
      title: `stall`,
    }
  },
  {
    path: '/login',
    beforeEnter() {
      window.location.href = BACKEND_BASE_URL + "/login";
    }
  }
]

const router = new VueRouter({
  routes: routes
});

export default router