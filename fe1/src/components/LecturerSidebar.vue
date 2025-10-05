<script setup lang="ts">
import { useRouter, RouterLink } from 'vue-router';
import { UserCircleIcon, BookOpenIcon, AcademicCapIcon, ArrowRightOnRectangleIcon, KeyIcon, UserGroupIcon, CalendarDaysIcon, BellIcon } from '@heroicons/vue/24/outline';

const router = useRouter();

const navigation = [
  { name: 'Sửa thông tin cá nhân', href: '/lecturer/profile-edit', icon: UserCircleIcon },
  { name: 'Quản lý Đề tài', href: '/lecturer/topics', icon: BookOpenIcon },
  { name: 'Quản lý Sinh viên', href: '/lecturer/students', icon: AcademicCapIcon },
  { name: 'Quản lý Nhóm', href: '/lecturer/groups', icon: UserGroupIcon },
  { name: 'Quản lý Kế hoạch Làm việc Hàng tuần', href: '/lecturer/weekly-plans', icon: CalendarDaysIcon },
  { name: 'Quản lý Thông báo', href: '/lecturer/notifications', icon: BellIcon },
  { name: 'Đổi mật khẩu', href: '/lecturer/change-password', icon: KeyIcon },
];

const handleLogout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  window.location.href = '/login';
};
</script>

<template>
  <aside class="lecturer-sidebar">
    <div class="sidebar-header">
      <h2 class="sidebar-title">Xin chào Giảng viên</h2>
      <p class="sidebar-subtitle">Chào mừng trở lại</p>
    </div>
    <nav class="navigation">
      <ul>
        <li v-for="item in navigation" :key="item.name" class="sidebar-item">
          <RouterLink :to="item.href" class="sidebar-link" active-class="active">
            <component :is="item.icon" class="h-6 w-6" aria-hidden="true" />
            <span>{{ item.name }}</span>
          </RouterLink>
        </li>
      </ul>
    </nav>
    
    <div class="sidebar-footer">
      <hr class="divider" />
      <button @click="handleLogout" class="logout-button">
        <ArrowRightOnRectangleIcon class="h-6 w-6" aria-hidden="true" />
        <span>Đăng xuất</span>
      </button>
    </div>
  </aside>
</template>

<style scoped>
.lecturer-sidebar {
  width: 16rem; /* w-64 */
  height: 100%; /* Đặt chiều cao 100% để chiếm toàn bộ chiều cao của phần tử cha */
  background-color: #2f4050; /* Màu nền tối hơn một chút */
  color: #fff;
  padding: 0rem 1rem; /* Tăng padding trên dưới, giữ nguyên trái phải */
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Thêm đổ bóng nhẹ */
  display: flex;
  flex-direction: column;
}

.sidebar-header {
  padding: 1.5rem 0;
  text-align: center;
  border-bottom: 1px solid #4a5a6a;
  margin-bottom: 1rem;
}

.sidebar-title {
  font-size: 1.25rem;
  font-weight: bold;
  color: #fff;
  margin-bottom: 0.25rem;
}

.sidebar-subtitle {
  font-size: 0.875rem;
  color: #a0aec0;
}

.navigation ul {
  list-style: none;
  padding: 0;
  margin: 0;
  flex-grow: 1;
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

.sidebar-footer {
  margin-top: auto; /* Đẩy footer xuống dưới cùng */
  padding: 10px 0;
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
  padding: 10px 0;
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

.divider {
  border: none;
  border-top: 1px solid #4a5a6a;
  margin: 1rem 0;
}
</style>