<template>
  <AdminManagementLayout>
    <template #title>Quản lý Đề tài</template>
    
    <TopicTable
      :topics="topics"
      :search-query="searchQuery"
      @update:search-query="searchQuery = $event"
      @edit-topic="editTopic"
      @delete-topic="deleteTopic"
      @show-add-topic-modal="openAddTopicModal"
      @request-change-topic="requestChangeTopic"
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
import { ref, onMounted, computed } from 'vue';
import Modal from '../common/Modal.vue';
import TopicForm from './TopicForm.vue';
import AdminManagementLayout from './AdminManagementLayout.vue';
import TopicTable from './TopicTable.vue';

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

const showTopicModal = ref(false);
const isEditing = ref(false);
const currentTopic = ref<Topic | null>(null);

const topics = ref<Topic[]>([
  { id: 'DT001', name: 'Nghiên cứu AI trong y tế', description: 'Ứng dụng AI để chẩn đoán bệnh', status: 'Đang tiến hành', lecturerId: 'GV001', lecturerName: 'Nguyễn Văn A', groups: [{ id: 'NH001', name: 'Nhóm 1' }, { id: 'NH002', name: 'Nhóm 2' }] },
  { id: 'DT002', name: 'Phát triển ứng dụng di động', description: 'Xây dựng ứng dụng quản lý công việc', status: 'Hoàn thành', lecturerId: 'GV002', lecturerName: 'Trần Thị B', groups: [{ id: 'NH003', name: 'Nhóm 3' }] },
  { id: 'DT003', name: 'Hệ thống IoT cho nhà thông minh', description: 'Thiết kế hệ thống điều khiển thiết bị gia đình', status: 'Đang tiến hành', lecturerId: 'GV001', lecturerName: 'Nguyễn Văn A', groups: [{ id: 'NH004', name: 'Nhóm 4' }] },
]);

const searchQuery = ref('');

const filteredTopics = computed(() => {
  if (!searchQuery.value) {
    return topics.value;
  }
  const query = searchQuery.value.toLowerCase();
  return topics.value.filter(topic => 
    topic.name.toLowerCase().includes(query) ||
    topic.description.toLowerCase().includes(query) ||
    (topic.lecturerName && topic.lecturerName.toLowerCase().includes(query))
  );
});

const openAddTopicModal = () => {
  isEditing.value = false;
  currentTopic.value = { id: '', name: '', description: '', status: 'pending', lecturerId: '', lecturerName: '' };
  showTopicModal.value = true;
};

const editTopic = (topic: Topic) => {
  isEditing.value = true;
  currentTopic.value = { ...topic };
  showTopicModal.value = true;
};

const deleteTopic = (topic: Topic) => {
  console.log('deleteTopic called for topic:', topic);
  if (confirm(`Bạn có chắc chắn muốn xóa đề tài "${topic.name}"?`)) {
    const index = topics.value.findIndex(t => t.id === topic.id);
    if (index !== -1) {
      topics.value.splice(index, 1);
      alert('Đề tài đã được xóa thành công.');
    }
  }
};

const saveTopic = (topic: Topic) => {
  if (isEditing.value) {
    const index = topics.value.findIndex(t => t.id === topic.id);
    if (index !== -1) {
      topics.value[index] = topic;
    }
  } else {
    topic.id = `DT${String(topics.value.length + 1).padStart(3, '0')}`;
    topics.value.push(topic);
  }
  closeTopicModal();
  alert('Đề tài đã được lưu thành công.');
};

const requestChangeTopic = (topic: Topic) => {
  console.log('requestChangeTopic called for topic:', topic);
  if (confirm(`Gửi thông báo yêu cầu cho nhóm đổi đề tài?"${topic.name}"?`)) {
    // Logic để gửi yêu cầu đổi đề tài sẽ được thêm vào đây (ví dụ: gọi API)
    alert(`Yêu cầu đổi đề tài "${topic.name}" đã được gửi.`);
  }
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
/* Các style dành riêng cho component này nếu cần */
</style>