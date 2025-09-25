<template>
  <div class="flex h-screen bg-gray-100">
    <Sidebar />
    <div class="flex-1 flex flex-col overflow-hidden">
      <Header username="Sinh viên" />
      <main class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-200">
        <div class="container mx-auto p-4">
          <h1 class="text-2xl font-bold mb-4">Student Dashboard</h1>
          <p>This is the student dashboard content.</p>
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
/* Add any specific styles for the Student Dashboard here */
</style>