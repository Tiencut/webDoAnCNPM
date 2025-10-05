<template>
  <div class="topic-management-container">
    <!-- Đề tài đang đăng ký -->
    <RegisteredTopicDisplay :registeredTopic="registeredTopic" @edit-registered-topic="handleEditRegisteredTopic" @save-registered-topic="handleSaveRegisteredTopic" @cancel-edit-registered-topic="handleCancelEditRegisteredTopic" />
     

    <!-- Bảng danh sách đề tài -->
    <TopicTable :topics="topics" @edit-topic="editTopic" @delete-topic="deleteTopic" @show-add-topic-modal="showAddTopicModal" @register-topic="handleRegisterTopic" />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive, onMounted } from 'vue';
import TopicTable from '../../components/student/TopicTable.vue';
import RegisteredTopicDisplay from '../../components/student/RegisteredTopicDisplay.vue';

interface Topic {
  id: number;
  name: string;
  description: string;
  status: string;
}

export default defineComponent({
  name: 'TopicManagement',
  components: {
    TopicTable,
    RegisteredTopicDisplay,
  },
  setup() {
    const topics = ref<Topic[]>([
      { id: 1, name: 'Đề tài 1', description: 'Mô tả đề tài 1', status: 'pending' },
      { id: 2, name: 'Đề tài 2', description: 'Mô tả đề tài 2', status: 'approved' },
    ]);

    const registeredTopic = ref<Topic | null>(null);

    const fetchRegisteredTopic = () => {
      // Simulate fetching a registered topic
      // In a real application, you would fetch this from an API
      registeredTopic.value = {
        id: 1,
        name: 'Đề tài 1',
        description: 'Mô tả đề tài 1',
        status: 'Đã đăng ký',
        members: ['Nguyễn Văn A', 'Trần Thị B'],
      };
    };

    onMounted(() => {
      fetchRegisteredTopic();
    });

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

    const handleRegisterTopic = (topic: Topic) => {
      registeredTopic.value = { ...topic, status: 'registered' };
      alert(`Bạn đã đăng ký thành công đề tài: ${topic.name}`);
    };

    const resetForm = () => {
      currentTopic.id = 0;
      currentTopic.name = '';
      currentTopic.description = '';
      currentTopic.status = 'pending';
      isEditing.value = false;
    };

    const handleSaveRegisteredTopic = (updatedTopic: Topic) => {
      if (registeredTopic.value && registeredTopic.value.id === updatedTopic.id) {
        registeredTopic.value.name = updatedTopic.name;
        // You might want to update other fields as well, but for now, only name is requested.
      }
    };

    const handleEditRegisteredTopic = (topic: Topic) => {
      // This will be handled by RegisteredTopicDisplay's internal state
      // or passed down as a prop if more complex editing is needed.
      // For now, we just need to ensure the RegisteredTopicDisplay can emit this.
    };

    const handleCancelEditRegisteredTopic = () => {
      // This will be handled by RegisteredTopicDisplay's internal state
    };

    return {
      topics,
      currentTopic,
      isEditing,
      handleSaveTopic,
      handleCancelEdit,
      editTopic,
      showTopicFormModal,
      showAddTopicModal,
      handleRegisterTopic,
      registeredTopic,
      handleSaveRegisteredTopic,
      handleEditRegisteredTopic,
      handleCancelEditRegisteredTopic,
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



/* Xóa các style sau đây vì chúng đã được chuyển sang TopicTable.vue */
/*
.topic-list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.topic-management-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 0;
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
  margin-bottom: 0;
  transition: background-color 0.2s ease-in-out;
}

.add-topic-button:hover {
  background-color: #218838;
}
*/
</style>