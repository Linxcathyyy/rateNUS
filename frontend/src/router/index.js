import Vue from "vue";
import VueRouter from "vue-router";
import Hostels from "../views/Hostels.vue";
import Canteens from "../views/Canteens.vue";
import StudyAreas from "../views/StudyAreas.vue";
import ItemViewMore from "../components/commons/ItemViewMore.vue";
import RegistrationConfirm from "../components/authentication/RegistrationConfirm.vue"
// import store from '@/store/index.js';
Vue.use(VueRouter);

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
    path: '/registrationConfirm',
    name: 'RegistrationConfirm',
    component: RegistrationConfirm,
  },

]

const router = new VueRouter({
  routes: routes
});

export default router