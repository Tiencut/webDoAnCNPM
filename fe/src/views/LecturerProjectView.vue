<template>
  <div class="container mx-auto p-4">
    <h1 class="text-3xl font-bold mb-6">Quản lý Đề tài/Dự án</h1>
    <div class="bg-white p-6 rounded-lg shadow-md">
      <h2 class="text-xl font-semibold mb-4">Danh sách Đề tài</h2>
      <table class="min-w-full bg-white">
        <thead>
          <tr>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Mã đề tài</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Tên đề tài</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Nhóm SV</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Trạng thái</th>
            <th class="py-2 px-4 border-b border-gray-200 bg-gray-100 text-left text-sm font-semibold text-gray-600">Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="py-2 px-4 border-b border-gray-200">DT001</td>
            <td class="py-2 px-4 border-b border-gray-200">Phát triển ứng dụng di động</td>
            <td class="py-2 px-4 border-b border-gray-200">N001</td>
            <td class="py-2 px-4 border-b border-gray-200">Đang thực hiện</td>
            <td class="py-2 px-4 border-b border-gray-200">
              <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded text-sm">Xem</button>
              <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-3 rounded text-sm ml-2">Đánh giá</button>
            </td>
          </tr>
          <tr>
            <td class="py-2 px-4 border-b border-gray-200">DT002</td>
            <td class="py-2 px-4 border-b border-gray-200">Hệ thống quản lý sinh viên</td>
            <td class="py-2 px-4 border-b border-gray-200">N002</td>
            <td class="py-2 px-4 border-b border-gray-200">Đã hoàn thành</td>
            <td class="py-2 px-4 border-b border-gray-200">
              <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded text-sm">Xem</button>
              <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-3 rounded text-sm ml-2">Đánh giá</button>
            </td>
          </tr>
        </tbody>
      </table>
      <button class="mt-4 bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded">Tạo đề tài mới</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerProjectView',
};
</script>

<style scoped>
/* Các style tùy chỉnh cho trang quản lý đề tài/dự án */
</style>