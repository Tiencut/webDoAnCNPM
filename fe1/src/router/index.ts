import { createRouter, createWebHistory } from 'vue-router'
import StudentLogin from '../views/StudentLogin.vue'
import LecturerLogin from '../views/LecturerLogin.vue'
import AdminLogin from '../views/AdminLogin.vue'
import StudentRegistration from '../views/StudentRegistration.vue'
import Home from '../views/Home.vue'
import LecturerManagement from '../views/admin/LecturerManagement.vue'
import StudentManagement from '../views/admin/StudentManagement.vue'
import TopicManagement from '../views/admin/TopicManagement.vue'
import LecturerTopicManagement from '../views/lecturer/LecturerTopicManagement.vue'
import LecturerStudentManagement from '../views/lecturer/LecturerStudentManagement.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
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
    },
    {
      path: '/student-registration',
      name: 'student-registration',
      component: StudentRegistration
    },
    {
      path: '/admin/lecturers',
      name: 'admin-lecturers',
      component: LecturerManagement
    },
    {
      path: '/admin/students',
      name: 'AdminStudentManagement',
      component: () => import('../views/admin/StudentManagement.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
    {
      path: '/admin/topics',
      name: 'admin-topics',
      component: TopicManagement
    },
    {
      path: '/lecturer/topics',
      name: 'lecturer-topics',
      component: LecturerTopicManagement
    },
    {
      path: '/lecturer/students',
      name: 'lecturer-students',
      component: LecturerStudentManagement
    },
    {
      path: '/admin/groups',
      name: 'AdminGroupManagement',
      component: () => import('../views/admin/GroupManagement.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
    {
      path: '/admin/weekly-plans',
      name: 'AdminWeeklyPlanManagement',
      component: () => import('../views/admin/WeeklyPlanManagement.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
    {
      path: '/admin/notifications',
      name: 'AdminNotificationManagement',
      component: () => import('../views/admin/NotificationManagement.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
  ],
})

export default router
