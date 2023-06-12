import Vue from 'vue'
import VueRouter from 'vue-router'
import home from '../views/home.vue'
import login from '../views/login.vue'
import error from '../views/error.vue'
import person from '../views/person.vue'
import certificate from '../views/certificate.vue'
import test from '../views/test.vue'
import introduce from '../views/introduce.vue'
import t from '../views/t.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path: '/home',
    name: 'home',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: home
  },
  {
    path: '/person',
    name: 'person',
    component: person,
  },
  {
    path:'/introduce',
    name:'introduce',
    component:introduce,
  },
  {
    path: '/test',
    name: 'test',
    component: test,
  },
  {
    path:'/certificate',
    name:'certificate',
    component:certificate
  },
  {
    path:'*',
    name:'error',
    component:error
  },
  {
    path:'/t',
    name:'t',
    component:t
  }
]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
