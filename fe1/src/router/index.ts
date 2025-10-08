import { createRouter, createWebHistory } from 'vue-router'
import StudentLogin from '../views/StudentLogin.vue'
import LecturerLogin from '../views/LecturerLogin.vue'
import AdminLogin from '../views/AdminLogin.vue'
import StudentRegistration from '../views/StudentRegistration.vue'
import Home from '../views/Home.vue'
import StudentChatComponent from '../views/student/ChatComponent.vue'

import StudentGroupManagement from '../views/student/GroupManagement.vue'
import StudentNotificationManagement from '../views/student/NotificationManagement.vue'
import StudentTopicManagement from '../views/student/TopicManagement.vue'
import StudentWeeklyPlanManagement from '../views/student/WeeklyPlanManagement.vue'
import TaskManagementView from '../views/student/TaskManagementView.vue' // Import TaskManagementView
import LecturerManagement from '../views/admin/LecturerManagement.vue'
import StudentManagement from '../views/admin/StudentManagement.vue'
import TopicManagement from '../views/admin/TopicManagement.vue'
import LecturerTopicManagement from '../views/lecturer/LecturerTopicManagement.vue'
import LecturerStudentManagement from '../views/lecturer/LecturerStudentManagement.vue'
import LecturerProfileEdit from '../views/lecturer/LecturerProfileEdit.vue'
import LecturerChangePassword from '../views/lecturer/LecturerChangePassword.vue'
import LecturerGroupManagement from '../views/lecturer/LecturerGroupManagement.vue'
import LecturerWeeklyPlanManagement from '../views/lecturer/LecturerWeeklyPlanManagement.vue'
import LecturerNotificationManagement from '../views/lecturer/LecturerNotificationManagement.vue'
import ChangePassword from '../views/admin/ChangePassword.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/admin',
      name: 'admin-home',
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
      path: '/student/groups',
      name: 'student-groups',
      component: StudentGroupManagement,
      meta: { requiresAuth: true, role: 'student' },
    },
    {
      path: '/student/notifications',
      name: 'student-notifications',
      component: StudentNotificationManagement,
      meta: { requiresAuth: true, role: 'student' },
    },
    {
      path: '/student/topics',
      name: 'student-topics',
      component: StudentTopicManagement,
      meta: { requiresAuth: true, role: 'student' },
    },
    {
      path: '/student/weekly-plans',
      name: 'student-weekly-plans',
      component: StudentWeeklyPlanManagement,
      meta: { requiresAuth: true, role: 'student' },
    },
    {
      path: '/student/chat',
      name: 'student-chat',
      component: StudentChatComponent,
      meta: { requiresAuth: true, role: 'student' },
    },
    {
      path: '/student/task-management',
      name: 'student-task-management',
      component: TaskManagementView,
      meta: { requiresAuth: true, role: 'student' },
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
      path: '/lecturer/profile-edit',
      name: 'lecturer-profile-edit',
      component: LecturerProfileEdit
    },
    {
      path: '/lecturer/change-password',
      name: 'lecturer-change-password',
      component: LecturerChangePassword
    },
    {
      path: '/lecturer/groups',
      name: 'lecturer-groups',
      component: LecturerGroupManagement
    },
    {
      path: '/lecturer/weekly-plans',
      name: 'lecturer-weekly-plans',
      component: LecturerWeeklyPlanManagement
    },
    {
      path: '/lecturer/notifications',
      name: 'lecturer-notifications',
      component: LecturerNotificationManagement
    },
    {
      path: '/admin/change-password',
      name: 'admin-change-password',
      component: ChangePassword
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
    {
      path: '/admin/rules',
      name: 'AdminRuleManagement',
      component: () => import('../views/admin/RuleManagement.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
    {
      path: '/admin/reports',
      name: 'AdminReportManagement',
      component: () => import('../views/admin/AdminReportsView.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
  ],
})

router.beforeEach((to, from, next) => {
  console.log('Navigating from:', from.path, 'to:', to.path);
  next();
});

export default router
