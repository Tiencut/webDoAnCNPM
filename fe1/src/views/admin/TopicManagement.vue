<template>
  <div class="topic-management-container">
    <h1 class="topic-management-title">Quản lý Đề tài</h1>

    <button @click="showAddTopicModal" class="btn btn-primary add-topic-button">Thêm Đề tài Mới</button>

    <!-- Form thêm/chỉnh sửa đề tài -->
    <TopicForm :initialTopic="currentTopic" :isEditingProp="isEditing" :show="showTopicFormModal" @save="handleSaveTopic" @close="handleCancelEdit" />

    <!-- Bảng danh sách đề tài -->
    <div class="topic-list-card">
      <h2 class="topic-list-title">Danh sách Đề tài</h2>
      <div class="table-responsive">
        <table class="data-table">
          <thead>
            <tr>
              <th class="table-th">
                Tên Đề tài
              </th>
              <th class="table-th">
                Mô tả
              </th>
              <th class="table-th">
                Trạng thái
              </th>
              <th class="table-th">
                Hành động
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="topic in topics" :key="topic.id">
              <td class="table-td">{{ topic.name }}</td>
              <td class="table-td">{{ topic.description }}</td>
              <td class="table-td">{{ topic.status }}</td>
              <td class="table-td">
                <button @click="editTopic(topic)"
                  class="btn-edit">
                  Sửa
                </button>
                <button @click="deleteTopic(topic.id)"
                  class="btn-delete">
                  Xóa
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import TopicForm from '../../components/TopicForm.vue';

interface Topic {
  id: number;
  name: string;
  description: string;
  status: string;
}

export default defineComponent({
  name: 'TopicManagement',
  components: {
    TopicForm,
  },
  setup() {
    const topics = ref<Topic[]>([
      { id: 1, name: 'Đề tài 1', description: 'Mô tả đề tài 1', status: 'pending' },
      { id: 2, name: 'Đề tài 2', description: 'Mô tả đề tài 2', status: 'approved' },
    ]);

    const currentTopic = reactive<Topic>({
      id: 0,
      name: '',
      description: '',
      status: 'pending',
    });

    const isEditing = ref(false);
    const showTopicFormModal = ref(false);

    const handleSaveTopic = (topic: Topic) => {
      if (isEditing.value) {
        const index = topics.value.findIndex(t => t.id === topic.id);
        if (index !== -1) {
          topics.value[index] = { ...topic };
        }
      } else {
        topic.id = topics.value.length > 0 ? Math.max(...topics.value.map(t => t.id)) + 1 : 1;
        topics.value.push({ ...topic });
      }
      showTopicFormModal.value = false; // Đóng modal sau khi lưu
      resetForm();
    };

    const handleCancelEdit = () => {
      showTopicFormModal.value = false; // Đóng modal khi hủy
      resetForm();
    };

    const editTopic = (topic: Topic) => {
      Object.assign(currentTopic, topic);
      isEditing.value = true;
      showTopicFormModal.value = true; // Hiển thị modal khi chỉnh sửa
    };

    const showAddTopicModal = () => {
      resetForm();
      isEditing.value = false;
      showTopicFormModal.value = true; // Hiển thị modal khi thêm mới
    };

    const deleteTopic = (id: number) => {
      topics.value = topics.value.filter(t => t.id !== id);
    };

    const resetForm = () => {
      currentTopic.id = 0;
      currentTopic.name = '';
      currentTopic.description = '';
      currentTopic.status = 'pending';
      isEditing.value = false;
    };

    return {
      topics,
      currentTopic,
      isEditing,
      handleSaveTopic,
      handleCancelEdit,
      editTopic,
      deleteTopic,
      showTopicFormModal,
      showAddTopicModal,
    };
  },
});
</script>

<style scoped>
.topic-management-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem;
}

.topic-management-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

.add-topic-button {
  background-color: #28a745;
  color: white;
  padding: 0.75rem 1.5rem;
  border-radius: 0.25rem;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  cursor: pointer;
  margin-bottom: 1.5rem;
  transition: background-color 0.2s ease-in-out;
}

.add-topic-button:hover {
  background-color: #218838;
}

.topic-list-card {
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  padding: 1.5rem;
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

.table-td {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #e2e8f0;
}

.btn-edit {
  background-color: #ecc94b;
  color: white;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.75rem;
  margin-right: 0.5rem;
}

.btn-edit:hover {
  background-color: #d69e2e;
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