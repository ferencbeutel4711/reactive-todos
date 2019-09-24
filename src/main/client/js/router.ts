import Vue from 'vue'
import VueRouter from 'vue-router'

import Overview from './components/Overview.vue';
import TodoList from "./components/TodoList.vue";

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Overview},
  {path: '/todos', component: TodoList}
];

export default new VueRouter({
  routes
});
