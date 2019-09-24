import Vue from 'vue'
import VueRouter from 'vue-router'

import Overview from './components/Overview.vue';
import TodoList from "./components/TodoList.vue";
import TodoCreationForm from "./components/TodoCreationForm.vue";

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Overview},
  {path: '/todos', component: TodoList},
  {path: '/todos/create', component: TodoCreationForm}
];

export default new VueRouter({
  routes
});
