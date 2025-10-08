<template>
  <router-link :to="to" :class="linkClass">
    <slot></slot>
  </router-link>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';

defineProps({
  to: {
    type: String,
    required: true,
  },
  linkClass: {
    type: String,
    default: '',
  },
});
</script>

<style scoped>
/* Có thể thêm các style cụ thể cho NavLink tại đây nếu cần */
</style>