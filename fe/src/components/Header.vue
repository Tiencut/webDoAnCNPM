<template>
  <header class="bg-white shadow-md p-4 flex items-center justify-between">
    <h2 class="text-2xl font-semibold text-gray-800">Xin chào, {{ username }}!</h2>
    <!-- You can add more elements here, like a logout button or user avatar -->
  </header>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  username: {
    type: String,
    required: true,
  },
});
</script>

<style scoped>
/* Component specific styles if needed */
</style>