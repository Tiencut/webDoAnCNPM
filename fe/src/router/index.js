import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../components/HelloWorld.vue') // Placeholder for now
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
    name: 'LecturerDashboard',
    component: () => import('../views/LecturerDashboardView.vue')
  },
  {
    path: '/admin-dashboard',
    name: 'AdminDashboard',
    component: () => import('../views/AdminDashboardView.vue')
  },
  {
    path: '/groups',
    name: 'Groups',
    component: () => import('../views/GroupsView.vue')
  },
  {
    path: '/projects',
    name: 'Projects',
    component: () => import('../views/ProjectsView.vue')
  },
  {
    path: '/reports',
    name: 'Reports',
    component: () => import('../views/ReportsView.vue')
  },
  {
    path: '/notifications',
    name: 'Notifications',
    component: () => import('../views/NotificationsView.vue')
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;