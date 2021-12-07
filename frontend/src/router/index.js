import Vue from "vue";
import VueRouter from "vue-router";
import Hostels from "../views/Hostels.vue";
import Canteens from "../views/Canteens.vue";
import StudyAreas from "../views/StudyAreas.vue";
import HostelViewMore from "../components/hostels/HostelViewMore.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: '/hostels',
    name: 'Hostels',
    component: Hostels
  },
  {
    path: '/canteens',
    name: 'Canteens',
    component: Canteens
  },
  {
    path: '/studyAreas',
    name: 'StudyAreas',
    component: StudyAreas
  },
  {
    path: '/hostels/:hostelId',
    name: 'HostelViewMore',
    component: HostelViewMore
  }
]

const router = new VueRouter({
    routes: routes
  });

export default router