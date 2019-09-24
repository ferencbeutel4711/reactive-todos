<template>
  <router-link :to="target">
    <div class="nav-entry" :class="selected ? 'selected' : ''">
      <v-icon class="icon">{{icon}}</v-icon>
      <span class="text">{{text}}</span>
    </div>
  </router-link>
</template>
<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  props: {
    target: String,
    icon: String,
    text: String,
    selected: Boolean
  }
});
</script>
<style scoped lang="scss">
  @import "../../css/mixins";

  .nav-entry {
    background: rgba(0, 0, 0, 0.1);
    color: black;
    display: inline-block;
    height: 50px;
    padding: 4px;
    text-decoration: none;
    width: 100%;

    @include vertical-anchor();

    &:hover {
      background: rgba(0, 0, 0, 0.2);
    }
  }

  .selected {
    background: rgba(0, 0, 0, 0.15);
  }

  .icon {
    padding-left: 8px;
    vertical-align: middle;
  }

  .text {
    vertical-align: middle;
  }
</style>
