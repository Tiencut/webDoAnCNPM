<template>
  <div class="topic-list-card">
    <div class="topic-list-header">
      <h1 class="topic-management-title">Quản lý Đề tài</h1>
      <input
        type="text"
        :value="searchQuery"
        @input="emit('update:searchQuery', ($event.target as HTMLInputElement).value)"
        placeholder="Tìm kiếm đề tài..."
        class="search-input"
      />
      <TopicImportModal />
      <button @click="emit('show-add-topic-modal')" class="btn-add-topic">Thêm Đề tài</button>
    </div>
    <div class="table-responsive">
      <BaseTable :headers="tableHeaders" :items="filteredTopics" item-key="id" :has-actions="true" :clickable="true"
        @row-click="emit('edit-topic', $event)">
        <template #item-groups="{ item }">
          <span v-if="item.groups && item.groups.length > 0">
            <span v-for="(group, index) in item.groups" :key="group.id">
              {{ group.name }}{{ index < item.groups.length - 1 ? ', ' : '' }}
            </span>
          </span>
          <span v-else>Chưa có nhóm</span>
        </template>
        <template #actions="{ item }">
          <button @click.stop="emit('request-change-topic', item)" class="btn-action btn-request-change">Yêu cầu đổi</button>
          <button @click.stop="emit('delete-topic', item)" class="btn-action btn-delete">Xóa</button>
        </template>
      </BaseTable>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineEmits, defineProps, ref, computed } from 'vue';
import TopicImportModal from '../common/TopicImportModal.vue';
import BaseTable from '../common/BaseTable.vue';

interface Group {
  id: string;
  name: string;
}

interface Topic {
  id: string;
  name: string;
  description: string;
  status: string;
  lecturerId?: string;
  lecturerName?: string;
  groups?: Group[];
}

const props = defineProps({
  topics: {
    type: Array as () => Topic[],
    required: true,
  },
  searchQuery: {
    type: String,
    default: '',
  },
});

const emit = defineEmits(['edit-topic', 'delete-topic', 'show-add-topic-modal', 'request-change-topic']);

const filteredTopics = computed(() => {
  if (!props.searchQuery) {
    return props.topics;
  }
  const query = props.searchQuery.toLowerCase();
  return props.topics.filter(topic => 
    topic.name.toLowerCase().includes(query) ||
    topic.description.toLowerCase().includes(query) ||
    (topic.lecturerName && topic.lecturerName.toLowerCase().includes(query))
  );
});

interface TableHeader {
  text: string;
  value: string;
}

const tableHeaders = ref<TableHeader[]>([
  { text: 'ID', value: 'id' },
  { text: 'Tên Đề tài', value: 'name' },
  { text: 'Mô tả', value: 'description' },
  { text: 'Nhóm thực hiện', value: 'groups' },
]);
</script>

<style scoped>
.topic-list-card {
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  padding: 1.5rem;
}

.topic-list-header {
  display: flex;
  align-items: center;
  gap: 1rem; /* Khoảng cách giữa các phần tử */
  margin-bottom: 1rem;
}

.topic-management-title {
  font-size: 1.5rem;
  font-weight: bold;
  color: #333;
  margin-right: auto; /* Đẩy các nút sang phải */
}

.search-input {
  padding: 0.5rem 0.75rem;
  border: 1px solid #d1d5db;
  border-radius: 0.375rem;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  width: 250px; /* Adjust width as needed */
}

.btn-add-topic {
  background-color: #22c55e; /* green-500 */
  color: #fff;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  font-weight: 600;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}

.btn-add-topic:hover {
  background-color: #16a34a; /* green-600 */
}

.topic-list-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.table-responsive {
  overflow-x: auto;
}

.data-table {
  min-width: 100%;
  background-color: white;
  border-collapse: collapse;
}

.data-table thead tr {
  border-bottom: 1px solid #e2e8f0;
}

.table-th {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #e2e8f0;
  background-color: #f7fafc;
  text-align: left;
  font-size: 0.75rem;
  font-weight: 600;
  color: #4a5568;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}


.btn-action {
  padding: 0.3rem 0.6rem;
  border-radius: 0.25rem;
  font-size: 0.875rem;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
  border: none;
  margin-right: 0.5rem;
}

.btn-edit {
  background-color: #3b82f6; /* blue-500 */
  color: #fff;
}

.btn-edit:hover {
  background-color: #2563eb; /* blue-600 */
}

.btn-delete {
  background-color: #ef4444; /* red-500 */
  color: #fff;
}

.btn-delete:hover {
  background-color: #dc2626; /* red-600 */
}

.btn-request-change {
  background-color: #f59e0b; /* amber-500 */
  color: #fff;
}

.btn-request-change:hover {
  background-color: #d97706; /* amber-600 */
}
</style>