<template>
  <div class="lecturer-change-password">
    <h1>Đổi mật khẩu Giảng viên</h1>
    <LecturerChangePasswordForm />
  </div>
</template>

<script setup lang="ts">
import LecturerChangePasswordForm from '../../components/LecturerChangePasswordForm.vue';
</script>

<style scoped>
.lecturer-change-password {
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