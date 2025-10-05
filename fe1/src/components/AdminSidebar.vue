<script setup lang="ts">
import { ref } from 'vue';
import { useRouter, RouterLink } from 'vue-router';
import { HomeIcon, UserGroupIcon, AcademicCapIcon, BookOpenIcon, CalendarDaysIcon, BellIcon, ArrowRightOnRectangleIcon, KeyIcon } from '@heroicons/vue/24/outline';
import StudentImportModal from './common/StudentImportModal.vue';
import LecturerImportModal from './common/LecturerImportModal.vue';
import TopicImportModal from './common/TopicImportModal.vue';

const router = useRouter();

const navigation = [
  { name: 'Trang chủ', href: '/admin', icon: HomeIcon },
  { name: 'Quản lý Giảng viên', href: '/admin/lecturers', icon: UserGroupIcon },
  { name: 'Quản lý Sinh viên', href: '/admin/students', icon: AcademicCapIcon },
  { name: 'Quản lý Nhóm', href: '/admin/groups', icon: UserGroupIcon },
  { name: 'Quản lý Đề tài', href: '/admin/topics', icon: BookOpenIcon },
  { name: 'Quản lý Kế hoạch Làm việc Hàng tuần', href: '/admin/weekly-plans', icon: CalendarDaysIcon },
  { name: 'Quản lý Thông báo', href: '/admin/notifications', icon: BellIcon },
  { name: 'Đổi mật khẩu', href: '/admin/change-password', icon: KeyIcon },
];

const handleLogout = () => {
  console.log('Đăng xuất');
  // Logic đăng xuất thực tế sẽ được thêm vào đây
};
</script>

<template>
  <div class="sidebar">
    <div class="admin-greeting">xin chào admin</div>
    <nav class="navigation">
      <ul>
        <li v-for="item in navigation" :key="item.name">
          <RouterLink :to="item.href" active-class="active">
            <component :is="item.icon" class="h-6 w-6" aria-hidden="true" />
            <span>{{ item.name }}</span>
          </RouterLink>
        </li>
      </ul>
    </nav>
    <!-- Nút đăng xuất -->
    <div class="logout-item">
       <!-- đấu gạch ngang -->
        <hr class="divider" />
      <button @click="handleLogout" class="logout-button">
        <ArrowRightOnRectangleIcon class="h-6 w-6" aria-hidden="true" />
        <span>Đăng xuất</span>
      </button>
    </div>
  </div>
</template>

<style scoped>
.sidebar {
  width: 250px;
  background-color: #2c3e50;
  color: white;
  /* padding: 20px; */
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  height: 100%;
  display: flex;
  flex-direction: column;
}

.admin-greeting {
  text-align: center;
  padding: 20px 0;
  font-size: 1.5em;
  font-weight: bold;
  color: white;
  border-bottom: 1px solid #eee;
  margin-bottom: 10px;
}

.logo {
  text-align: center;
  margin-bottom: 30px;
}

.logo img {
  max-width: 100px;
  height: auto;
}

.navigation ul {
  list-style: none;
  padding: 0;
}

.navigation li {
  margin-bottom: 15px;
}

.navigation a {
  color: white;
  text-decoration: none;
  font-size: 1.1em;
  display: flex;
  align-items: center;
  padding: 10px 15px;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.navigation a:hover,
.navigation a.active {
  background-color: #34495e;
}

.navigation a svg {
  margin-right: 10px;
  height: 24px;
  width: 24px;
}

.logout-item {
  margin-top: auto; /* Đẩy nút đăng xuất xuống cuối */
  padding: 10px 15px; /* Thêm khoảng đệm xung quanh nút */
}

.logout-button {
  width: 100%; /* Nút chiếm toàn bộ chiều rộng của container */
  background-color: transparent; /* Nền trong suốt */
  border: none;
  color: white;
  text-align: left; /* Căn chỉnh văn bản sang trái */
  font-size: 1.1em;
  display: flex;
  align-items: center;
  padding: 10px 0; /* Điều chỉnh khoảng đệm cho nội dung nút */
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.logout-button:hover {
  background-color: #e74c3c; /* Màu nền khi hover, ví dụ: màu đỏ cho đăng xuất */
}

.logout-button svg {
  margin-right: 10px;
  height: 24px;
  width: 24px;
}
</style>