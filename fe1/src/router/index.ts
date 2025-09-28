import { createRouter, createWebHistory } from 'vue-router'
import StudentLogin from '../views/StudentLogin.vue'
import LecturerLogin from '../views/LecturerLogin.vue'
import AdminLogin from '../views/AdminLogin.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/student-login',
      name: 'student-login',
      component: StudentLogin
    },
    {
      path: '/lecturer-login',
      name: 'lecturer-login',
      component: LecturerLogin
    },
    {
      path: '/admin-login',
      name: 'admin-login',
      component: AdminLogin
    }
  ],
})

export default router
