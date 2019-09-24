import {ActionTree, GetterTree, Module, MutationTree} from 'vuex';
import axios from 'axios';

import RootState from "./RootState";

interface HelloWorldState {
  name: string,
  src: string
}

const state: HelloWorldState = {
  name: '',
  src: ''
};

const getters: GetterTree<HelloWorldState, RootState> = {
  getName(state): string {
    return state.name;
  },
  getSrc(state): string {
    return state.src;
  }
};

const actions: ActionTree<HelloWorldState, RootState> = {
  fetchRandomName({commit}): any {
    axios('https://randomuser.me/api/', {headers: {'Accept': 'application/json'}})
      .then((response) => {
        if (response.status !== 200) {
          console.error(`Bad response from the random user api: ${response.status} Body: ${response.data}`);
        } else {
          commit('setName', `${response.data.results[0].name.first} ${response.data.results[0].name.last}`);
          commit('setSrc', response.data.results[0].picture.medium);
        }
      })
      .catch((error) => {
        console.error(`error during calling the random user api: ${error.toString()}`);
      })
  }
};

const mutations: MutationTree<HelloWorldState> = {
  setName(state, name: string) {
    state.name = name;
  },
  setSrc(state, src: string) {
    state.src = src;
  }
};

const helloWorldModule: Module<HelloWorldState, RootState> = {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};

export default helloWorldModule;
