<template>
  <transition name="fade">
    <div v-if="showForm" class="modal-overlay" @click.self="cancelEdit">
      <div class="modal-content" role="dialog" aria-modal="true" aria-labelledby="modalTitle">
        <button aria-label="Close form" class="modal-close-button" @click="cancelEdit">×</button>
        <form @submit.prevent="handleSaveLecturer(currentLecturer)">
          <div class="form-field">
            <label for="fullName" id="modalTitle">
              Họ và Tên <span class="required">*</span>
            </label>
            <input
              id="fullName"
              type="text"
              v-model.trim="currentLecturer.fullName"
              required
              autofocus
              placeholder="Nhập họ và tên"
            />
          </div>

          <div class="form-field">
            <label for="email">
              Email <span class="required">*</span>
            </label>
            <input
              id="email"
              type="email"
              v-model.trim="currentLecturer.email"
              required
              placeholder="example@mail.com"
            />
          </div>

          <div class="form-field">
            <label for="phoneNumber">
              SĐT <span class="required">*</span>
            </label>
            <input
              id="phoneNumber"
              type="tel"
              v-model.trim="currentLecturer.phoneNumber"
              required
              placeholder="Nhập số điện thoại"
              pattern="^[0-9+\-\s()]*$"
              title="Chỉ được nhập số và ký tự +, -, (), khoảng trắng"
            />
          </div>

          <div class="form-actions">
            <button type="submit" class="btn-primary">
              {{ isEditing ? 'Cập nhật' : 'Thêm' }}
            </button>
            <button type="button" class="btn-danger" @click="cancelEdit">Hủy</button>
          </div>
        </form>
      </div>
    </div>
  </transition>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from 'vue';

interface Lecturer {
  id: number;
  fullName: string;
  email: string;
  phoneNumber: string;
}

const props = defineProps<{
  showForm: boolean;
  editingLecturer: Lecturer | null;
}>();

const emit = defineEmits(['save', 'cancel']);

const currentLecturer = ref<Lecturer>({
  id: 0,
  fullName: '',
  email: '',
  phoneNumber: '',
});

const isEditing = ref(false);

watch(
  () => props.editingLecturer,
  (newVal) => {
    if (newVal) {
      currentLecturer.value = { ...newVal };
      isEditing.value = true;
    } else {
      currentLecturer.value = { id: 0, fullName: '', email: '', phoneNumber: '' };
      isEditing.value = false;
    }
  },
  { immediate: true }
);

const nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/u;

const handleSaveLecturer = (lecturer: Lecturer) => {
  if (!nameRegex.test(lecturer.fullName)) {
    alert('Tên không hợp lệ. Vui lòng chỉ nhập chữ cái và khoảng trắng.');
    return;
  }
  emit('save', { ...lecturer });
};

const cancelEdit = () => {
  emit('cancel');
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: #fff;
  border-radius: 12px;
  padding: 24px 32px;
  width: 100%;
  max-width: 560px;
  box-shadow: 0 10px 36px rgba(30, 41, 59, 0.15);
  position: relative;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.modal-close-button {
  position: absolute;
  top: 16px;
  right: 20px;
  background: transparent;
  border: none;
  font-size: 1.8rem;
  line-height: 1;
  cursor: pointer;
  color: #6b7280; /* Gray-500 */
  transition: color 0.2s ease;
}
.modal-close-button:hover {
  color: #374151; /* Gray-700 */
}

.required {
  color: #ef4444; /* Red-600 */
  font-weight: 600;
}

.form-field {
  margin-bottom: 20px;
}

.form-field label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
  color: #374151;
}

.form-field input[type='text'],
.form-field input[type='email'],
.form-field input[type='tel'] {
  width: 100%;
  padding: 10px 14px;
  border: 1.5px solid #d1d5db; /* Gray-300 */
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.3s ease;
}

.form-field input:focus {
  outline: none;
  border-color: #2563eb; /* Blue-600 */
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.3);
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 24px;
}

.btn-primary {
  background-color: #2563eb;
  color: #fff;
  font-weight: 700;
  padding: 12px 24px;
  border-radius: 10px;
  border: none;
  cursor: pointer;
  font-size: 1rem;
  box-shadow: 0 4px 12px rgba(37, 99, 235, 0.4);
  transition: background-color 0.25s ease;
}
.btn-primary:hover {
  background-color: #1d4ed8;
}

.btn-danger {
  background-color: #ef4444;
  color: #fff;
  font-weight: 700;
  padding: 12px 24px;
  border-radius: 10px;
  border: none;
  cursor: pointer;
  font-size: 1rem;
  box-shadow: 0 4px 12px rgba(239, 68, 68, 0.4);
  transition: background-color 0.25s ease;
}
.btn-danger:hover {
  background-color: #dc2626;
}

/* Fade transition for modal */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
