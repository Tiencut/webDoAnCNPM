import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/HomeView.vue') // Sử dụng HomeView mới
  },
  {
    path: '/login/student',
    name: 'StudentLogin',
    component: () => import('../views/StudentLoginView.vue')
  },
  {
    path: '/login/lecturer',
    name: 'LecturerLogin',
    component: () => import('../views/LecturerLoginView.vue')
  },
  {
    path: '/login/admin',
    name: 'AdminLogin',
    component: () => import('../views/AdminLoginView.vue')
  },
  {
    path: '/student-dashboard',
    name: 'StudentDashboard',
    component: () => import('../views/StudentDashboardView.vue')
  },
  {
    path: '/lecturer-dashboard',
    name: 'lecturer-dashboard',
    component: () => import('../views/LecturerDashboardView.vue')
  },
  {
    path: '/lecturer/profile',
    name: 'lecturer-profile',
    component: () => import('../views/LecturerProfileView.vue')
  },
  {
    path: '/lecturer/course-sections',
    name: 'lecturer-course-sections',
    component: () => import('../views/LecturerCourseSectionsView.vue')
  },
  {
    path: '/lecturer/groups',
    name: 'lecturer-groups',
    component: () => import('../views/LecturerGroupsView.vue')
  },
  {
    path: '/lecturer/projects',
    name: 'lecturer-projects',
    component: () => import('../views/LecturerProjectsView.vue')
  },
  {
    path: '/lecturer/reports',
    name: 'lecturer-reports',
    component: () => import('../views/LecturerReportsView.vue')
  },
  {
    path: '/lecturer/notifications',
    name: 'lecturer-notifications',
    component: () => import('../views/LecturerNotificationsView.vue')
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;