<template>
  <div class="lecturer-form-section">
    <h2 class="lecturer-form-title">{{ isEditing ? 'Chỉnh sửa Giảng viên' : 'Thêm Giảng viên Mới' }}</h2>
    <form @submit.prevent="saveLecturer">
      <div class="form-grid">
        <div>
          <label for="fullName" class="form-label">Họ và Tên</label>
          <input type="text" id="fullName" v-model="currentLecturer.fullName" class="form-input" required />
        </div>
        <div>
          <label for="email" class="form-label">Email</label>
          <input type="email" id="email" v-model="currentLecturer.email" class="form-input" required />
        </div>
        <div>
          <label for="phoneNumber" class="form-label">Số điện thoại</label>
          <input type="text" id="phoneNumber" v-model="currentLecturer.phoneNumber" class="form-input" required />
        </div>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn-primary">
          {{ isEditing ? 'Cập nhật' : 'Thêm' }}
        </button>
        <button type="button" @click="cancelEdit" v-if="isEditing" class="btn-secondary">
          Hủy
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';

interface Lecturer {
  id: number;
  fullName: string;
  email: string;
  phoneNumber: string;
}

const props = defineProps<{
  lecturer: Lecturer;
  isEditing: boolean;
}>();

const emit = defineEmits(['save', 'cancel']);

const currentLecturer = ref<Lecturer>({ ...props.lecturer });

watch(() => props.lecturer, (newVal) => {
  currentLecturer.value = { ...newVal };
});

const saveLecturer = () => {
  emit('save', currentLecturer.value);
};

const cancelEdit = () => {
  emit('cancel');
};
</script>

<style scoped>
.lecturer-form-section {
  margin-bottom: 2rem; /* mb-8 */
  padding: 1.5rem; /* p-6 */
  border: 1px solid #e5e7eb; /* border border-gray-200 */
  border-radius: 0.5rem; /* rounded-lg */
  background-color: #f9fafb; /* bg-gray-50 */
  max-width: 100%;
}

.lecturer-form-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: 600; /* font-semibold */
  margin-bottom: 1rem; /* mb-4 */
  color: #4b5563; /* text-gray-700 */
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr; /* grid-cols-1 */
  gap: 1rem; /* gap-4 */
  margin-bottom: 1rem; /* mb-4 */
  /* border: 1px solid blue; */
}

.form-grid > div {
  width: 100%;
  min-width: 0;
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: 1fr 1fr; /* md:grid-cols-2 */
  }
}

.form-label {
  display: block;
  font-size: 0.875rem; /* text-sm */
  font-weight: 500; /* font-medium */
  color: #4b5563; /* text-gray-700 */
  word-break: break-word;
}

.form-input {
  margin-top: 0.25rem; /* mt-1 */
  display: block;
  width: 100%;
  border: 1px solid #d1d5db; /* border border-gray-300 */
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-sm */
  padding: 0.5rem; /* p-2 */
  overflow-wrap: break-word;
  box-sizing: border-box;
  min-width: 0;
  flex-shrink: 1;
}

.form-actions {
  display: flex;
  gap: 1rem; /* space-x-4 */
}

.btn-primary {
  background-color: #2563eb; /* bg-blue-600 */
  color: #fff;
  font-weight: 700; /* font-bold */
  padding: 0.5rem 1rem; /* py-2 px-4 */
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-md */
  transition: background-color 0.2s ease-in-out;
}

.btn-primary:hover {
  background-color: #1d4ed8; /* hover:bg-blue-700 */
}

.btn-secondary {
  background-color: #9ca3af; /* bg-gray-400 */
  color: #fff;
  font-weight: 700; /* font-bold */
  padding: 0.5rem 1rem; /* py-2 px-4 */
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-md */
  transition: background-color 0.2s ease-in-out;
}

.btn-secondary:hover {
  background-color: #6b7280; /* hover:bg-gray-500 */
}
</style>