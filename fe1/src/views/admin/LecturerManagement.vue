<template>
  <div class="admin-lecturer-management">
    <div class="content-wrapper">
      <AdminLecturerManagementContent />
    </div>
  </div>
</template>

<script setup lang="ts">
import AdminLecturerManagementContent from '../../components/admin/AdminLecturerManagementContent.vue';
import AdminSidebar from '../../components/AdminSidebar.vue';
</script>

<style scoped>
.admin-lecturer-management {
  display: flex;
  height: 100vh;
}

.content-wrapper {
  flex-grow: 1;
  background-color: #f0f2f5;
}
</style>