<template>
  <div class="lecturer-profile-edit">
    <h1>Sửa thông tin cá nhân Giảng viên</h1>
    <LecturerProfileForm />
  </div>
</template>

<script setup lang="ts">
import LecturerProfileForm from '../../components/LecturerProfileForm.vue';
</script>

<style scoped>
.lecturer-profile-edit {
  padding: 2rem;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  color: #2f4050;
  margin-bottom: 1rem;
}

p {
  color: #555;
}
</style>