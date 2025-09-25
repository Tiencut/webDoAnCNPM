<template>
  <div class="lecturer-profile-container min-h-screen bg-gray-100 p-6">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Thông tin cá nhân Giảng viên</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Hồ sơ của bạn</h2>
      <form @submit.prevent="updateProfile">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-4">
          <div>
            <label for="fullName" class="block text-gray-700 text-sm font-bold mb-2">Họ và tên:</label>
            <input type="text" id="fullName" v-model="profile.fullName" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Nguyễn Văn A">
          </div>
          <div>
            <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email:</label>
            <input type="email" id="email" v-model="profile.email" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="email@example.com" disabled>
          </div>
          <div>
            <label for="phone" class="block text-gray-700 text-sm font-bold mb-2">Số điện thoại:</label>
            <input type="text" id="phone" v-model="profile.phone" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="0901234567">
          </div>
          <div>
            <label for="department" class="block text-gray-700 text-sm font-bold mb-2">Khoa/Bộ môn:</label>
            <input type="text" id="department" v-model="profile.department" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Khoa Công nghệ thông tin">
          </div>
        </div>
        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Cập nhật hồ sơ</button>
      </form>
    </div>

    <div class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Đổi mật khẩu</h2>
      <form @submit.prevent="changePassword">
        <div class="mb-4">
          <label for="currentPassword" class="block text-gray-700 text-sm font-bold mb-2">Mật khẩu hiện tại:</label>
          <input type="password" id="currentPassword" v-model="password.current" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <div class="mb-4">
          <label for="newPassword" class="block text-gray-700 text-sm font-bold mb-2">Mật khẩu mới:</label>
          <input type="password" id="newPassword" v-model="password.new" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <div class="mb-6">
          <label for="confirmPassword" class="block text-gray-700 text-sm font-bold mb-2">Xác nhận mật khẩu mới:</label>
          <input type="password" id="confirmPassword" v-model="password.confirm" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Đổi mật khẩu</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerProfileView',
  data() {
    return {
      profile: {
        fullName: 'Nguyễn Văn A',
        email: 'nguyenvana@example.com',
        phone: '0901234567',
        department: 'Khoa Công nghệ thông tin',
      },
      password: {
        current: '',
        new: '',
        confirm: '',
      },
    };
  },
  methods: {
    updateProfile() {
      // Logic để cập nhật thông tin cá nhân
      alert('Cập nhật hồ sơ thành công!');
      console.log('Profile updated:', this.profile);
    },
    changePassword() {
      // Logic để đổi mật khẩu
      if (this.password.new !== this.password.confirm) {
        alert('Mật khẩu mới và xác nhận mật khẩu không khớp.');
        return;
      }
      alert('Đổi mật khẩu thành công!');
      console.log('Password changed:', this.password);
      // Reset form
      this.password = { current: '', new: '', confirm: '' };
    },
  },
};
</script>

<style scoped>
/* Các style tùy chỉnh nếu cần */
</style>