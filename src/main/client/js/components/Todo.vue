<template>
  <div class="todo">
    <v-list-item v-on:click="setToDone">
      <v-list-item-content>
        <span :class="classList">{{name}} : {{dueDate}}</span>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>
<script lang="ts">
import Vue, {PropType} from "vue";
import axios from 'axios';

export default Vue.extend({
  props: {
    id: String,
    name: String,
    dueDate: {
      type: Date as PropType<Date>
    },
    initialDone: Boolean
  },
  data() {
    return {
      done: this.initialDone,
    }
  },
  computed: {
    classList(): string[] {
      const now = new Date();
      const classList = [];

      if (this.done) {
        classList.push('done');
      }

      if (!this.done && now >= this.dueDate) {
        classList.push('overdue');
      }

      return classList;
    }
  },
  methods: {
    setToDone() {
      axios.post(`/todo/${this.id}/done`, {})
        .then((response) => {
          if (response.status !== 200) {
            console.error(`bad response from the todo done endpoint: ${response.status} Body: ${response.data}`);
          }

          this.done = true;
        })
        .catch((error) => {
          console.error(`error during calling the todo done endpoint: ${error.toString()}`);
        })
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
