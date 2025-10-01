<template>
  <Modal :show="show" @close="cancelEdit">
    <div class="topic-form-card">
      <h2 class="topic-form-title">{{ isEditing ? 'Chỉnh sửa Đề tài' : 'Thêm Đề tài mới' }}</h2>
      <form @submit.prevent="saveTopic">
        <div class="form-grid">
          <div>
            <label for="topicId" class="form-label">Mã Đề tài</label>
            <input type="text" id="topicId" v-model="editableTopic.topicId"
                   class="form-input" required :disabled="isEditing">
          </div>
          <div>
            <label for="topicName" class="form-label">Tên Đề tài</label>
            <input type="text" id="topicName" v-model="editableTopic.topicName"
                   class="form-input" required>
          </div>
          <div>
            <label for="lecturer" class="form-label">Giảng viên hướng dẫn</label>
            <input type="text" id="lecturer" v-model="editableTopic.lecturer"
                   class="form-input">
          </div>
          <div>
            <label for="description" class="form-label">Mô tả</label>
            <textarea id="description" v-model="editableTopic.description"
                      class="form-input form-textarea"></textarea>
          </div>
        </div>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">
            {{ isEditing ? 'Cập nhật' : 'Thêm' }}
          </button>
          <button type="button" @click="cancelEdit"
                  class="btn btn-secondary">
            Hủy
          </button>
        </div>
      </form>
    </div>
  </Modal>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import Modal from '../common/Modal.vue';

interface Topic {
  id: string;
  name: string;
  description: string;
  lecturerId: string;
  lecturerName?: string; // Optional for form, will be populated in parent
}

const props = defineProps({
  topic: {
    type: Object as () => Topic,
    required: true,
  },
  isEditing: {
    type: Boolean,
    required: true,
  },
  show: Boolean, // Thêm prop show để điều khiển hiển thị modal
});

const emit = defineEmits(['save', 'close']); // Thêm sự kiện close

const editableTopic = ref<Topic>({ ...props.topic });

watch(() => props.topic, (newVal) => {
  editableTopic.value = { ...newVal };
});

const saveTopic = () => {
  emit('save', editableTopic.value);
  emit('close'); // Đóng modal sau khi lưu
};

const cancelEdit = () => {
  emit('close'); // Đóng modal khi hủy
};
</script>

<style scoped>
.topic-form-card {
  background-color: #fff;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 0 auto;
}

.topic-form-title {
  font-size: 1.75rem;
  color: #333;
  margin-bottom: 1.5rem;
  text-align: center;
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

@media (min-width: 600px) {
  .form-grid {
    grid-template-columns: 1fr 1fr;
  }
}

.form-label {
  display: block;
  font-weight: 600;
  color: #555;
  margin-bottom: 0.5rem;
}

.form-input,
.form-textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  transition: border-color 0.2s ease-in-out;
}

.form-input:focus,
.form-textarea:focus {
  border-color: #007bff;
  outline: none;
}

.form-textarea {
  resize: vertical;
  min-height: 80px;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
}

.btn {
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
  border: 1px solid #007bff;
}

.btn-primary:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  color: #fff;
  border: 1px solid #6c757d;
}

.btn-secondary:hover {
  background-color: #5a6268;
  border-color: #5a6268;
}
</style>