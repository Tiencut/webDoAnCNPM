<template>
  <div class="home-dashboard">
    <h1 class="dashboard-title">Dashboard</h1>
    <div v-if="userRole === 'student'" class="dashboard-content">
      <h2 class="dashboard-subtitle">Chào mừng Sinh viên!</h2>
      <p>Đây là trang tổng quan dành cho sinh viên. Bạn có thể xem thông tin cá nhân, đăng ký nhóm, và theo dõi tiến độ đồ án.</p>
      <!-- Nội dung cụ thể cho sinh viên -->
    </div>

    <div v-else-if="userRole === 'lecturer'" class="dashboard-content">
      <h2 class="dashboard-subtitle">Chào mừng Giảng viên!</h2>
      <p>Đây là trang tổng quan dành cho giảng viên. Bạn có thể quản lý đề tài, xem báo cáo tiến độ của sinh viên và chấm điểm.</p>
      <!-- Nội dung cụ thể cho giảng viên -->
    </div>

    <div v-else-if="userRole === 'admin'" class="dashboard-content">
      <h2 class="dashboard-subtitle">Chào mừng Quản trị viên!</h2>
      <p>Đây là trang tổng quan dành cho quản trị viên. Bạn có thể quản lý người dùng, đề tài và các thiết lập hệ thống.</p>
      <!-- Nội dung cụ thể cho quản trị viên -->
    </div>

    <div v-else class="dashboard-content">
      <h2 class="dashboard-subtitle">Chào mừng đến với hệ thống quản lý đồ án!</h2>
      <p class="dashboard-text">Vui lòng đăng nhập để xem nội dung phù hợp với vai trò của bạn hoặc sử dụng các liên kết dưới đây để thử nghiệm:</p>
      <div class="dashboard-links">
        <router-link to="/admin/lecturers" class="dashboard-link">Dashboard Quản trị viên (Giảng viên)</router-link>
        <router-link to="/admin/students" class="dashboard-link">Dashboard Quản trị viên (Sinh viên)</router-link>
        <router-link to="/admin/topics" class="dashboard-link">Dashboard Quản trị viên (Đề tài)</router-link>
        <router-link to="/lecturer/topics" class="dashboard-link">Dashboard Giảng viên (Đề tài)</router-link>
        <router-link to="/lecturer/students" class="dashboard-link">Dashboard Giảng viên (Sinh viên)</router-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useAuthStore } from '../stores/auth';

const authStore = useAuthStore();
const userRole = computed(() => authStore.userRole);
</script>

<style scoped>
.home-dashboard {
  padding: 20px;
}

.dashboard-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: 700; /* font-bold */
  margin-bottom: 1rem; /* mb-4 */
}

.dashboard-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dashboard-subtitle {
  font-size: 1.25rem; /* text-xl */
  font-weight: 600; /* font-semibold */
  margin-bottom: 0.5rem; /* mb-2 */
}

.dashboard-text {
  margin-bottom: 1rem; /* mb-4 */
}

.dashboard-links {
  display: flex;
  flex-direction: column;
  gap: 0.5rem; /* space-y-2 */
}

.dashboard-link {
  color: #3b82f6; /* text-blue-500 */
  text-decoration: none;
}

.dashboard-link:hover {
  text-decoration: underline;
}
</style>