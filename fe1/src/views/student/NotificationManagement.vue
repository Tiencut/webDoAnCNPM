<template>
  <div class="notification-management-container">
    <h1 class="notification-management-title">Thông báo</h1>

    <BaseTable :headers="tableHeaders" :items="notifications" item-key="id" :has-actions="false" :clickable="false">
      <template #actions="{ item }">
      </template>
    </BaseTable>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import BaseTable from '../../components/common/BaseTable.vue';

interface Notification {
  id: number;
  title: string;
  content: string;
  targetRole: 'all' | 'student' | 'lecturer' | 'admin';
  createdAt: string;
}

interface TableHeader {
  text: string;
  value: string;
}

export default defineComponent({
  name: 'NotificationManagement',
  components: {
    BaseTable,
  },
  setup() {
    const notifications = ref<Notification[]>([
      { id: 1, title: 'Thông báo 1', content: 'Nội dung thông báo 1', targetRole: 'all', createdAt: '2023-01-01' },
      { id: 2, title: 'Thông báo 2', content: 'Nội dung thông báo 2', targetRole: 'student', createdAt: '2023-01-02' },
    ]);

    const tableHeaders = ref<TableHeader[]>([
      { text: 'Tiêu đề', value: 'title' },
      { text: 'Nội dung', value: 'content' },
      { text: 'Ngày tạo', value: 'createdAt' },
    ]);

    return {
      notifications,
      tableHeaders,
    };
  },
});
</script>

<style scoped>
.notification-management-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem;
}

.notification-management-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

.add-notification-btn {
  background-color: #4CAF50; /* Green */
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 1rem;
  font-size: 1rem;
}

.add-notification-btn:hover {
  background-color: #45a049;
}

.btn-delete {
  background-color: #ef4444;
  color: white;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.75rem;
}

.btn-delete:hover {
  background-color: #dc2626;
}
</style>