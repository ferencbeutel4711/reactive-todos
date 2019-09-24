<template>
  <div class="vue-app">
    <v-app>
      <v-navigation-drawer :value="drawer" app clipped></v-navigation-drawer>
      <v-app-bar app clipped-left>
        <v-app-bar-nav-icon v-on:click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>Fancy Todo List</v-toolbar-title>
      </v-app-bar>
      <v-content>
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </v-content>
      <v-footer app>
      </v-footer>
    </v-app>
  </div>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      drawer: false
    }
  }
});
</script>

<style scoped lang="scss">

</style>
