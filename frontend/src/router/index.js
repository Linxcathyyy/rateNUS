import Vue from "vue";
import VueRouter from "vue-router";
import Hostels from "../views/Hostels.vue";
import Canteens from "../views/Canteens.vue";
import StudyAreas from "../views/StudyAreas.vue";
import ItemViewMore from "../components/commons/ItemViewMore.vue";
import MyComments from "../views/MyComments.vue"
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
    path: '/stalls',
    name: 'Stalls',
    component: Canteens,
    meta: {
      title: "Stalls",
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
    path: '/myComments',
    name: 'MyComments',
    component: MyComments,
    meta: {
      title: "My Comments",
    }
  },
  {
    path: '/hostels/:id',
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
    path: '/stalls/:id',
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
    path: '/studyAreas/:id',
    name: 'StudyAreaViewMore',
    component: ItemViewMore,
    props: {
      type: "studyArea"
    },
    meta: {
      title: `studyArea`,
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