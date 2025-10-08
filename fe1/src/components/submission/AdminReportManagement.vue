<template>
  <div class="admin-report-management p-6">
    <div class="header-container">
      <h1 class="text-3xl font-bold text-gray-800">Quản lý Báo cáo</h1>
      <button
        class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors"
      >
        Thêm
      </button>
    </div>

    <div class="bg-white shadow-md rounded-lg p-4">
      <BaseTable :headers="reportHeaders" :items="mockReports" itemKey="id" :hasActions="false">
        <template #item-actions="{ item }">
          <div class="flex space-x-2">
            <!-- Các nút hành động đã bị xóa -->
          </div>
        </template>
      </BaseTable>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import BaseTable from '../common/BaseTable.vue'; // Đảm bảo đường dẫn đúng

interface Report {
  id: string;
  assignmentName: string;
}

const reportHeaders = [
  { value: 'assignmentName', text: 'Tên Bài tập' },
  { value: 'actions', text: 'Hành động' },
];

const mockReports = ref<Report[]>([
  {
    id: '10',
    assignmentName: 'Requirement Outline',
  },
  {
    id: '9',
    assignmentName: 'SRS v1.0',
  },
  {
    id: '2',
    assignmentName: 'checklist Self review SRS',
  },
  {
    id: '8',
    assignmentName: 'SRS v2.0',
  },
  {
    id: '7',
    assignmentName: 'DB Design v1.0',
  },
  {
    id: '6',
    assignmentName: 'DB design v2.0',
  },
  {
    id: '5',
    assignmentName: 'Testcase v1.0',
  },
  {
    id: '4',
    assignmentName: 'SRS v3.0',
  },
  {
    id: '3',
    assignmentName: 'Testcase v2.0',
  },
  {
    id: '1',
    assignmentName: 'Checklist Self review DB Design',
  },
]);
</script>

<style scoped>
.admin-report-management {
}

.header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem; /* Tương đương mb-6 của Tailwind */
}
</style>
