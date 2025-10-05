<template>
  <div class="topic-list-card">
    <!-- Form thêm/chỉnh sửa đề tài -->
    <TopicForm :initialTopic="currentTopic" :isEditingProp="isEditing" :show="showTopicFormModal" @save="handleSaveTopic" @close="handleCancelEdit" />
    <div class="topic-list-header">
      <h1 class="topic-management-title">Danh sách Đề tài</h1>
      <button @click="showAddTopicModal" class="btn-add-topic">Đăng ký đề tài mới</button>
      <input
        type="text"
        :value="searchQuery"
        @input="emit('update:searchQuery', ($event.target as HTMLInputElement).value)"
        placeholder="Tìm kiếm đề tài..."
        class="search-input"
      />
    </div>
    <div class="table-responsive">
      <BaseTable :headers="tableHeaders" :items="filteredTopics" item-key="id" :has-actions="true" :clickable="true"
        @row-click="editTopic">
        <template #item-groups="{ item }">
          <span v-if="item.groups && item.groups.length > 0">
            <span v-for="(group, index) in item.groups" :key="group.id">
              {{ group.name }}{{ index < item.groups.length - 1 ? ', ' : '' }}
            </span>
          </span>
          <span v-else>Chưa có nhóm</span>
        </template>
        <template #item-members="{ item }">
          <span v-if="item.members && item.members.length > 0">
            <span v-for="(member, index) in item.members" :key="member.id">
              {{ member.name }}{{ index < item.members.length - 1 ? ', ' : '' }}
            </span>
          </span>
          <span v-else>Chưa có thành viên</span>
        </template>
        <template #actions="{ item }">
          <button v-if="!item.groups || item.groups.length === 0" @click.stop="registerTopic(item)" class="btn-action btn-register-topic">Đăng ký đề tài</button>
        </template>
      </BaseTable>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineEmits, defineProps, ref, computed } from 'vue';
import TopicForm from './TopicForm.vue'; // Import TopicForm
// ... existing code ...
const currentTopic = ref<Topic | null>(null);
const isEditing = ref(false);
const showTopicFormModal = ref(false);
// import TopicImportModal from '../common/TopicImportModal.vue'; // Xóa import này
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
  members?: Member[];
}

interface Member {
  id: string;
  name: string;
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

const emit = defineEmits(['edit-topic', 'delete-topic', 'show-add-topic-modal', 'register-topic']);

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
  { text: 'Thành viên', value: 'members' },
]);

const handleSaveTopic = (topic: Topic) => {
  console.log('Saving topic:', topic);
  showTopicFormModal.value = false;
};

const handleCancelEdit = () => {
  showTopicFormModal.value = false;
  isEditing.value = false;
  currentTopic.value = null;
};

const editTopic = (topic: Topic) => {
  currentTopic.value = { ...topic };
  isEditing.value = true;
  showTopicFormModal.value = true;
};

const showAddTopicModal = () => {
  currentTopic.value = null;
  isEditing.value = false;
  showTopicFormModal.value = true;
};

const registerTopic = (topic: Topic) => {
  if (confirm(`Bạn có chắc chắn muốn đăng ký đề tài "${topic.name}" không?`)) {
    console.log('Registering for topic:', topic);
    // Implement your logic for registering topic here
    // For example, emit an event to the parent component or show a confirmation modal
  }
};
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

.btn-register-topic {
  display: inline-block;
  background-color: #10b981; /* green-500 */
  color: #fff;
}

.btn-register-topic:hover {
  background-color: #059669; /* green-600 */
}
</style>