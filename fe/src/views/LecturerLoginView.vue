<template>
  <div class="container mx-auto p-4 flex justify-center items-center min-h-screen bg-gray-100">
    <form action="/Account/Login" method="post" class="hitec-form-signin bg-white p-8 rounded-lg shadow-md w-full max-w-md">
      <input name="__RequestVerificationToken" type="hidden" value="9mUNvZc4gmMvjFai0u0PMpvQEiuLyp6VsUoDJ5OSg5Iu5lAIIbA7Sp9vcnwHW6WfAR1lKWQW1OAV4EJtaH_-uh2XdDg1">
      <h2 class="form-signin-heading text-2xl font-bold text-center mb-6">GIẢNG VIÊN</h2>
      <div class="form-group mb-4">
        <label for="loginID" class="block text-gray-700 text-sm font-bold mb-2">Mã giảng viên:</label>
        <input type="text" id="loginID" name="loginID" class="form-control input-lg shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Mã giảng viên" required="" autofocus="" value="">
      </div>
      <div class="form-group mb-6">
        <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Mật khẩu:</label>
        <input type="password" id="password" name="password" class="form-control input-lg shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" placeholder="Mật khẩu" required="" value="">
      </div>
      <div class="form-group mb-4">
        <span class="text-red-500 text-xs italic"></span>
      </div>
      <button class="btn btn-lg btn-primary btn-block btn-custom bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full" type="submit">Đăng nhập</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'LecturerLoginView',
};
</script>

<style scoped>
/* Add any specific styles for the Lecturer Login page here */
</style>