import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import VueCarousel from 'vue-carousel';
Vue.config.productionTip = false;
Vue.use(VueCarousel);
new Vue({
  render: h => h(App),
  router: router,
}).$mount('#app')
