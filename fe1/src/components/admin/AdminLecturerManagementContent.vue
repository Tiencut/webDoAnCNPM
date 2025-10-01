<template>
  <div class="lecturer-management-container">
    <!-- Danh sách giảng viên -->
    <div class="lecturer-list-section">
      <div class="lecturer-list-header">
        <h2 class="lecturer-list-title">Danh sách Giảng viên</h2>
        <!-- import giảng viên -->
        <LecturerImportModal/>
        <!-- thêm giảng viên -->
        <button @click="toggleForm" class="btn-primary add-lecturer-btn">
          {{ showForm ? 'Ẩn Form' : 'Thêm Giảng viên Mới' }}
        </button>
      </div>

      <!-- Form thêm/sửa giảng viên -->
      <Modal :show="showForm" @close="cancelEdit">
        <LecturerForm
          :lecturer="currentLecturer"
          :isEditing="isEditing"
          @save="handleSaveLecturer"
          @cancel="cancelEdit"
        />
      </Modal>
      <div class="table-responsive">
        <BaseTable
          :headers="lecturerTableHeaders"
          :items="lecturers"
          item-key="id"
          :has-actions="true"
          @edit="editLecturer"
          @delete="deleteLecturer"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import LecturerForm from './LecturerForm.vue';
import Modal from '../common/Modal.vue';
import BaseTable from '../common/BaseTable.vue';
import LecturerImportModal from '../common/LecturerImportModal.vue';

const lecturerTableHeaders = [
  { text: 'ID', value: 'id' },
  { text: 'Họ và Tên', value: 'fullName' },
  { text: 'Email', value: 'email' },
  { text: 'Số đề tài tối đa', value: 'maxTopics' },
];

interface Lecturer {
  id: number;
  fullName: string;
  email: string;
  maxTopics: number;
}

const lecturers = ref<Lecturer[]>([
  { id: 1, fullName: 'Nguyễn Văn A', email: 'vana@example.com', maxTopics: 5 },
  { id: 2, fullName: 'Trần Thị B', email: 'thib@example.com', maxTopics: 7 },
]);

const currentLecturer = ref<Lecturer>({
  id: 0,
  fullName: '',
  email: '',
  maxTopics: 0,
});

const isEditing = ref(false);
const showForm = ref(false);

const handleSaveLecturer = (lecturer: Lecturer) => {
  if (isEditing.value) {
    const index = lecturers.value.findIndex((l) => l.id === lecturer.id);
    if (index !== -1) {
      lecturers.value[index] = { ...lecturer };
    }
  } else {
    lecturer.id = lecturers.value.length > 0 ? Math.max(...lecturers.value.map((l) => l.id)) + 1 : 1;
    lecturers.value.push({ ...lecturer });
  }
  cancelEdit();
  showForm.value = false; // Hide form after saving
};

const editLecturer = (lecturer: Lecturer) => {
  currentLecturer.value = { ...lecturer };
  isEditing.value = true;
  showForm.value = true; // Show form when editing
};

const deleteLecturer = (id: number) => {
  if (confirm('Bạn có chắc chắn muốn xóa giảng viên này không?')) {
    lecturers.value = lecturers.value.filter((l) => l.id !== id);
  }
};

const toggleForm = () => {
  showForm.value = !showForm.value;
  if (!showForm.value) {
    // Reset form when hiding
    currentLecturer.value = {
      id: 0,
      fullName: '',
      email: '',
      maxTopics: 0,
    };
    isEditing.value = false;
  }
};

const cancelEdit = () => {
  currentLecturer.value = {
    id: 0,
    fullName: '',
    email: '',
    maxTopics: 0,
  };
  isEditing.value = false;
  showForm.value = false;
};
</script>

<style scoped>
.lecturer-management-container {
  padding: 1.5rem; /* p-6 */
  background-color: #fff; /* bg-white */
  border-radius: 0.5rem; /* rounded-lg */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* shadow-md */
}

.lecturer-management-title {
  font-size: 1.875rem; /* text-3xl */
  font-weight: 700; /* font-bold */
  margin-bottom: 1.5rem; /* mb-6 */
  color: #374151; /* text-gray-800 */
}

.lecturer-form-section {
  margin-bottom: 2rem; /* mb-8 */
  padding: 1.5rem; /* p-6 */
  border: 1px solid #e5e7eb; /* border border-gray-200 */
  border-radius: 0.5rem; /* rounded-lg */
  background-color: #f9fafb; /* bg-gray-50 */
}

.lecturer-form-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: 600; /* font-semibold */
  margin-bottom: 1rem; /* mb-4 */
  color: #4b5563; /* text-gray-700 */
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(1, minmax(0, 1fr)); /* grid-cols-1 */
  gap: 1rem; /* gap-4 */
  margin-bottom: 1rem; /* mb-4 */
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr)); /* md:grid-cols-2 */
  }
}

.form-label {
  display: block;
  font-size: 0.875rem; /* text-sm */
  font-weight: 500; /* font-medium */
  color: #4b5563; /* text-gray-700 */
}

.form-input {
  margin-top: 0.25rem; /* mt-1 */
  display: block;
  width: 100%;
  border: 1px solid #d1d5db; /* border border-gray-300 */
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-sm */
  padding: 0.5rem; /* p-2 */
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

.add-lecturer-btn {
  /* margin-bottom: 1.5rem; */ /* mb-6 */
}

.lecturer-list-section {
  background-color: #fff; /* bg-white */
  padding: 1.5rem; /* p-6 */
  border-radius: 0.5rem; /* rounded-lg */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* shadow-md */
}

.lecturer-list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem; /* Khoảng cách giữa header và bảng */
}

.lecturer-list-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: 600; /* font-semibold */
  margin-bottom: 0; /* Đặt lại margin-bottom */
  color: #4b5563; /* text-gray-700 */
}


</style>