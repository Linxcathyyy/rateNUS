import Vue from "vue";
import VueRouter from "vue-router";
import Hostels from "../views/Hostels.vue";
import Canteens from "../views/Canteens.vue";
import StudyAreas from "../views/StudyAreas.vue";
import HostelViewMore from "../components/hostels/HostelViewMore.vue";

Vue.use(VueRouter);

const BACKEND_BASE_URL = "http://localhost:8080";

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