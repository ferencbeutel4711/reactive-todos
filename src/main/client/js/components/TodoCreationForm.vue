<template>
  <div class="creation-form">
    <p class="success" v-if="success">Success!</p>
    <v-text-field v-model="name" clearable placeholder="name"></v-text-field>
    <v-menu ref="dateMenu" v-model="dateMenu" :close-on-content-click="false" :return-value.sync="date"
            transition="scale-transition"
            offset-y min-width="290px">
      <template v-slot:activator="{ on }">
        <v-text-field v-model="date" label="Due date" readonly v-on="on"></v-text-field>
      </template>
      <v-date-picker v-model="date" no-title scrollable>
        <div class="flex-grow-1"></div>
        <v-btn text color="primary" @click="dateMenu = false">Cancel</v-btn>
        <v-btn text color="primary" @click="$refs.dateMenu.save(date)">OK</v-btn>
      </v-date-picker>
    </v-menu>

    <v-menu ref="timeMenu" v-model="timeMenu" :close-on-content-click="false" :return-value.sync="time"
            transition="scale-transition" offset-y min-width="290px">
      <template v-slot:activator="{ on }">
        <v-text-field v-model="time" label="Due date" readonly v-on="on"></v-text-field>
      </template>
      <v-time-picker v-model="time" no-title scrollable>
        <div class="flex-grow-1"></div>
        <v-btn text color="primary" @click="timeMenu = false">Cancel</v-btn>
        <v-btn text color="primary" @click="$refs.timeMenu.save(time)">OK</v-btn>
      </v-time-picker>
    </v-menu>
    <v-btn v-on:click="createTodo">Create Todo</v-btn>
  </div>
</template>
<script lang="ts">
import axios from 'axios';
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      dateMenu: false,
      timeMenu: false,
      name: '',
      success: false,
      date: null,
      time: null
    }
  },
  methods: {
    createTodo() {
      axios.post('/todo', {
        name: this.name,
        dueDate: `${this.date}T${this.time}`
      })
        .then((response) => {
          if (response.status !== 200) {
            console.error(`bad response from the create todo endpoint: ${response.status} Body: ${response.data}`);
          } else {
            this.name = '';
            this.date = null;
            this.time = null;
            this.success = true;
            setTimeout(() => {
              this.success = false
            }, 2000);
          }
        })
        .catch((error) => {
          console.error(`error during calling the create todo endpoint: ${error.toString()}`);
        })
    }
  }
});
</script>
<style scoped lang="scss">
  .creation-form {
    max-width: 600px;
    padding: 16px;
  }
</style>
