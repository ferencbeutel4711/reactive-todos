import Vue from 'vue'
import VueRouter from 'vue-router'

import Overview from './components/Overview.vue';

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Overview}
];

export default new VueRouter({
  routes
});
