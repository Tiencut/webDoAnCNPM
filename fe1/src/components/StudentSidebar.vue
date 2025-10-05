<template>
  <aside class="student-sidebar">
    <div class="sidebar-header">
      <h2 class="sidebar-title">Xin chào Sinh viên</h2>
      <p class="sidebar-subtitle">Chào mừng trở lại</p>
    </div>
    <nav>
      <ul>
        <li class="sidebar-item">
          <router-link to="/" class="sidebar-link">Trang chủ</router-link>
        </li>
        <li class="sidebar-item">
          <router-link to="/student-registration" class="sidebar-link">Sửa thông tin cá nhân</router-link>
        </li>
        <li class="sidebar-item">
          <router-link to="/student/groups" class="sidebar-link">Quản lý Nhóm</router-link>
        </li>
        <li class="sidebar-item">
          <router-link to="/student/topics" class="sidebar-link">Quản lý Đề tài</router-link>
        </li>
        <li class="sidebar-item">
          <router-link to="/student/weekly-plans" class="sidebar-link">Kế hoạch theo tuần</router-link>
        </li>
        <li class="sidebar-item">
          <router-link to="/student/notifications" class="sidebar-link">Thông báo</router-link>
        </li>

      </ul>
      
      <div class="sidebar-footer">
        <button @click="logout" class="logout-button">
          <svg xmlns="http://www.w3.org/2000/svg" class="logout-icon" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
          </svg>
          Đăng xuất
        </button>
      </div>
    </nav>
  </aside>
</template>

<script setup lang="ts">
// Xử lý đăng xuất
const logout = () => {
  // Xóa token hoặc thông tin đăng nhập từ localStorage
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  
  // Chuyển hướng về trang đăng nhập
  window.location.href = '/login';
};
</script>

<style scoped>
.student-sidebar {
  width: 16rem;
  height: 100%;
  background-color: #2f4050;
  color: #fff;
  padding: 0rem 1rem; /* Updated as per user request */
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
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

nav {
  display: flex;
  flex-direction: column;
  flex-grow: 1; /* This is important to make nav take available space */
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
  flex-grow: 1; /* This is important to make ul take available space within nav */
}

.sidebar-item {
  margin-bottom: 0.5rem;
}

.sidebar-link {
  display: flex;
  align-items: center;
  padding: 0.75rem 1rem;
  border-radius: 0.25rem;
  color: #fff;
  text-decoration: none;
  transition: background-color 0.2s ease;
}

.sidebar-link:hover {
  background-color: #3a4b5c;
  color: #fff;
}

.divider {
  border: none;
  border-top: 1px solid #4a5a6a;
  margin: 1rem 0;
}

.sidebar-footer {
  margin-top: auto; /* Pushes the footer to the bottom */
  padding-top: 1rem;
  border-top: 1px solid #4a5a6a;
}

.logout-button {
  display: flex;
  width: 100%;
  background-color: transparent;
  border: none;
  color: white;
  text-align: left;
  font-size: 1.1em;
  align-items: center;
  padding: 10px 0;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.logout-button:hover {
  background-color: #e74c3c;
}

.logout-icon {
  height: 1.25rem;
  width: 1.25rem;
  margin-right: 0.75rem;
}
</style>