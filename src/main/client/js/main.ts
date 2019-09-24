import Vue from 'vue'

import App from './App.vue'
import router from './router'
import store from './store/store'
import vuetify from "./vuetify";

new Vue({
  el: '#app',
  router,
  store,
  vuetify,
  template: '<App/>',
  components: {App}
});
