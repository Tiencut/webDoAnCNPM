<template>
  <div class="notification-form-card">
    <h2 class="notification-form-title">{{ isEditingProp ? 'Chỉnh sửa Thông báo' : 'Thêm Thông báo Mới' }}</h2>
    <form @submit.prevent="saveNotification">
      <div class="form-group">
        <label for="title" class="form-label">Tiêu đề:</label>
        <input type="text" id="title" v-model="notification.title"
          class="form-input"
          required>
      </div>
      <div class="form-group">
        <label for="message" class="form-label">Nội dung:</label>
        <textarea id="message" v-model="notification.message"
          class="form-textarea"
          rows="4"
          required></textarea>
      </div>
      <div class="form-group">
        <label for="date" class="form-label">Ngày:</label>
        <input type="date" id="date" v-model="notification.date"
          class="form-input"
          required>
      </div>
      <div class="form-actions">
        <button type="submit"
          class="btn btn-primary">
          {{ isEditingProp ? 'Cập nhật' : 'Thêm Thông báo' }}
        </button>
        <button type="button" @click="cancelEdit" v-if="isEditingProp"
          class="btn btn-secondary">
          Hủy
        </button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch, reactive } from 'vue';

interface Notification {
  id: number;
  title: string;
  message: string;
  date: string;
}

export default defineComponent({
  name: 'NotificationForm',
  props: {
    initialNotification: {
      type: Object as () => Notification,
      default: () => ({
        id: 0,
        title: '',
        message: '',
        date: '',
      }),
    },
    isEditingProp: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['save', 'cancel'],
  setup(props, { emit }) {
    const notification = reactive<Notification>({
      id: 0,
      title: '',
      message: '',
      date: '',
    });

    watch(
      () => props.initialNotification,
      (newVal) => {
        if (newVal) {
          Object.assign(notification, newVal);
        }
      },
      { immediate: true, deep: true }
    );

    watch(
      () => props.isEditingProp,
      (newVal) => {
        if (!newVal) {
          resetForm();
        }
      }
    );

    const saveNotification = () => {
      emit('save', { ...notification });
    };

    const cancelEdit = () => {
      emit('cancel');
    };

    const resetForm = () => {
      notification.id = 0;
      notification.title = '';
      notification.message = '';
      notification.date = '';
    };

    return {
      notification,
      saveNotification,
      cancelEdit,
    };
  },
});
</script>

<style scoped>
.notification-form-card {
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
}

.notification-form-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
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
.form-textarea {
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
.form-textarea:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5);
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