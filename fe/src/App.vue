<script setup>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
</script>

<template>
  <div id="app" class="min-h-screen bg-gray-100">
    <nav class="bg-white shadow-md p-4">
      <router-link to="/" class="text-blue-500 hover:text-blue-700 mr-4">Home</router-link>
      <router-link to="/login/student" class="text-blue-500 hover:text-blue-700 mr-4">Student Login</router-link>
      <router-link to="/login/lecturer" class="text-blue-500 hover:text-blue-700 mr-4">Lecturer Login</router-link>
      <router-link to="/login/admin" class="text-blue-500 hover:text-blue-700 mr-4">Admin Login</router-link>
      <router-link to="/student-dashboard" class="text-blue-500 hover:text-blue-700 mr-4">Student Dashboard</router-link>
      <router-link to="/lecturer-dashboard" class="text-blue-500 hover:text-blue-700 mr-4">Lecturer Dashboard</router-link>
      <router-link to="/admin-dashboard" class="text-blue-500 hover:text-blue-700 mr-4">Admin Dashboard</router-link>
      <router-link to="/groups" class="text-blue-500 hover:text-blue-700 mr-4">Groups</router-link>
      <router-link to="/projects" class="text-blue-500 hover:text-blue-700 mr-4">Projects</router-link>
      <router-link to="/reports" class="text-blue-500 hover:text-blue-700 mr-4">Reports</router-link>
      <router-link to="/notifications" class="text-blue-500 hover:text-blue-700">Notifications</router-link>
    </nav>
    <main class="p-4">
      <router-view />
    </main>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* Global styles or Tailwind imports can go here */
@import './assets/base.css';
</style>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
