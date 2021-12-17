import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import vuetify from './plugins/vuetify';
import VueCarousel from 'vue-carousel';
import 'material-design-icons-iconfont/dist/material-design-icons.css';
import store from "./store";

Vue.config.productionTip = false;
Vue.use(VueCarousel);

new Vue({
  store,
  render: h => h(App),
  vuetify,
  router: router
}).$mount('#app')
