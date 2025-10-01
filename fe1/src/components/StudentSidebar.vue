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
          <router-link to="/student-registration" class="sidebar-link">Đăng ký thông tin cá nhân</router-link>
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
    width: 16rem; /* w-64 */
    height: 100%; /* Đặt chiều cao 100% để chiếm toàn bộ chiều cao của phần tử cha */
    background-color: #2f4050; /* Màu nền tối hơn một chút */
    color: #fff;
    padding: 1.5rem 1rem; /* Tăng padding trên dưới, giữ nguyên trái phải */
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Thêm đổ bóng nhẹ */
    display: flex;
    flex-direction: column;
  }

  nav {
    display: flex;
    flex-direction: column;
    ul {
      list-style: none;
      padding: 0;
      margin: 0;
      flex-grow: 1;
    }
  }

  .logout-button {
    display: flex;
    align-items: center;
    width: fit-content;
    color: #f87171;
    padding: 0.5rem 0.75rem;
    border-radius: 0.25rem; /* rounded */
    background-color: transparent;
    border: none;
    cursor: pointer;
    font-size: 1rem;
    text-align: left;
    transition: background-color 0.2s ease, color 0.2s ease; /* Thêm hiệu ứng chuyển đổi mượt mà */
  }

  .logout-button:hover {
    color: #feb2b2; /* hover:text-red-300 */
    background-color: #3a4b5c; /* hover:bg-gray-700 */
  }

  .logout-icon {
    height: 1.25rem; /* h-5 */
    width: 1.25rem; /* w-5 */
    margin-right: 0.75rem; /* mr-2 */
  }

  .sidebar-link {
    display: flex; /* Sử dụng flexbox để căn chỉnh icon và text */
    align-items: center; /* Căn giữa theo chiều dọc */
    padding: 0.75rem 1rem; /* Tăng padding */
    border-radius: 0.25rem; /* rounded */
    color: #fff;
    text-decoration: none;
    transition: background-color 0.2s ease; /* Thêm hiệu ứng chuyển đổi mượt mà */
  }
  .sidebar-link:hover {
    background-color: #3a4b5c; /* Màu nền khi hover */
    color: #fff; /* Màu chữ khi hover */
  }
</style>