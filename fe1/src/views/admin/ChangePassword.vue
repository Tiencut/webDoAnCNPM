<template>
  <div class="change-password-container">
    <h2>Đổi mật khẩu</h2>
    <form @submit.prevent="handleChangePassword" class="password-form">
      <div class="form-group">
        <label for="current-password">Mật khẩu hiện tại:</label>
        <input type="password" id="current-password" v-model="currentPassword" required />
      </div>
      <div class="form-group">
        <label for="new-password">Mật khẩu mới:</label>
        <input type="password" id="new-password" v-model="newPassword" required />
      </div>
      <div class="form-group">
        <label for="confirm-password">Xác nhận mật khẩu mới:</label>
        <input type="password" id="confirm-password" v-model="confirmPassword" required />
      </div>
      <div class="form-group">
        <label for="otp">Mã OTP:</label>
        <input type="text" id="otp" v-model="otp" required />
      </div>
      <button type="button" class="send-otp-button" @click="sendOtp">Gửi mã OTP</button>
      <button type="submit" class="submit-button">Đổi mật khẩu</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const currentPassword = ref('');
const newPassword = ref('');
const confirmPassword = ref('');
const otp = ref('');

const sendOtp = () => {
  // Logic gửi mã OTP đến email của người dùng
  console.log('Gửi mã OTP đến email...');
  alert('Mã OTP đã được gửi đến email của bạn!');
};

const handleChangePassword = () => {
  if (newPassword.value !== confirmPassword.value) {
    alert('Mật khẩu mới và xác nhận mật khẩu không khớp!');
    return;
  }

  if (!otp.value) {
    alert('Vui lòng nhập mã OTP!');
    return;
  }

  // Logic để gửi yêu cầu đổi mật khẩu đến API sẽ được thêm vào đây
  console.log('Đổi mật khẩu:', {
    currentPassword: currentPassword.value,
    newPassword: newPassword.value,
    otp: otp.value,
  });
  alert('Đổi mật khẩu thành công!');

  // Xóa các trường sau khi đổi mật khẩu thành công
  currentPassword.value = '';
  newPassword.value = '';
  confirmPassword.value = '';
  otp.value = '';
};
</script>

<style scoped>
.change-password-container {
  max-width: 500px;
  margin: 50px auto;
  padding: 30px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  color: #2c3e50;
  margin-bottom: 30px;
  font-size: 1.8em;
}

.password-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #34495e;
  font-weight: bold;
}

.form-group input[type="password"],
.form-group input[type="text"] {
  width: 100%;
  padding: 12px;
  border: 1px solid #cccccc;
  border-radius: 6px;
  font-size: 1em;
  box-sizing: border-box;
  transition: border-color 0.3s ease;
}

.form-group input[type="password"]:focus,
.form-group input[type="text"]:focus {
  border-color: #3498db;
  outline: none;
}

.send-otp-button {
  background-color: #e67e22;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 1em;
  font-weight: bold;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.send-otp-button:hover {
  background-color: #d35400;
  transform: translateY(-2px);
}

.send-otp-button:active {
  transform: translateY(0);
}

.submit-button {
  background-color: #3498db;
  color: white;
  padding: 12px 25px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 1.1em;
  font-weight: bold;
  transition: background-color 0.3s ease, transform 0.2s ease;
  margin-top: 20px;
}

.submit-button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
}

.submit-button:active {
  transform: translateY(0);
}
</style>