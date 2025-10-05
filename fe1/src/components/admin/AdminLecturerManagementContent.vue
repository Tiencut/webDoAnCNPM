<template>
  <div class="lecturer-management-container">
    <LecturerTable />
  </div>
</template>

<script setup lang="ts">
import LecturerTable from './LecturerTable.vue';
</script>

<style scoped>
.lecturer-management-container {
  padding: 1.5rem; /* p-6 */
  background-color: #fff; /* bg-white */
  border-radius: 0.5rem; /* rounded-lg */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* shadow-md */
}
</style>