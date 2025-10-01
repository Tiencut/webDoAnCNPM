<template>
  <AdminManagementLayout>
    <template #title>Quản lý Đề tài</template>
    
    <template #actions>
      <div class="topic-actions-header">
        <TopicImportModal/>
        <button @click="openAddTopicModal" class="bg-green-500 text-white px-4 py-2 rounded">Thêm Đề tài</button>
      </div>
    </template>

    <BaseTable
      :headers="topicTableHeaders"
      :items="topics"
      item-key="id"
      :has-actions="true"
      @edit="editTopic"
      @delete="deleteTopic"
    />

    <Modal :show="showTopicModal" @close="closeTopicModal">
      <template #header>
        <h3 class="text-xl font-bold">{{ isEditing ? 'Chỉnh sửa Đề tài' : 'Thêm Đề tài mới' }}</h3>
      </template>
      <template #body>
        <TopicForm
          :topic="currentTopic"
          :is-editing="isEditing"
          @save="saveTopic"
          @cancel="closeTopicModal"
        />
      </template>
    </Modal>
  </AdminManagementLayout>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import BaseTable from '../common/BaseTable.vue';
import Modal from '../common/Modal.vue';
import TopicForm from './TopicForm.vue';
import AdminManagementLayout from './AdminManagementLayout.vue';
import TopicImportModal from '../common/TopicImportModal.vue';

interface Topic {
  id: string;
  name: string;
  description: string;
  lecturerId: string;
  lecturerName: string;
}

const topics = ref<Topic[]>([
  { id: 'DT001', name: 'Nghiên cứu AI trong y tế', description: 'Ứng dụng AI để chẩn đoán bệnh', lecturerId: 'GV001', lecturerName: 'Nguyễn Văn A' },
  { id: 'DT002', name: 'Phát triển ứng dụng di động', description: 'Xây dựng ứng dụng quản lý công việc', lecturerId: 'GV002', lecturerName: 'Trần Thị B' },
  { id: 'DT003', name: 'Hệ thống IoT cho nhà thông minh', description: 'Thiết kế hệ thống điều khiển thiết bị gia đình', lecturerId: 'GV001', lecturerName: 'Nguyễn Văn A' },
]);

const topicTableHeaders = ref([
  { text: 'ID', value: 'id' },
  { text: 'Tên Đề tài', value: 'name' },
  { text: 'Mô tả', value: 'description' },
  { text: 'Giảng viên hướng dẫn', value: 'lecturerName' },
]);

const showTopicModal = ref(false);
const isEditing = ref(false);
const currentTopic = ref<Topic | null>(null);

const openAddTopicModal = () => {
  isEditing.value = false;
  currentTopic.value = { id: '', name: '', description: '', lecturerId: '', lecturerName: '' };
  showTopicModal.value = true;
};

const editTopic = (topic: Topic) => {
  isEditing.value = true;
  currentTopic.value = { ...topic };
  showTopicModal.value = true;
};

const deleteTopic = (topic: Topic) => {
  if (confirm(`Bạn có chắc chắn muốn xóa đề tài "${topic.name}"?`)) {
    topics.value = topics.value.filter(t => t.id !== topic.id);
    alert('Đề tài đã được xóa.');
  }
};

const saveTopic = (topic: Topic) => {
  if (isEditing.value) {
    const index = topics.value.findIndex(t => t.id === topic.id);
    if (index !== -1) {
      topics.value[index] = topic;
    }
  } else {
    topic.id = `DT${String(topics.value.length + 1).padStart(3, '0')}`; // Simple ID generation
    topics.value.push(topic);
  }
  closeTopicModal();
  alert('Đề tài đã được lưu thành công.');
};

const closeTopicModal = () => {
  showTopicModal.value = false;
  currentTopic.value = null;
};

onMounted(() => {
  // Có thể tải dữ liệu từ API tại đây
});
</script>

<style scoped>
.topic-actions-header {
  display: flex;
  align-items: center;
  gap: 1rem; /* Khoảng cách giữa các nút */
}

/* Các style dành riêng cho component này nếu cần */
</style>