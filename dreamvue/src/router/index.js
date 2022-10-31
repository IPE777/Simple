import Vue from 'vue'
import Router from 'vue-router'
import User from '../views/User'
import Add from '../views/Add'
import Update from '../views/Update'
import Index from '../views/Index'
import Test from '../views/Test'



Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: "导航一",
      component: Index,
      show: true,

      redirect: "/user",
      children:[
        {
          path: '/user',
          name: "用户管理",
          component: User
        },
        {
          path: '/test',
          name: "测试",
          component: Test
        }
      ]
    },
    {
      path: '/navigation',
      name: '导航二',
      show: false,
      component: Index,

      children:[
        {
          path: '/add',
          name: "添加用户",
          component: Add
        },
        {
          path: '/update',
          name: "修改图书",
          component: Update
        }
      ]
    }
  ]
})
