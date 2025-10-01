<template>
  <div class="student-registration">
    <h1>Đăng Ký Thông Tin Cá Nhân Sinh Viên</h1>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="studentId">Mã số sinh viên:</label>
        <input type="text" id="studentId" v-model="studentId" required />
      </div>
      <div class="form-group">
        <label for="fullName">Họ và tên:</label>
        <input type="text" id="fullName" v-model="fullName" required />
      </div>
      <div class="form-group">
        <label for="phoneNumber">Số điện thoại:</label>
        <input type="tel" id="phoneNumber" v-model="phoneNumber" required />
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div class="form-group">
        <label for="course">Khóa:</label>
        <input type="text" id="course" v-model="course" required />
      </div>
      <div class="form-group">
        <label for="major">Ngành học:</label>
        <input type="text" id="major" v-model="major" required />
      </div>
      <button type="submit">Đăng ký</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'StudentRegistration',
  setup() {
    const studentId = ref('');
    const fullName = ref('');
    const phoneNumber = ref('');
    const email = ref('');
    const course = ref('');
    const major = ref('');

    const register = () => {
      console.log('Đăng ký thông tin sinh viên:', {
        studentId: studentId.value,
        fullName: fullName.value,
        phoneNumber: phoneNumber.value,
        email: email.value,
        course: course.value,
        major: major.value,
      });
      // TODO: Tích hợp API đăng ký tại đây
    };

    return {
      studentId,
      fullName,
      phoneNumber,
      email,
      course,
      major,
      register,
    };
  },
});
</script>

<style scoped>
.student-registration {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  font-family: Arial, sans-serif;
}

h1 {
  color: #333;
  margin-bottom: 20px;
}

form {
  background-color: #f9f9f9;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 400px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  color: #555;
}

input[type="text"],
input[type="tel"],
input[type="email"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #17a2b8;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #138496;
}
</style>