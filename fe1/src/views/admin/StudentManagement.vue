<template>
  <div class="admin-student-management">
    <!-- <div class="content-wrapper"> -->
      <AdminStudentManagementContent />
    <!-- </div> -->
  </div>
</template>

<script setup lang="ts">
import AdminStudentManagementContent from '../../components/admin/AdminStudentManagementContent.vue';
import AdminSidebar from '../../components/AdminSidebar.vue';
</script>

<style scoped>
.admin-student-management {
  height: 100vh;
}

.content-wrapper {
  flex-grow: 1;
  background-color: #f0f2f5;
}
</style>