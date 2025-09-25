<template>
  <div class="lecturer-notifications-container min-h-screen bg-gray-100 p-6">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Thông báo Giảng viên</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Danh sách Thông báo</h2>
      <div v-if="notifications.length === 0" class="text-gray-500">Bạn không có thông báo mới nào.</div>
      <div v-else>
        <ul class="divide-y divide-gray-200">
          <li v-for="notification in notifications" :key="notification.id" class="py-4">
            <div class="flex items-center justify-between">
              <p class="text-lg font-semibold text-gray-900">{{ notification.title }}</p>
              <span class="text-sm text-gray-500">{{ notification.date }}</span>
            </div>
            <p class="text-gray-600">{{ notification.message }}</p>
            <button v-if="!notification.read" @click="markAsRead(notification.id)" class="mt-2 text-blue-500 hover:text-blue-700 text-sm">Đánh dấu đã đọc</button>
          </li>
        </ul>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Gửi Thông báo mới</h2>
      <form @submit.prevent="sendNotification">
        <div class="mb-4">
          <label for="notificationTitle" class="block text-gray-700 text-sm font-bold mb-2">Tiêu đề:</label>
          <input type="text" id="notificationTitle" v-model="newNotification.title" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Nhập tiêu đề thông báo">
        </div>
        <div class="mb-4">
          <label for="notificationMessage" class="block text-gray-700 text-sm font-bold mb-2">Nội dung:</label>
          <textarea id="notificationMessage" v-model="newNotification.message" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" rows="4" placeholder="Nhập nội dung thông báo"></textarea>
        </div>
        <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Gửi thông báo</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerNotificationsView',
  data() {
    return {
      notifications: [
        { id: 1, title: 'Nhắc nhở nộp báo cáo', message: 'Hạn chót nộp báo cáo tiến độ là ngày 30/10/2023.', date: '2023-10-25', read: false },
        { id: 2, title: 'Thông báo lịch họp', message: 'Cuộc họp khoa sẽ diễn ra vào lúc 9h sáng ngày mai.', date: '2023-10-24', read: true },
      ],
      newNotification: {
        title: '',
        message: '',
      },
    };
  },
  methods: {
    markAsRead(id) {
      const notification = this.notifications.find(n => n.id === id);
      if (notification) {
        notification.read = true;
        alert('Đã đánh dấu thông báo là đã đọc.');
      }
    },
    sendNotification() {
      if (this.newNotification.title && this.newNotification.message) {
        const newId = this.notifications.length > 0 ? Math.max(...this.notifications.map(n => n.id)) + 1 : 1;
        this.notifications.unshift({ 
          ...this.newNotification, 
          id: newId, 
          date: new Date().toISOString().slice(0, 10), 
          read: false 
        });
        alert('Thông báo đã được gửi!');
        this.newNotification = { title: '', message: '' };
      } else {
        alert('Vui lòng nhập đầy đủ tiêu đề và nội dung thông báo.');
      }
    },
  },
};
</script>

<style scoped>
/* Các style tùy chỉnh nếu cần */
</style>