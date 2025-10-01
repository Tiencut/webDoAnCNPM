<template>
  <div v-if="show" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <button class="modal-close-button" @click="closeModal">×</button>
      <form @submit.prevent="saveNotification">
        <div class="form-group">
          <label for="title" class="form-label">Tiêu đề:</label>
          <input type="text" id="title" v-model="notification.title" class="form-input" required>
        </div>
        <div class="form-group">
          <label for="content" class="form-label">Nội dung:</label>
          <textarea id="content" v-model="notification.content" class="form-input form-textarea" required></textarea>
        </div>
        <div class="form-group">
          <label for="targetRole" class="form-label">Đối tượng:</label>
          <select id="targetRole" v-model="notification.targetRole" class="form-input" required>
            <option value="all">Tất cả</option>
            <option value="student">Sinh viên</option>
            <option value="lecturer">Giảng viên</option>
            <option value="admin">Quản trị viên</option>
          </select>
        </div>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">
            {{ isEditing ? 'Cập nhật' : 'Thêm Thông báo' }}
          </button>
          <button type="button" @click="cancelEdit" class="btn btn-secondary">
            Hủy
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, reactive } from 'vue';

interface Notification {
  id: number;
  title: string;
  content: string;
  targetRole: 'all' | 'student' | 'lecturer' | 'admin';
  createdAt: string;
}

const props = defineProps<{
  initialNotification: Notification | null;
  show: boolean;
}>();

const emit = defineEmits(['save', 'cancel', 'close']);

const notification = reactive<Notification>({
  id: 0,
  title: '',
  content: '',
  targetRole: 'all',
  createdAt: '',
});

const isEditing = ref(false);

watch(() => props.initialNotification, (newVal) => {
  if (newVal) {
    Object.assign(notification, newVal);
    isEditing.value = true;
  } else {
    resetForm();
    isEditing.value = false;
  }
}, { immediate: true });

watch(() => props.show, (newVal) => {
  if (!newVal) {
    resetForm();
  }
});

const resetForm = () => {
  notification.id = 0;
  notification.title = '';
  notification.content = '';
  notification.targetRole = 'all';
  notification.createdAt = '';
};

const saveNotification = () => {
  emit('save', { ...notification });
  emit('close');
  resetForm();
};

const cancelEdit = () => {
  emit('cancel');
  emit('close');
  resetForm();
};

const closeModal = () => {
  emit('close');
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  width: 90%;
  max-width: 500px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.modal-close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #333;
}

.form-group {
  margin-bottom: 1rem; /* mb-4 */
}

.form-label {
  display: block; /* block */
  color: #4a5568; /* text-gray-700 */
  font-size: 0.875rem; /* text-sm */
  font-weight: bold; /* font-bold */
  margin-bottom: 0.5rem; /* mb-2 */
}

.form-input {
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06); /* shadow */
  appearance: none; /* appearance-none */
  border: 1px solid #e2e8f0; /* border */
  border-radius: 0.25rem; /* rounded */
  width: 100%; /* w-full */
  padding-top: 0.5rem; /* py-2 */
  padding-bottom: 0.5rem; /* py-2 */
  padding-left: 0.75rem; /* px-3 */
  padding-right: 0.75rem; /* px-3 */
  color: #4a5568; /* text-gray-700 */
  line-height: 1.25; /* leading-tight */
}

.form-input:focus {
  outline: none; /* focus:outline-none */
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5); /* focus:shadow-outline */
}

.form-textarea {
  height: 8rem; /* h-32 */
}

.form-actions {
  display: flex; /* flex */
  align-items: center; /* items-center */
  justify-content: space-between; /* justify-between */
  margin-top: 1rem;
}

.btn {
  font-weight: bold; /* font-bold */
  padding-top: 0.5rem; /* py-2 */
  padding-bottom: 0.5rem; /* py-2 */
  padding-left: 1rem; /* px-4 */
  padding-right: 1rem; /* px-4 */
  border-radius: 0.25rem; /* rounded */
  outline: none; /* focus:outline-none */
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5); /* focus:shadow-outline */
}

.btn-primary {
  background-color: #4299e1; /* bg-blue-500 */
  color: #ffffff; /* text-white */
}

.btn-primary:hover {
  background-color: #2b6cb0; /* hover:bg-blue-700 */
}

.btn-secondary {
  background-color: #a0aec0; /* bg-gray-500 */
  color: #ffffff; /* text-white */
}

.btn-secondary:hover {
  background-color: #718096; /* hover:bg-gray-700 */
}
</style>