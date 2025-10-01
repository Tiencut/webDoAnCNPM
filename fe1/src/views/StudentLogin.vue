<template>
  <div class="student-login">
    <h1>Trang Đăng Nhập Sinh Viên</h1>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="studentId">Mã số sinh viên:</label>
        <input type="text" id="studentId" v-model="studentId" />
      </div>
      <div class="form-group">
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" v-model="password" />
      </div>
      <button type="submit">Đăng nhập</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'StudentLogin',
  setup() {
    const studentId = ref('');
    const password = ref('');

    const login = () => {
      console.log('Đăng nhập với:', studentId.value, password.value);
      // TODO: Tích hợp API xác thực tại đây
    };

    return {
      studentId,
      password,
      login,
    };
  },
});
</script>

<style scoped>
.student-login {
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
  width: 300px;
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
input[type="password"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #0056b3;
}
</style>