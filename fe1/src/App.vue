<template>
  <div id="app">
    <StudentSidebar v-if="$route.path.startsWith('/student')" />
    <LecturerSidebar v-else-if="$route.path.startsWith('/lecturer') && $route.path !== '/lecturer-login'" />
    <AdminSidebar v-else-if="$route.path.startsWith('/admin')" />
    <div class="main-content">
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import AdminSidebar from './components/AdminSidebar.vue';
import StudentSidebar from './components/StudentSidebar.vue';
import LecturerSidebar from './components/LecturerSidebar.vue';

export default defineComponent({
  name: 'App',
  components: {
    AdminSidebar,
    StudentSidebar,
    LecturerSidebar,
  },
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  min-width: 100%;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  display: flex;
  min-height: 100vh;
}

.main-content {
  flex-grow: 1;
  /* padding: 20px; */
}
</style>
