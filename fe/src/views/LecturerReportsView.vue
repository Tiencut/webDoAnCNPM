<template>
  <div class="lecturer-reports-container min-h-screen bg-gray-100 p-6">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Quản lý Báo cáo</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Danh sách Báo cáo</h2>
      <div v-if="reports.length === 0" class="text-gray-500">Chưa có báo cáo nào.</div>
      <div v-else>
        <ul class="divide-y divide-gray-200">
          <li v-for="report in reports" :key="report.id" class="py-4 flex items-center justify-between">
            <div>
              <p class="text-lg font-semibold text-gray-900">{{ report.title }}</p>
              <p class="text-sm text-gray-600">Sinh viên: {{ report.student }}</p>
              <p class="text-sm text-gray-600">Ngày gửi: {{ report.date }}</p>
            </div>
            <div class="flex space-x-2">
              <button @click="viewReportDetails(report.id)" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-sm">Xem chi tiết</button>
              <button @click="evaluateReport(report.id)" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded text-sm">Đánh giá</button>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Thống kê Báo cáo</h2>
      <p class="text-gray-500">Biểu đồ hoặc số liệu thống kê về báo cáo sẽ được hiển thị tại đây.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerReportsView',
  data() {
    return {
      reports: [
        { id: 1, title: 'Báo cáo tiến độ Đề tài A', student: 'Nguyễn Thị B', date: '2023-10-26' },
        { id: 2, title: 'Báo cáo cuối kỳ Đề tài C', student: 'Trần Văn D', date: '2023-10-20' },
      ],
    };
  },
  methods: {
    viewReportDetails(id) {
      alert(`Xem chi tiết báo cáo ${id}`);
      // Logic để điều hướng đến trang chi tiết báo cáo
    },
    evaluateReport(id) {
      alert(`Đánh giá báo cáo ${id}`);
      // Logic để đánh giá báo cáo
    },
  },
};
</script>

<style scoped>
/* Các style tùy chỉnh nếu cần */
</style>