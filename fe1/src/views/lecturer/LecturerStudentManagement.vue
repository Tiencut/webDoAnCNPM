<template>
  <div class="lecturer-student-management p-4">
    <h1 class="text-2xl font-bold mb-4">Quản lý Sinh viên (Giảng viên)</h1>
    <AdminStudentManagementContent />
  </div>
</template>

<script setup lang="ts">
import AdminStudentManagementContent from '@/components/admin/AdminStudentManagementContent.vue';
</script>

<style scoped>
.lecturer-student-management {
  /* Các style tùy chỉnh cho trang quản lý sinh viên của giảng viên */
}
</style>