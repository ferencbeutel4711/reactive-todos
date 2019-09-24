<template>
  <div class="todo-list">
    <v-list>
      <v-subheader>Todos</v-subheader>
      <Todo v-for="todo in todos" :key="todo.id" :name="todo.name" :due-date="new Date(Date.parse(todo.dueDate))"
            :done="todo.done"/>
    </v-list>
  </div>
</template>
<script lang="ts">
import axios from 'axios';
import Vue from "vue";
import Todo from "./Todo.vue";

export default Vue.extend({
  components: {
    Todo
  },
  data() {
    return {
      todos: []
    }
  },
  mounted() {
    const config = {
      headers: {
        'Accept': 'application/json'
      }
    };
    axios.get('/todo', config)
      .then((response) => {
        if (response.status !== 200) {
          console.error(`bad response from the todos endpoint: ${response.status} Body: ${response.data}`);
        }

        this.todos = response.data;
      })
      .catch((error) => {
        console.error(`error during calling the todos endpoint: ${error.toString()}`);
      })
  }
});
</script>
<style scoped lang="scss">
</style>
