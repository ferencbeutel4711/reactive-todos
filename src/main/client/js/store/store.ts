import Vue from 'vue'
import Vuex, {StoreOptions} from 'vuex'

import RootState from "./RootState";
import HelloWorldModule from "./HelloWorldModule";

Vue.use(Vuex);

const store: StoreOptions<RootState> = {
  state: {
    version: '1.0.0' // a simple property
  },
  modules: {
    HelloWorldModule
  }
};


export default new Vuex.Store<RootState>(store);
