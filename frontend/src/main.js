import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import vuetify from './plugins/vuetify';
import VueCarousel from 'vue-carousel';

Vue.config.productionTip = false;
Vue.use(VueCarousel);

new Vue({
  render: h => h(App),
  vuetify,
  router: router
}).$mount('#app')
