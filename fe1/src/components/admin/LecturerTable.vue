<template>
  <div class="lecturer-list-section">
    <div class="lecturer-list-header">
      <h2 class="lecturer-list-title">Danh sách Giảng viên</h2>
      <!-- import giảng viên -->
      <LecturerImportModal/>
      <!-- Ô tìm kiếm -->
      <input
        type="text"
        placeholder="Tìm kiếm giảng viên..."
        class="search-input"
        v-model="searchQuery"
      />
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
        :items="filteredLecturers"
        item-key="id"
        :has-actions="true"
        :clickable="true"
        @row-click="editLecturer"
        @delete="deleteLecturer"
      >
        <template #actions="{ item }">
          <button @click.stop="deleteLecturer(item.id)" class="btn-danger">
            Xóa
          </button>
        </template>
      </BaseTable>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import LecturerForm from './LecturerForm.vue';
import Modal from '../common/Modal.vue';
import BaseTable from '../common/BaseTable.vue';
import LecturerImportModal from '../common/LecturerImportModal.vue';
import { computed } from 'vue';

const lecturerTableHeaders = [
  { text: 'ID', value: 'id' },
  { text: 'Họ và Tên', value: 'fullName' },
  { text: 'Email', value: 'email' },
  { text: 'SĐT', value: 'phoneNumber' },
];

interface Lecturer {
  id: number;
  fullName: string;
  email: string;
  phoneNumber: string;
}

const lecturers = ref<Lecturer[]>([
  { id: 1, fullName: 'Nguyễn Văn A', email: 'vana@example.com', phoneNumber: '0912345678' },
  { id: 2, fullName: 'Trần Thị B', email: 'thib@example.com', phoneNumber: '0987654321' },
]);

const currentLecturer = ref<Lecturer>({
  id: 0,
  fullName: '',
  email: '',
  phoneNumber: '',
});

const isEditing = ref(false);
const showForm = ref(false);
const searchQuery = ref('');

const filteredLecturers = computed(() => {
  const query = searchQuery.value.toLowerCase();
  if (!query) {
    return lecturers.value;
  }
  return lecturers.value.filter(
    (lecturer) =>
      lecturer.fullName.toLowerCase().includes(query) ||
      lecturer.email.toLowerCase().includes(query)
  );
});

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
      phoneNumber: '',
    };
    isEditing.value = false;
  }
};

const cancelEdit = () => {
  currentLecturer.value = {
    id: 0,
    fullName: '',
    email: '',
    phoneNumber: '',
  };
  isEditing.value = false;
  showForm.value = false;
};
</script>

<style scoped>
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

.search-input {
  padding: 0.5rem 1rem;
  border: 1px solid #d1d5db;
  border-radius: 0.375rem;
  margin-right: 1rem;
  width: 300px;
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

.btn-danger {
  background-color: #ef4444; /* Tailwind's red-500 */
  color: #fff;
  font-weight: 700;
  padding: 0.5rem 1rem;
  border-radius: 0.375rem;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  transition: background-color 0.2s ease-in-out;
  border: none;
  cursor: pointer;
}

.btn-danger:hover {
  background-color: #dc2626; /* Tailwind's red-600 */
}
</style>