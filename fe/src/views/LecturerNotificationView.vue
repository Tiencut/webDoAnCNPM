<template>
  <div class="container mx-auto p-4">
    <h1 class="text-3xl font-bold mb-6">Quản lý Thông báo</h1>
    <div class="bg-white p-6 rounded-lg shadow-md">
      <h2 class="text-xl font-semibold mb-4">Danh sách Thông báo</h2>
      <table class="min-w-full bg-white">
        <thead>
          <tr>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Tiêu đề</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Nội dung</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Ngày gửi</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="py-2 px-4 border-b border-gray-200">Thông báo họp</td>
            <td class="py-2 px-4 border-b border-gray-200">Vui lòng tham gia cuộc họp vào lúc 10h sáng mai.</td>
            <td class="py-2 px-4 border-b border-gray-200">2023-10-26</td>
            <td class="py-2 px-4 border-b border-gray-200">
              <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded text-sm">Xem</button>
              <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-3 rounded text-sm ml-2">Xóa</button>
            </td>
          </tr>
          <tr>
            <td class="py-2 px-4 border-b border-gray-200">Nhắc nhở nộp báo cáo</td>
            <td class="py-2 px-4 border-b border-gray-200">Hạn chót nộp báo cáo là cuối tuần này.</td>
            <td class="py-2 px-4 border-b border-gray-200">2023-10-25</td>
            <td class="py-2 px-4 border-b border-gray-200">
              <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded text-sm">Xem</button>
              <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-3 rounded text-sm ml-2">Xóa</button>
            </td>
          </tr>
        </tbody>
      </table>
      <button class="mt-4 bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded">Tạo thông báo mới</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerNotificationView',
};
</script>

<style scoped>
/* Các style tùy chỉnh cho trang quản lý thông báo */
</style>