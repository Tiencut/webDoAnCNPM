<template>
  <div class="home flex flex-col items-center justify-center min-h-screen py-10">
    <h1 class="text-5xl font-extrabold text-gray-900 mb-6 animate-fade-in-down">Chào mừng đến với Hệ thống Quản lý Đồ án</h1>
    <p class="text-xl text-gray-700 mb-12 text-center max-w-2xl animate-fade-in-up">Vui lòng chọn vai trò của bạn để đăng nhập:</p>

    <div class="login-options grid grid-cols-1 md:grid-cols-3 gap-10 w-full max-w-5xl">
      <router-link to="/login/student" class="login-card bg-white rounded-xl shadow-xl hover:shadow-2xl transform hover:-translate-y-2 transition-all duration-300 p-8 flex flex-col items-center text-center border-b-4 border-blue-500 hover:border-blue-700">
        <div class="icon-wrapper bg-blue-100 text-blue-600 rounded-full p-5 mb-6 shadow-md">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM12 14H8c-2.206 0-4 1.794-4 4v1h16v-1c0-2.206-1.794-4-4-4z" />
          </svg>
        </div>
        <h2 class="text-3xl font-bold text-gray-800 mb-3">Sinh viên</h2>
        <p class="text-gray-600 text-lg">Đăng nhập với tư cách sinh viên để quản lý đồ án, nhóm và báo cáo.</p>
      </router-link>

      <router-link to="/login/lecturer" class="login-card bg-white rounded-xl shadow-xl hover:shadow-2xl transform hover:-translate-y-2 transition-all duration-300 p-8 flex flex-col items-center text-center border-b-4 border-green-500 hover:border-green-700">
        <div class="icon-wrapper bg-green-100 text-green-600 rounded-full p-5 mb-6 shadow-md">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.253v13m0-13C10.832 5.477 9.206 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.794 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.794 5 16.5 5c1.706 0 3.332.477 4.5 1.253v13C19.832 18.477 18.206 18 16.5 18s-3.332.477-4.5 1.253" />
          </svg>
        </div>
        <h2 class="text-3xl font-bold text-gray-800 mb-3">Giảng viên</h2>
        <p class="text-gray-600 text-lg">Đăng nhập với tư cách giảng viên để quản lý lớp học, đề tài và sinh viên.</p>
      </router-link>

      <router-link to="/login/admin" class="login-card bg-white rounded-xl shadow-xl hover:shadow-2xl transform hover:-translate-y-2 transition-all duration-300 p-8 flex flex-col items-center text-center border-b-4 border-red-500 hover:border-red-700">
        <div class="icon-wrapper bg-red-100 text-red-600 rounded-full p-5 mb-6 shadow-md">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.001 12.001 0 002.92 12c0 3.072 1.874 5.798 4.515 7.553L12 22.953l4.515-2.398A12.001 12.001 0 0021.08 12c0-3.072-1.874-5.798-4.515-7.553z" />
          </svg>
        </div>
        <h2 class="text-3xl font-bold text-gray-800 mb-3">Quản trị viên</h2>
        <p class="text-gray-600 text-lg">Đăng nhập với tư cách quản trị viên để quản lý hệ thống và người dùng.</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
};
</script>

<style scoped>
@keyframes fade-in-down {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fade-in-up {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-fade-in-down {
  animation: fade-in-down 0.8s ease-out forwards;
}

.animate-fade-in-up {
  animation: fade-in-up 0.8s ease-out forwards 0.2s; /* Delay for a staggered effect */
}
</style>