<template>
  <div class="lecturer-change-password-form">
    <form @submit.prevent="changePassword">
      <div class="form-group">
        <label for="currentPassword">Mật khẩu hiện tại:</label>
        <input type="password" id="currentPassword" v-model="passwords.current" required />
      </div>

      <div class="form-group">
        <label for="newPassword">Mật khẩu mới:</label>
        <input type="password" id="newPassword" v-model="passwords.new" required />
      </div>

      <div class="form-group">
        <label for="confirmPassword">Xác nhận mật khẩu mới:</label>
        <input type="password" id="confirmPassword" v-model="passwords.confirm" required />
      </div>

      <button type="submit" class="change-password-button">Đổi mật khẩu</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const passwords = ref({
  current: '',
  new: '',
  confirm: '',
});

const changePassword = () => {
  if (passwords.value.new !== passwords.value.confirm) {
    alert('Mật khẩu mới và xác nhận mật khẩu không khớp!');
    return;
  }
  // Logic gọi API để đổi mật khẩu sẽ được thêm vào đây
  console.log('Đổi mật khẩu:', passwords.value);
  alert('Mật khẩu đã được đổi thành công!');
  // Reset form
  passwords.value = {
    current: '',
    new: '',
    confirm: '',
  };
};
</script>

<style scoped>
.lecturer-change-password-form {
  background-color: #fff;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
  color: #333;
}

input[type="password"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  box-sizing: border-box;
}

input:focus {
  border-color: #007bff;
  outline: none;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.change-password-button {
  background-color: #007bff;
  color: white;
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.2s ease;
}

.change-password-button:hover {
  background-color: #0056b3;
}
</style>