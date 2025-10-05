<template>
  <div class="lecturer-profile-form">
    <form @submit.prevent="saveProfile">
      <div class="form-group">
        <label for="fullName">Họ và tên:</label>
        <input type="text" id="fullName" v-model="profile.fullName" required />
      </div>

      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="profile.email" required />
      </div>

      <div class="form-group">
        <label for="phone">Số điện thoại:</label>
        <input type="tel" id="phone" v-model="profile.phone" />
      </div>

      <div class="form-group">
        <label for="department">Khoa/Bộ môn:</label>
        <input type="text" id="department" v-model="profile.department" />
      </div>

      <button type="submit" class="save-button">Lưu thông tin</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';

interface LecturerProfile {
  fullName: string;
  email: string;
  phone?: string;
  department?: string;
}

const profile = ref<LecturerProfile>({
  fullName: '',
  email: '',
  phone: '',
  department: '',
});

// Giả lập việc tải dữ liệu hồ sơ
onMounted(() => {
  // Trong thực tế, bạn sẽ gọi API để lấy dữ liệu
  profile.value = {
    fullName: 'Nguyễn Văn A',
    email: 'nguyenvana@example.com',
    phone: '0901234567',
    department: 'Công nghệ thông tin',
  };
});

const saveProfile = () => {
  console.log('Lưu hồ sơ:', profile.value);
  alert('Thông tin đã được lưu thành công!');
  // Trong thực tế, bạn sẽ gọi API để lưu dữ liệu
};
</script>

<style scoped>
.lecturer-profile-form {
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

input[type="text"],
input[type="email"],
input[type="tel"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  box-sizing: border-box; /* Đảm bảo padding không làm tăng kích thước input */
}

input:focus {
  border-color: #007bff;
  outline: none;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.save-button {
  background-color: #28a745;
  color: white;
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.2s ease;
}

.save-button:hover {
  background-color: #218838;
}
</style>