import { defineStore } from 'pinia';

interface UserInfo {
  id: string;
  fullName: string;
  email: string;
  // Thêm các trường thông tin người dùng khác nếu cần
}

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isLoggedIn: false,
    userRole: null as string | null,
    userInfo: null as UserInfo | null,
    authToken: null as string | null,
  }),
  getters: {
    isAuthenticated: (state) => state.isLoggedIn,
    getUserRole: (state) => state.userRole,
    getUserInfo: (state) => state.userInfo,
    getAuthToken: (state) => state.authToken,
  },
  actions: {
    login(token: string, role: string, userInfo: UserInfo) {
      this.isLoggedIn = true;
      this.authToken = token;
      this.userRole = role;
      this.userInfo = userInfo;
      // Lưu token vào localStorage hoặc sessionStorage nếu cần
      localStorage.setItem('authToken', token);
      localStorage.setItem('userRole', role);
      localStorage.setItem('userInfo', JSON.stringify(userInfo));
    },
    logout() {
      this.isLoggedIn = false;
      this.authToken = null;
      this.userRole = null;
      this.userInfo = null;
      // Xóa token khỏi localStorage hoặc sessionStorage
      localStorage.removeItem('authToken');
      localStorage.removeItem('userRole');
      localStorage.removeItem('userInfo');
    },
    initializeAuth() {
      // Khởi tạo trạng thái xác thực từ localStorage khi ứng dụng tải
      const token = localStorage.getItem('authToken');
      const role = localStorage.getItem('userRole');
      const userInfo = localStorage.getItem('userInfo');

      if (token && role && userInfo) {
        this.isLoggedIn = true;
        this.authToken = token;
        this.userRole = role; // Hoàn tác thay đổi
        this.userInfo = JSON.parse(userInfo);
      }
    },
  },
});