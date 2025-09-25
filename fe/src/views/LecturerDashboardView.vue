<template>
  <div class="flex h-screen bg-gray-100">
    <Sidebar />
    <div class="flex-1 flex flex-col overflow-hidden">
      <Header username="Giảng viên" />
      <main class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-200">
        <div class="container mx-auto p-4">
          <h1 class="text-2xl font-bold mb-4">Lecturer Dashboard</h1>
          <p>This is the lecturer dashboard content.</p>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup>
import Header from '../components/Header.vue';
import Sidebar from '../components/Sidebar.vue';
</script>

<style scoped>
/* Add any specific styles for the Lecturer Dashboard here */
</style>

<style scoped>
/* Các style tùy chỉnh nếu cần, nhưng Tailwind CSS đã xử lý hầu hết */
.router-link-active.router-link-exact-active {
  @apply bg-blue-100 text-blue-700;
}
</style>