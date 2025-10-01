<template>
  <header class="app-header">
    <div class="header-greeting">
      Xin chào, {{ userInfo?.fullName || 'Khách' }}!
    </div>
    <button @click="logout" class="logout-button">
      Đăng xuất
    </button>
  </header>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../stores/auth';

const authStore = useAuthStore();
const router = useRouter();

const userInfo = computed(() => authStore.userInfo);

const logout = () => {
  authStore.logout();
  router.push('/student-login'); // Chuyển hướng về trang đăng nhập sau khi đăng xuất
};
</script>

<style scoped>
.app-header {
  background-color: #fff;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-greeting {
  font-size: 1.25rem; /* text-xl */
  font-weight: 600; /* font-semibold */
}

.logout-button {
  background-color: #ef4444; /* bg-red-500 */
  color: #fff;
  font-weight: 700; /* font-bold */
  padding: 0.5rem 1rem; /* py-2 px-4 */
  border-radius: 0.25rem; /* rounded */
  transition: background-color 0.2s ease-in-out;
}

.logout-button:hover {
  background-color: #b91c1c; /* hover:bg-red-700 */
}
</style>