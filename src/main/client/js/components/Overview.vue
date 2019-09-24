<template>
  <div class="overview">
    <p class="greeting">Hello, {{name}}
      <v-avatar class="avatar" :size="'32px'" :color="'grey'" :tile="false"><img :src="avatarSrc" alt=""/></v-avatar>
    </p>
    <v-btn class="load-random-name-button" :options="{type: 'outlined', color: 'primary'}" v-on:click="loadRandomName">
      Load random name
    </v-btn>
  </div>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  computed: {
    name() {
      let storedName = this.$store.getters['HelloWorldModule/getName'];
      return storedName ? storedName : ' handsome ;)';
    },
    avatarSrc() {
      let src = this.$store.getters['HelloWorldModule/getSrc'];
      return src ? src : 'https://i1.wp.com/www.mvhsoracle.com/wp-content/uploads/2018/08/default-avatar.jpg?ssl=1';
    }
  },
  methods: {
    loadRandomName() {
      this.$store.dispatch('HelloWorldModule/fetchRandomName');
    }
  }
});
</script>
<style scoped lang="scss">
  .overview {
    max-width: 80%;
    margin: 8px auto 0 auto;
    text-align: center;
  }

  .greeting {
    vertical-align: middle;
  }

  .avatar {
    margin-left: 8px;
    vertical-align: middle;
  }

  .load-random-name-button {
    margin-top: 16px;
  }
</style>
