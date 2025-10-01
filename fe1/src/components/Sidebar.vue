<template>
  <AdminSidebar v-if="userRole === 'admin'" />
  <LecturerSidebar v-else-if="userRole === 'lecturer'" />
  <StudentSidebar v-else-if="userRole === 'student'" />
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useAuthStore } from '../stores/auth';
import AdminSidebar from './AdminSidebar.vue';
import LecturerSidebar from './LecturerSidebar.vue';
import StudentSidebar from './StudentSidebar.vue';

const authStore = useAuthStore();
const userRole = computed(() => {
  console.log('Current user role:', authStore.userRole);
  return authStore.userRole;
});
</script>

<style scoped>
.default-sidebar {
  width: 16rem; /* w-64 */
  background-color: #1f2937; /* bg-gray-800 */
  color: #fff;
  padding: 1rem;
}

.default-sidebar-item {
  margin-bottom: 0.5rem; /* mb-2 */
}

.default-sidebar-link {
  display: block;
  padding: 0.5rem 0.75rem; /* py-2 px-3 */
  border-radius: 0.25rem; /* rounded */
  color: #fff;
  text-decoration: none;
}

.default-sidebar-link:hover {
  background-color: #374151; /* hover:bg-gray-700 */
}
</style>