import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import vuetify from './plugins/vuetify';
import VueCarousel from 'vue-carousel';
import 'material-design-icons-iconfont/dist/material-design-icons.css';
import VueCookies from 'vue-cookies';

Vue.config.productionTip = false;
Vue.use(VueCarousel);
Vue.use(VueCookies);

new Vue({
  render: h => h(App),
  vuetify,
  router: router
}).$mount('#app')
