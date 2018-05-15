import page4 from './page4.vue';
import page5 from './page5.vue';

const routes = [
  { path: '/', component: page4 },
  { path: '/page5', component: page5 },
]

const router = new VueRouter({
  routes
})

var store = new Vuex.Store({
  state: {
    form: {
      params: {
        username: {
          value: '',
          error: false
        },
        selected1: {
          value: ''
        },
        ininin: []
      },
      error: {}
    },
    input1: []
  },
  actions: {
    getInput1 (context) {
      axios.get('/vuetest/mockdata/input1.json?ver=1')
        .then(response => (context.commit('SET_INPUT', response.data)))
    },
    inputUpdate (context, payload) {
      context.commit('INPUT_UPDATE', payload);
    },
    inputUpdate2 (context, payload) {
      context.commit('INPUT_UPDATE2', payload);
    }
  },
  mutations: {
    SET_INPUT (state, body) {
      state.input1 = body.input
    },
    INPUT_UPDATE (state, payload){

      const segments = payload.path.split('.')

      state[segments[0]][segments[1]][segments[2]][segments[3]] = payload.value
    },
    INPUT_UPDATE2 (state, payload) {
      state.form.params.ininin.push(payload)
    }
  }
})

const app = new Vue({
  el: '#app2',
  router,
  store
})
