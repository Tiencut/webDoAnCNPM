<template>
  <div class="container mx-auto p-4 flex justify-center items-center min-h-screen bg-gray-100">
    <div class="hitec-form-signin bg-white p-8 rounded-lg shadow-md w-full max-w-md text-center">
      <h2 class="form-signin-heading text-2xl font-bold mb-6">Vui lòng chọn vai trò đăng nhập</h2>
      <div class="flex flex-col space-y-4">
        <router-link to="/login/student" class="btn btn-lg btn-primary btn-block btn-custom bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full">Đăng nhập với tư cách Sinh viên</router-link>
        <router-link to="/login/lecturer" class="btn btn-lg btn-primary btn-block btn-custom bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full">Đăng nhập với tư cách Giảng viên</router-link>
        <router-link to="/login/admin" class="btn btn-lg btn-primary btn-block btn-custom bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full">Đăng nhập với tư cách Quản trị viên</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
};
</script>

<style scoped>
/* Add any specific styles for the Login page here */
</style>