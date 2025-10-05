<template>
  <Modal :show="show" @close="cancelEdit">
    <div class="topic-form-card">
      <h2 class="topic-form-title">{{ isEditing ? 'Chỉnh sửa Đề tài' : 'Thêm Đề tài Mới' }}</h2>
      <form @submit.prevent="saveTopic">
        <div class="form-grid">
          <div class="form-group">
            <label for="topicName" class="form-label">Tên Đề tài:</label>
            <input type="text" id="topicName" v-model="currentTopic.name"
              class="form-input"
              required>
          </div>
          <div class="form-group">
            <label for="topicDescription" class="form-label">Mô tả:</label>
            <textarea id="topicDescription" v-model="currentTopic.description"
              class="form-textarea"
              rows="3"
              required></textarea>
          </div>
          <!-- Sinh viên không được phép thay đổi trạng thái đề tài -->
          <!-- <div class="form-group">
            <label for="topicStatus" class="form-label">Trạng thái:</label>
            <select id="topicStatus" v-model="currentTopic.status"
              class="form-select"
              required>
              <option value="pending">Chờ duyệt</option>
              <option value="approved">Đã duyệt</option>
              <option value="rejected">Từ chối</option>
            </select>
          </div> -->
        </div>
        <div class="form-actions">
          <button type="submit"
            class="btn btn-primary">
            {{ isEditing ? 'Cập nhật' : 'Thêm Đề tài' }}
          </button>
          <button type="button" @click="cancelEdit" v-if="isEditing"
            class="btn btn-secondary">
            Hủy
          </button>
        </div>
      </form>
    </div>
  </Modal>
</template>

<script lang="ts">
import { defineComponent, ref, reactive, watch } from 'vue';
import Modal from '../common/Modal.vue';

interface Topic {
  id: number;
  name: string;
  description: string;
  status: string;
}

export default defineComponent({
  name: 'TopicForm',
  components: {
    Modal,
  },
  props: {
    initialTopic: {
      type: Object as () => Topic,
      default: () => ({ id: 0, name: '', description: '', status: 'pending' }),
    },
    isEditingProp: {
      type: Boolean,
      default: false,
    },
    show: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['save', 'close'],
  setup(props, { emit }) {
    const currentTopic = reactive<Topic>({ ...props.initialTopic });
    const isEditing = ref(props.isEditingProp);

    watch(() => props.initialTopic, (newVal) => {
      Object.assign(currentTopic, newVal);
    });

    watch(() => props.isEditingProp, (newVal) => {
      isEditing.value = newVal;
    });

    const resetForm = () => {
      Object.assign(currentTopic, { id: 0, name: '', description: '', status: 'pending' });
      isEditing.value = false;
    };

    const saveTopic = () => {
      emit('save', { ...currentTopic });
      emit('close');
      resetForm();
    };

    const cancelEdit = () => {
      emit('close');
      resetForm();
    };

    return {
      currentTopic,
      isEditing,
      saveTopic,
      cancelEdit,
    };
  },
});
</script>

<style scoped>
.topic-form-card {
  background-color: white;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  margin-bottom: 0;
}

.topic-form-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1rem;
  margin-bottom: 1rem;
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

.form-group {
  margin-bottom: 1rem;
}

.form-label {
  display: block;
  color: #4a5568;
  font-size: 0.875rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.form-input,
.form-textarea,
.form-select {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
  appearance: none;
  border: 1px solid #e2e8f0;
  border-radius: 0.25rem;
  width: 100%;
  padding: 0.5rem 0.75rem;
  color: #4a5568;
  line-height: 1.25;
}

.form-input:focus,
.form-textarea:focus,
.form-select:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5);
}

.form-textarea {
  resize: vertical;
}

.form-actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.btn {
  font-weight: bold;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  outline: none;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

.btn-primary {
  background-color: #4299e1;
  color: white;
}

.btn-primary:hover {
  background-color: #2b6cb0;
}

.btn-secondary {
  background-color: #a0aec0;
  color: white;
}

.btn-secondary:hover {
  background-color: #718096;
}
</style>