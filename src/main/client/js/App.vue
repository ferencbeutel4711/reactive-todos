<template>
  <div class="vue-app">
    <v-app>
      <v-navigation-drawer :value="drawer" app clipped>
        <NavEntry v-for="navConfig in navConfigs" :target="navConfig.target" :text="navConfig.text" :icon="navConfig.icon"
                  :selected="isSelected(navConfig)" :key="navConfig.target"/>
      </v-navigation-drawer>
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
import NavEntry from "./components/NavEntry.vue";

export default Vue.extend({
  components: {
    NavEntry
  },
  data() {
    return {
      drawer: false,
      navConfigs: [
        {
          target: '/',
          text: 'Home',
          icon: 'mdi-home'
        },
        {
          target: '/todos',
          text: 'Todos',
          icon: 'mdi-format-list-checkbox'
        }
      ]
    }
  },
  methods: {
    isSelected(navConfig: any) {
      console.log(this.$route);
      return this.$route.path === navConfig.target;
    }
  }
});
</script>

<style scoped lang="scss">

</style>
