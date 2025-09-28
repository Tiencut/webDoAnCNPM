<script setup>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
import { useRoute } from 'vue-router';
import { computed } from 'vue';

const route = useRoute()
const showNavbar = computed(() => {
  // Tạm thời luôn hiển thị navbar để kiểm tra lỗi
  return true;
});
</script>

<template>
  <div id="app" class="min-h-screen font-sans antialiased">
    <nav v-if="showNavbar" class="bg-gradient-to-r from-blue-500 to-purple-600 p-4 shadow-lg">
      <div class="container mx-auto flex flex-wrap items-center justify-between">
        <router-link to="/" class="text-white text-2xl font-bold hover:text-blue-200 transition duration-300 ease-in-out">
          Project Management
        </router-link>
        <div class="flex flex-wrap space-x-4 mt-2 md:mt-0">
          <router-link to="/" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Home</router-link>
          <router-link to="/login/student" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Student Login</router-link>
          <router-link to="/login/lecturer" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Login</router-link>
          <router-link to="/login/admin" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Admin Login</router-link>
          <router-link to="/student-dashboard" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Student Dashboard</router-link>
          <router-link to="/lecturer-dashboard" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Dashboard</router-link>
          <router-link to="/admin-dashboard" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Admin Dashboard</router-link>
          <router-link to="/groups" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Groups</router-link>
          <router-link to="/projects" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Projects</router-link>
          <router-link to="/reports" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Reports</router-link>
          <router-link to="/notifications" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Notifications</router-link>
          <router-link to="/lecturer/profile" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Profile</router-link>
          <router-link to="/lecturer/course-sections" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Course Sections</router-link>
          <router-link to="/lecturer/groups" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Groups</router-link>
          <router-link to="/lecturer/projects" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Projects</router-link>
          <router-link to="/lecturer/reports" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Reports</router-link>
          <router-link to="/lecturer/notifications" class="text-white hover:text-blue-200 transition duration-300 ease-in-out px-3 py-2 rounded-md text-sm font-medium">Lecturer Notifications</router-link>
        </div>
      </div>
    </nav>
    <main class="container mx-auto p-4 mt-4">
      <router-view />
    </main>
  </div>
</template>

<style>
/* Global styles or Tailwind imports can go here */
@import './assets/base.css';
</style>

<style scoped>
/* Remove scoped styles that might conflict with Tailwind */
</style>
