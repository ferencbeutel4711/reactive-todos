<template>
  <div class="todo">
    <v-list-item>
      <v-list-item-content>
        <span :class="classList">{{name}} : {{dueDate}}</span>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>
<script lang="ts">
import Vue, {PropType} from "vue";

export default Vue.extend({
  props: {
    name: String,
    dueDate: {
      type: Date as PropType<Date>
    },
    done: Boolean
  },
  computed: {
    classList(): string[] {
      const now = new Date();
      const classList = [];

      if (this.done) {
        classList.push('done');
      }

      if (now >= this.dueDate) {
        classList.push('overdue');
      }

      return classList;
    }
  }
});
</script>
<style scoped lang="scss">
  .done {
    text-decoration: line-through;
  }

  .overdue {
    color: red;
  }
</style>
