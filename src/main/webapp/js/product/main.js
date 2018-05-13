import page1 from './page1.vue';
import page2 from './page2.vue';
import page3 from './page3.vue';

const routes = [
  { path: '/', component: page1 },
  { path: '/page1', component: page1 },
  { path: '/page2', component: page2 },
  { path: '/page3', component: page3 }
]

const router = new VueRouter({
  routes
})

var store = new Vuex.Store({
  state: {
    inputes:{
        select1edObj: {
          value: '',
          valueView: ''
        },
        select2edObj: {
          value: '',
          valueView: ''
        },
        radio1: '',
        text1: '',
        daySelect1ed: '',
        modalInput: ''
    },
    tableRow:Object
  },
  actions: {
    upDate (context, entity) {
      context.commit('UPDATE', entity)
    },
    searchTrance (context, obj) {
      context.commit('PAGETRANCE', obj)
    }
  },
  mutations: {
    UPDATE(state, entity){
      state.inputes = entity
    },
    PAGETRANCE(state, obj){
      state.tableRow = obj
    }
  }
})

const app = new Vue({
  el: '#app',
  router,
  store
})
